package btcars.repository;

import btcars.domain.Recommend;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Recommend entity.
 */
@SuppressWarnings("unused")
public interface RecommendRepository extends JpaRepository<Recommend,Long> {

}
