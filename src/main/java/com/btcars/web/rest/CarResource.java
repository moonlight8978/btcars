package com.btcars.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.btcars.domain.Car;

import com.btcars.repository.CarRepository;
import org.springframework.http.*;
import com.btcars.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Car.
 */
@RestController
@RequestMapping("/api")
public class CarResource {

    private final Logger log = LoggerFactory.getLogger(CarResource.class);

    private static final String ENTITY_NAME = "car";
        
    private final CarRepository carRepository;

    public CarResource(CarRepository carRepository) {
        this.carRepository = carRepository;
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
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) throws URISyntaxException {
        log.debug("REST request to save Car : {}", car);
        if (car.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new car cannot already have an ID")).body(null);
        }
        Car result = carRepository.save(car);
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
    public ResponseEntity<Car> updateCar(@Valid @RequestBody Car car) throws URISyntaxException {
        log.debug("REST request to update Car : {}", car);
        if (car.getId() == null) {
            return createCar(car);
        }
        Car result = carRepository.save(car);
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
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        log.debug("REST request to delete Car : {}", id);
        carRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
	
	/* ----- Get Top6 Sold Cars ----- */
	@GetMapping("/cars/hot")
	@Timed
	public ResponseEntity<List<Car>> getTop5Sold() {
		List<Car> list = carRepository.findTop5ByOrderBySoldDesc();
		if (list.isEmpty())
			return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
	}
	
	/* ----- Get Top3 New Cars ----- */
	@GetMapping("/cars/new")
	@Timed
	public ResponseEntity<List<Car>> getTop3New() {
		List<Car> list = carRepository.findTop3ByOrderByIdDesc();
		if (list.isEmpty())
			return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
	}
	
	/* ----- Get Top3 Random Cars ----- */
	@GetMapping("/cars/random")
	@Timed
	public ResponseEntity<List<Car>> getTop3Rand() {
		List<Car> list = carRepository.findTop3Rand();
		if (list.isEmpty())
			return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
	}
	
	/* ----- Get Lamborghini Cars ----- */
	@GetMapping("/cars/lamborghini")
	@Timed
	public ResponseEntity<List<Car>> getLamborghini() {
		List<Car> list = carRepository.findByMake("Lamborghini");
		if (list.isEmpty())
			return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
	}
	
	/* ----- Get Ferrari Cars ----- */
	@GetMapping("/cars/ferrari")
	@Timed
	public ResponseEntity<List<Car>> getFerrari() {
		List<Car> list = carRepository.findByMake("Ferrari");
		if (list.isEmpty())
			return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
	}
	
	/* ----- Get Porsche Cars ----- */
	@GetMapping("/cars/porsche")
	@Timed
	public ResponseEntity<List<Car>> getPorsche() {
		List<Car> list = carRepository.findByMake("Porsche");
		if (list.isEmpty())
			return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
	}
	
	/* ----- Get Rolls-Royces Cars ----- */
	@GetMapping("/cars/rollsroyce")
	@Timed
	public ResponseEntity<List<Car>> getRollsRoyce() {
		List<Car> list = carRepository.findByMake("Rolls-Royce");
		if (list.isEmpty())
			return new ResponseEntity<List<Car>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Car>>(list, HttpStatus.OK);
	}

}
