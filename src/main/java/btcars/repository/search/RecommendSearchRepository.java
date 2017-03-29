package btcars.repository.search;

import btcars.domain.Recommend;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Recommend entity.
 */
public interface RecommendSearchRepository extends ElasticsearchRepository<Recommend, Long> {
}
