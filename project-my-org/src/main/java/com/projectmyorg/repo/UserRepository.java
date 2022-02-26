package com.projectmyorg.repo;

import java.util.Optional;

import com.projectmyorg.domain.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shankar D
 *
 */
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

	Optional<AppUser> findByEmailOrUsername(String email, String username);

	boolean existsByUsername(String username);

	boolean existsByUuid(String uuid);
    
}
