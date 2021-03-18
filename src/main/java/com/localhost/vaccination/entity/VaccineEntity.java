/**
 * 
 */
package com.localhost.vaccination.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rohit
 *
 */
@Entity
@Table(name = "VACCINES")
public class VaccineEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7918884049226670149L;

	@Id
	@GeneratedValue
	@Column(name = "VACCINE_ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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

	@Override
	public String toString() {
		return String.format("VaccineEntity [id=%s, name=%s]", id, name);
	}

}
