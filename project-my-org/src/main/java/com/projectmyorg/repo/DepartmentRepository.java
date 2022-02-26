/**
 * 
 */
package com.projectmyorg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.Department;

/**
 * @author Shankar D
 *
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Optional<Department> findByDept(String dept);

}
