/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.JobRole;

/**
 * @author Shankar D
 *
 */
@Repository
public interface JobRoleRepository extends JpaRepository<JobRole, Long> {

	Optional<JobRole> findByRoleName(String jobRole);

}
