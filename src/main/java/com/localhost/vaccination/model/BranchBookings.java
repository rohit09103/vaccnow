/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author rohitsharma08
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class BranchBookings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1029299637248414031L;
	private Integer branchId;
	private String location;
	private String name;
	private String phoneNumber;
	private List<Booking> bookings;
	private String errorMessageCode;
	private String errorMessageDescription;

	/**
	 * default constructor
	 */
	public BranchBookings() {
		// empty constructor
	}

	/**
	 * @param errorMessageCode
	 * @param errorMessageDescription
	 */
	public BranchBookings(String errorMessageCode, String errorMessageDescription) {
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
	 * @return the bookings
	 */
	public List<Booking> getBookings() {
		return bookings;
	}

	/**
	 * @param bookings the bookings to set
	 */
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
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

	@Override
	public String toString() {
		return String.format("BranchBookings [branchId=%s, location=%s, name=%s, phoneNumber=%s, bookings=%s]",
				branchId, location, name, phoneNumber, bookings);
	}

}
