/**
 * 
 */
package com.projectmyorg.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmyorg.domain.masters.Node;
import com.projectmyorg.domain.masters.OrgService;

/**
 * @author Shankar D
 *
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {

	Optional<Node> findByNameAndOrgService(String name, OrgService service);

	List<Node> findByOrgService(OrgService service);

}
