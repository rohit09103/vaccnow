/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * Model to hold all availability data for a branch
 * @author Rohit
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class Availability implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2614082639409019051L;
	private Integer branchId;
	private String branchName;
	private String branchLocation;
	private String branchPhone;
	private List<AvailabilityDatesAndSlot> dates;
	private String errorMessageCode;
	private String errorMessageDescription;
	
	/**
	 * default constructor
	 */
	public Availability() {
		//empty constructor.
	}
	
	/**
	 * @param branchId
	 * @param branchName
	 * @param branchLocation
	 */
	public Availability(Integer branchId, String branchName, String branchLocation, String branchPhone) {
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchLocation = branchLocation;
		this.branchPhone = branchPhone;
	}

	/**
	 * @param errorMessageCode
	 * @param errorMessageDescription
	 */
	public Availability(String errorMessageCode, String errorMessageDescription) {
		this.errorMessageCode = errorMessageCode;
		this.errorMessageDescription = errorMessageDescription;
	}


}
