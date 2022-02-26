/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.Group;

/**
 * @author Shankar D
 *
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	boolean existsByName(String name);

	Optional<Group> findByName(String name);

}
