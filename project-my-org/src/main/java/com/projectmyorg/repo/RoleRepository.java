package com.projectmyorg.repo;

import java.util.Optional;

import com.projectmyorg.domain.enums.ERole;
import com.projectmyorg.domain.masters.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shankar D
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
    Optional<Role> findByName(ERole name);
    
}
