/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.JobLocation;

/**
 * @author Shankar D
 *
 */
@Repository
public interface JobLocationRepository extends JpaRepository<JobLocation, Long> {

	Optional<JobLocation> findByUuid(String uuid);

}
