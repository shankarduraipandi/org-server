/**
 * 
 */
package com.projectmyorg.domain.masters;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * @author Shankar D
 *
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "name", columnDefinition = "varchar(75)")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "groups_nodes", joinColumns = {
			@JoinColumn(name = "groups_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "nodes_id", referencedColumnName = "id") })
	private Set<Node> nodes = new HashSet<>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the nodes
	 */
	public Set<Node> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
