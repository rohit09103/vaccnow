/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * Model to hold data related to vaccination center.
 * @author Rohit
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class Branch implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6429496531271202775L;
	private Integer branchId;
	private String location;
	private String name;
	private String phoneNumber;
	private List<Date> availableSlots;
	private String errorMessageCode;
	private String errorMessageDescription;
	private List<Vaccine> vaccines;
	/**
	 * Default constructor
	 */
	public Branch() {
		
	}
	
	/**
	 * @param errorMessageCode
	 * @param errorMessageDescription
	 */
	public Branch(String errorMessageCode, String errorMessageDescription) {
		this.errorMessageCode = errorMessageCode;
		this.errorMessageDescription = errorMessageDescription;
	}
	
}
