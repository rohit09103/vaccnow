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

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rohit
 *
 */
@Entity
@Table(name = "VACCINES")
@Getter
@Setter
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


	@Override
	public String toString() {
		return String.format("VaccineEntity [id=%s, name=%s]", id, name);
	}

}
