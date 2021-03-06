package com.projectmyorg.domain.masters;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.projectmyorg.domain.enums.ERole;

/**
 * @author Shankar D
 *
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
	@Column(length = 25)
	private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
  
}
