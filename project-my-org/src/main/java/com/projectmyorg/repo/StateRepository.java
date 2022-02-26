/**
 * 
 */
package com.projectmyorg.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.Country;
import com.projectmyorg.domain.masters.State;

/**
 * @author Shankar D
 *
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	Optional<State> findByStateNameAndCountry(String state, Country country);

	List<State> findByCountry(Country country);

}
