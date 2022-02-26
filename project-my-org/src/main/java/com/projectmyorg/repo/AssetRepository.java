/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.Asset;
import com.projectmyorg.domain.masters.AssetCategory;

/**
 * @author Shankar D
 *
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

	Optional<Asset> findByAssetNo(String assetNo);

	Page<Asset> findByAssetCategory(AssetCategory assetCategory, Pageable pageable);

}
