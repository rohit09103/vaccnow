/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Rohit
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class Vaccine implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3908467921113815968L;
	private Integer vaccineId;
	private String name;
	private Integer quantity;
	
	/**
	 * Default constructor for vaccine model.
	 */
	public Vaccine() {
		// Auto-generated constructor stub
	}

	/**
	 * @return the vaccineId
	 */
	public Integer getVaccineId() {
		return vaccineId;
	}

	/**
	 * @param vaccineId the vaccineId to set
	 */
	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
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
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
