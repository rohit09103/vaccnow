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
 * Model to contain dates and slots timings. slots are 15min window.
 * @author Rohit
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class AvailabilityDatesAndSlot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8189162207312849024L;
	private Date date;
	private List<String> slots;
	
	/**
	 * default constructor
	 */
	public AvailabilityDatesAndSlot() {
		//empty constructor
	}

}
