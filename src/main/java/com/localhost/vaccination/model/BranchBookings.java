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
 * @author rohitsharma08
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
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


	@Override
	public String toString() {
		return String.format("BranchBookings [branchId=%s, location=%s, name=%s, phoneNumber=%s, bookings=%s]",
				branchId, location, name, phoneNumber, bookings);
	}

}
