package btcars.web.rest;

import com.codahale.metrics.annotation.Timed;
import btcars.domain.Orderlist;

import btcars.repository.OrderlistRepository;
import btcars.repository.search.OrderlistSearchRepository;
import btcars.security.AuthoritiesConstants;
import btcars.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Orderlist.
 */
@RestController
@RequestMapping("/api")
public class OrderlistResource {

    private final Logger log = LoggerFactory.getLogger(OrderlistResource.class);

    private static final String ENTITY_NAME = "orderlist";
        
    private final OrderlistRepository orderlistRepository;

    private final OrderlistSearchRepository orderlistSearchRepository;

    public OrderlistResource(OrderlistRepository orderlistRepository, OrderlistSearchRepository orderlistSearchRepository) {
        this.orderlistRepository = orderlistRepository;
        this.orderlistSearchRepository = orderlistSearchRepository;
    }

    /**
     * POST  /orderlists : Create a new orderlist.
     *
     * @param orderlist the orderlist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderlist, or with status 400 (Bad Request) if the orderlist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/orderlists")
    @Timed
    @Secured(AuthoritiesConstants.USER)
    public ResponseEntity<Orderlist> createOrderlist(@Valid @RequestBody Orderlist orderlist) throws URISyntaxException {
        log.debug("REST request to save Orderlist : {}", orderlist);
        if (orderlist.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new orderlist cannot already have an ID")).body(null);
        }
        Orderlist result = orderlistRepository.save(orderlist);
        orderlistSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/orderlists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orderlists : Updates an existing orderlist.
     *
     * @param orderlist the orderlist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderlist,
     * or with status 400 (Bad Request) if the orderlist is not valid,
     * or with status 500 (Internal Server Error) if the orderlist couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     
    @PutMapping("/orderlists")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Orderlist> updateOrderlist(@Valid @RequestBody Orderlist orderlist) throws URISyntaxException {
        log.debug("REST request to update Orderlist : {}", orderlist);
        if (orderlist.getId() == null) {
            return createOrderlist(orderlist);
        }
        Orderlist result = orderlistRepository.save(orderlist);
        orderlistSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderlist.getId().toString()))
            .body(result);
    }
    */

    /**
     * GET  /orderlists : get all the orderlists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of orderlists in body
     */
    @GetMapping("/orderlists")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public List<Orderlist> getAllOrderlists() {
        log.debug("REST request to get all Orderlists");
        List<Orderlist> orderlists = orderlistRepository.findAllWithEagerRelationships();
        return orderlists;
    }

    /**
     * GET  /orderlists/:id : get the "id" orderlist.
     *
     * @param id the id of the orderlist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderlist, or with status 404 (Not Found)
     */
    @GetMapping("/orderlists/{id}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Orderlist> getOrderlist(@PathVariable Long id) {
        log.debug("REST request to get Orderlist : {}", id);
        Orderlist orderlist = orderlistRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderlist));
    }

    /**
     * DELETE  /orderlists/:id : delete the "id" orderlist.
     *
     * @param id the id of the orderlist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/orderlists/{id}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteOrderlist(@PathVariable Long id) {
        log.debug("REST request to delete Orderlist : {}", id);
        orderlistRepository.delete(id);
        orderlistSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/orderlists?query=:query : search for the orderlist corresponding
     * to the query.
     *
     * @param query the query of the orderlist search 
     * @return the result of the search
     */
    @GetMapping("/_search/orderlists")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public List<Orderlist> searchOrderlists(@RequestParam String query) {
        log.debug("REST request to search Orderlists for query {}", query);
        return StreamSupport
            .stream(orderlistSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
