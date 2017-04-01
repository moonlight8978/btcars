package btcars.repository;

import btcars.domain.Customer;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("select distinct customer from Customer customer left join fetch customer.cars")
    List<Customer> findAllWithEagerRelationships();

    @Query("select customer from Customer customer left join fetch customer.cars where customer.id =:id")
    Customer findOneWithEagerRelationships(@Param("id") Long id);

	@Query("select customer from Customer customer left join fetch customer.cars where customer.user.login = :login")
    Customer findOneByUser(@Param("login") String login);

}
