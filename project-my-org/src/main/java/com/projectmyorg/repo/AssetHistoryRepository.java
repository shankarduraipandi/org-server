/**
 * 
 */
package com.projectmyorg.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.AssetHistory;
import com.projectmyorg.domain.masters.Asset;

/**
 * @author Shankar D
 *
 */
@Repository
public interface AssetHistoryRepository extends JpaRepository<AssetHistory, Long> {

	Page<AssetHistory> findByAsset(Asset asset, Pageable pageable);

}
