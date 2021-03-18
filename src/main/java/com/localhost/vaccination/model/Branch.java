/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Model to hold data related to vaccination center.
 * @author Rohit
 *
 */
@JsonInclude(value = Include.NON_NULL)
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



	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the availableSlots
	 */
	public List<Date> getAvailableSlots() {
		return availableSlots;
	}

	/**
	 * @param availableSlots the availableSlots to set
	 */
	public void setAvailableSlots(List<Date> availableSlots) {
		this.availableSlots = availableSlots;
	}

	/**
	 * @return the errorMessageCode
	 */
	public String getErrorMessageCode() {
		return errorMessageCode;
	}

	/**
	 * @param errorMessageCode the errorMessageCode to set
	 */
	public void setErrorMessageCode(String errorMessageCode) {
		this.errorMessageCode = errorMessageCode;
	}

	/**
	 * @return the errorMessageDescription
	 */
	public String getErrorMessageDescription() {
		return errorMessageDescription;
	}

	/**
	 * @param errorMessageDescription the errorMessageDescription to set
	 */
	public void setErrorMessageDescription(String errorMessageDescription) {
		this.errorMessageDescription = errorMessageDescription;
	}

	/**
	 * @return the vaccines
	 */
	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	/**
	 * @param vaccines the vaccines to set
	 */
	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}
	
	
	
}
