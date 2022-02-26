/**
 * 
 */
package com.projectmyorg.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.City;
import com.projectmyorg.domain.masters.State;

/**
 * @author Shankar D
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	Optional<City> findByCityNameAndState(String city, State state);

	List<City> findByState(State state);

}
