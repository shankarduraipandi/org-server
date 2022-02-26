/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.AssetCategory;

/**
 * @author Shankar D
 *
 */
@Repository
public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Long> {

	Optional<AssetCategory> findByCategory(String assetName);

}
