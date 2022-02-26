/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.AssetRequest;
import com.projectmyorg.domain.enums.Status;

/**
 * @author Shankar D
 *
 */
@Repository
public interface AssetRequestRepository extends JpaRepository<AssetRequest, Long> {

	Optional<AssetRequest> findByUuid(String uuid);

	Page<AssetRequest> findByRequestedByAndStatus(String loggedInUserName, Status statusE, Pageable pageable);

	Page<AssetRequest> findByStatus(Status statusE, Pageable pageable);

}
