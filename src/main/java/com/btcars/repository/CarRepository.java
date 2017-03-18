package com.btcars.repository;

import com.btcars.domain.Car;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Car entity.
 */
public interface CarRepository extends JpaRepository<Car,Long> {
	public Car findByMakeAndModelAndTrimAndYear(String make, String model, String trim, Integer year);
	
	public List<Car> findByMake(String make);
	
	public List<Car> findTop5ByOrderBySoldDesc();
	
	public List<Car> findTop3ByOrderByIdDesc();
	
	@Query(value = "SELECT * FROM car ORDER BY RAND() LIMIT 0,3", nativeQuery = true)
	public List<Car> findTop3Rand();
}
