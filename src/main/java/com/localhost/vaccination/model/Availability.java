/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Model to hold all availability data for a branch
 * @author Rohit
 *
 */
@JsonInclude(value = Include.NON_NULL)
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
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * @return the branchLocation
	 */
	public String getBranchLocation() {
		return branchLocation;
	}

	/**
	 * @param branchLocation the branchLocation to set
	 */
	public void setBranchLocation(String branchLocation) {
		this.branchLocation = branchLocation;
	}

	/**
	 * @return the dates
	 */
	public List<AvailabilityDatesAndSlot> getDates() {
		return dates;
	}

	/**
	 * @param dates the dates to set
	 */
	public void setDates(List<AvailabilityDatesAndSlot> dates) {
		this.dates = dates;
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
	 * @return the branchPhone
	 */
	public String getBranchPhone() {
		return branchPhone;
	}

	/**
	 * @param branchPhone the branchPhone to set
	 */
	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}

}
