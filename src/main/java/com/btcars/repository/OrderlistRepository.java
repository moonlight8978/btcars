package com.btcars.repository;

import com.btcars.domain.Orderlist;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Orderlist entity.
 */
@SuppressWarnings("unused")
public interface OrderlistRepository extends JpaRepository<Orderlist,Long> {

    @Query("select distinct orderlist from Orderlist orderlist left join fetch orderlist.cars")
    List<Orderlist> findAllWithEagerRelationships();

    @Query("select orderlist from Orderlist orderlist left join fetch orderlist.cars where orderlist.id =:id")
    Orderlist findOneWithEagerRelationships(@Param("id") Long id);

}
