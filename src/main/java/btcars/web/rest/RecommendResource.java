package btcars.web.rest;

import com.codahale.metrics.annotation.Timed;
import btcars.domain.Recommend;

import btcars.repository.RecommendRepository;
import btcars.repository.search.RecommendSearchRepository;
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
 * REST controller for managing Recommend.
 */
@RestController
@RequestMapping("/api")
public class RecommendResource {

    private final Logger log = LoggerFactory.getLogger(RecommendResource.class);

    private static final String ENTITY_NAME = "recommend";
        
    private final RecommendRepository recommendRepository;

    private final RecommendSearchRepository recommendSearchRepository;

    public RecommendResource(RecommendRepository recommendRepository, RecommendSearchRepository recommendSearchRepository) {
        this.recommendRepository = recommendRepository;
        this.recommendSearchRepository = recommendSearchRepository;
    }

    /**
     * POST  /recommends : Create a new recommend.
     *
     * @param recommend the recommend to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recommend, or with status 400 (Bad Request) if the recommend has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recommends")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Recommend> createRecommend(@Valid @RequestBody Recommend recommend) throws URISyntaxException {
        log.debug("REST request to save Recommend : {}", recommend);
        if (recommend.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new recommend cannot already have an ID")).body(null);
        }
        Recommend result = recommendRepository.save(recommend);
        recommendSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/recommends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recommends : Updates an existing recommend.
     *
     * @param recommend the recommend to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recommend,
     * or with status 400 (Bad Request) if the recommend is not valid,
     * or with status 500 (Internal Server Error) if the recommend couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recommends")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Recommend> updateRecommend(@Valid @RequestBody Recommend recommend) throws URISyntaxException {
        log.debug("REST request to update Recommend : {}", recommend);
        if (recommend.getId() == null) {
            return createRecommend(recommend);
        }
        Recommend result = recommendRepository.save(recommend);
        recommendSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recommend.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recommends : get all the recommends.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of recommends in body
     */
    @GetMapping("/recommends")
    @Timed
    public List<Recommend> getAllRecommends() {
        log.debug("REST request to get all Recommends");
        List<Recommend> recommends = recommendRepository.findAll();
        return recommends;
    }

    /**
     * GET  /recommends/:id : get the "id" recommend.
     *
     * @param id the id of the recommend to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recommend, or with status 404 (Not Found)
     */
    @GetMapping("/recommends/{id}")
    @Timed
    public ResponseEntity<Recommend> getRecommend(@PathVariable Long id) {
        log.debug("REST request to get Recommend : {}", id);
        Recommend recommend = recommendRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(recommend));
    }

    /**
     * DELETE  /recommends/:id : delete the "id" recommend.
     *
     * @param id the id of the recommend to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recommends/{id}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteRecommend(@PathVariable Long id) {
        log.debug("REST request to delete Recommend : {}", id);
        recommendRepository.delete(id);
        recommendSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/recommends?query=:query : search for the recommend corresponding
     * to the query.
     *
     * @param query the query of the recommend search 
     * @return the result of the search
     */
    @GetMapping("/_search/recommends")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public List<Recommend> searchRecommends(@RequestParam String query) {
        log.debug("REST request to search Recommends for query {}", query);
        return StreamSupport
            .stream(recommendSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }


}
