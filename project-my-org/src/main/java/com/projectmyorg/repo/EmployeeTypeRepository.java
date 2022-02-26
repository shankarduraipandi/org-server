/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.EmployeeType;

/**
 * @author Shankar D
 *
 */
@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {

	Optional<EmployeeType> findByEmpType(String empType);

}
