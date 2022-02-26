/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.OrgService;

/**
 * @author Shankar D
 *
 */
@Repository
public interface OrgServiceRepository extends JpaRepository<OrgService, Long> {

	Optional<OrgService> findByName(String serviceName);

}
