package btcars.service;

import com.codahale.metrics.annotation.Timed;
import btcars.domain.*;
import btcars.repository.*;
import btcars.repository.search.*;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class ElasticsearchIndexService {

    private final Logger log = LoggerFactory.getLogger(ElasticsearchIndexService.class);

    private final CarRepository carRepository;

    private final CarSearchRepository carSearchRepository;

    private final CustomerRepository customerRepository;

    private final CustomerSearchRepository customerSearchRepository;

    private final OrderlistRepository orderlistRepository;

    private final OrderlistSearchRepository orderlistSearchRepository;

    private final UserRepository userRepository;

    private final UserSearchRepository userSearchRepository;

    private final ElasticsearchTemplate elasticsearchTemplate;

    public ElasticsearchIndexService(
        UserRepository userRepository,
        UserSearchRepository userSearchRepository,
        CarRepository carRepository,
        CarSearchRepository carSearchRepository,
        CustomerRepository customerRepository,
        CustomerSearchRepository customerSearchRepository,
        OrderlistRepository orderlistRepository,
        OrderlistSearchRepository orderlistSearchRepository,
        ElasticsearchTemplate elasticsearchTemplate) {
        this.userRepository = userRepository;
        this.userSearchRepository = userSearchRepository;
        this.carRepository = carRepository;
        this.carSearchRepository = carSearchRepository;
        this.customerRepository = customerRepository;
        this.customerSearchRepository = customerSearchRepository;
        this.orderlistRepository = orderlistRepository;
        this.orderlistSearchRepository = orderlistSearchRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Async
    @Timed
    public void reindexAll() {
        reindexForClass(Car.class, carRepository, carSearchRepository);
        reindexForClass(Customer.class, customerRepository, customerSearchRepository);
        reindexForClass(Orderlist.class, orderlistRepository, orderlistSearchRepository);
        reindexForClass(User.class, userRepository, userSearchRepository);

        log.info("Elasticsearch: Successfully performed reindexing");
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    private <T, ID extends Serializable> void reindexForClass(Class<T> entityClass, JpaRepository<T, ID> jpaRepository,
                                                              ElasticsearchRepository<T, ID> elasticsearchRepository) {
        elasticsearchTemplate.deleteIndex(entityClass);
        try {
            elasticsearchTemplate.createIndex(entityClass);
        } catch (IndexAlreadyExistsException e) {
            // Do nothing. Index was already concurrently recreated by some other service.
        }
        elasticsearchTemplate.putMapping(entityClass);
        if (jpaRepository.count() > 0) {
            try {
                Method m = jpaRepository.getClass().getMethod("findAllWithEagerRelationships");
                elasticsearchRepository.save((List<T>) m.invoke(jpaRepository));
            } catch (Exception e) {
                elasticsearchRepository.save(jpaRepository.findAll());
            }
        }
        log.info("Elasticsearch: Indexed all rows for " + entityClass.getSimpleName());
    }
}
