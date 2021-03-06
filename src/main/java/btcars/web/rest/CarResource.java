package btcars.web.rest;

import com.codahale.metrics.annotation.Timed;
import btcars.domain.Car;

import btcars.repository.CarRepository;
import btcars.repository.search.CarSearchRepository;
import btcars.security.AuthoritiesConstants;
import btcars.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
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
 * REST controller for managing Car.
 */
@RestController
@RequestMapping("/api")
public class CarResource {

    private final Logger log = LoggerFactory.getLogger(CarResource.class);

    private static final String ENTITY_NAME = "car";
        
    private final CarRepository carRepository;

    private final CarSearchRepository carSearchRepository;

    public CarResource(CarRepository carRepository, CarSearchRepository carSearchRepository) {
        this.carRepository = carRepository;
        this.carSearchRepository = carSearchRepository;
    }

    /**
     * POST  /cars : Create a new car.
     *
     * @param car the car to create
     * @return the ResponseEntity with status 201 (Created) and with body the new car, or with status 400 (Bad Request) if the car has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cars")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) throws URISyntaxException {
        log.debug("REST request to save Car : {}", car);
        if (car.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new car cannot already have an ID")).body(null);
        }
        Car result = carRepository.save(car);
        carSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/cars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cars : Updates an existing car.
     *
     * @param car the car to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated car,
     * or with status 400 (Bad Request) if the car is not valid,
     * or with status 500 (Internal Server Error) if the car couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cars")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Car> updateCar(@Valid @RequestBody Car car) throws URISyntaxException {
        log.debug("REST request to update Car : {}", car);
        if (car.getId() == null) {
            return createCar(car);
        }
        Car result = carRepository.save(car);
        carSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, car.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cars : get all the cars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cars in body
     */
    @GetMapping("/cars")
    @Timed
    public List<Car> getAllCars() {
        log.debug("REST request to get all Cars");
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    /**
     * GET  /cars/:id : get the "id" car.
     *
     * @param id the id of the car to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the car, or with status 404 (Not Found)
     */
    @GetMapping("/cars/{id}")
    @Timed
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        log.debug("REST request to get Car : {}", id);
        Car car = carRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(car));
    }

    /**
     * DELETE  /cars/:id : delete the "id" car.
     *
     * @param id the id of the car to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cars/{id}")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        log.debug("REST request to delete Car : {}", id);
        carRepository.delete(id);
        carSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/cars?query=:query : search for the car corresponding
     * to the query.
     *
     * @param query the query of the car search 
     * @return the result of the search
     */
    @GetMapping("/_search/cars")
    @Timed
    public List<Car> searchCars(@RequestParam String query) {
        log.debug("REST request to search Cars for query {}", query);
        return StreamSupport
            .stream(carSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
		
	/* ----- Get Specific company's Cars ----- */
	@GetMapping(value = "/cars", params = "company")
	@Timed
    public ResponseEntity<List<Car>> getCompanyCars(@RequestParam String company) {
        log.debug("REST request to search Cars for query by company", company);
		String make = null;
		if (company.equals("lamborghini"))
			make = "Lamborghini";
		else if (company.equals("porsche"))
			make = "Porsche";
		else if (company.equals("ferrari"))
			make = "Ferrari";
		else if (company.equals("rollsroyce"))
			make = "Rolls-Royce";
        else
            return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
		List<Car> cars = carRepository.findByMake(make);
        return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
    }

    /* ----- Get Car By Category ----- */
    @GetMapping(value = "/cars", params = "category")
    @Timed
    public List<Car> getCategory(@RequestParam String category) {
        log.debug("REST request to search Cars for query by category", category);
        List<Car> cars = null;
        if (category.equals("hot"))
            cars = carRepository.findTop5ByOrderBySoldDesc();
        else if (category.equals("new"))
            cars = carRepository.findTop3ByOrderByIdDesc();
        else if (category.equals("random"))
            cars = carRepository.findTop3Rand();
        return cars;
    }

}
