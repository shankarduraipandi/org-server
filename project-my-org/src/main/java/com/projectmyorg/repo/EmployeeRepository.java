/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.Employee;

/**
 * @author Shankar D
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByUuid(String uuid);

	boolean existsByEmail(String email);

}
