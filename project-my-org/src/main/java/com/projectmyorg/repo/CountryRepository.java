/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.Country;

/**
 * @author Shankar D
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	Optional<Country> findByCountryName(String country);

}
