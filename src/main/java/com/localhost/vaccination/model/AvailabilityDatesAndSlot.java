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
 * Model to contain dates and slots timings. slots are 15min window.
 * @author Rohit
 *
 */
@JsonInclude(value = Include.NON_NULL)
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

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the slots
	 */
	public List<String> getSlots() {
		return slots;
	}

	/**
	 * @param slots the slots to set
	 */
	public void setSlots(List<String> slots) {
		this.slots = slots;
	}
	
	
}
