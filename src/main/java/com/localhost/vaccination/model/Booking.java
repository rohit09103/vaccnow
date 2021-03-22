/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.localhost.vaccination.enums.StatusType;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to hold booking request.
 * @author rohitsharma08
 *
 */
@JsonAutoDetect
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class Booking implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 438952107730235672L;
	
	private Integer bookingId;
	@NotNull
	private Integer branchId;
	@NotNull
	private Integer vaccineId;
	@NotNull
	private Date bookingDate;
	@NotNull
	private String bookingSlot;
	@NotNull
	private PaymentMethod paymentMethod;
	@NotNull
	private String name;
	private String email;
	private List<String> errorMessages;
	private String errorMessage;
	private StatusType statusType;

	@Override
	public String toString() {
		return String.format(
				"Booking [bookingId=%s, branchId=%s, vaccineId=%s, bookingDate=%s, bookingSlot=%s, paymentMethod=%s, name=%s, errorMessages=%s, errorMessage=%s, getBranchId()=%s, getVaccineId()=%s, getBookingDate()=%s, getBookingSlot()=%s, getPaymentMethod()=%s, getName()=%s, getErrorMessages()=%s, getBookingId()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				bookingId, branchId, vaccineId, bookingDate, bookingSlot, paymentMethod, name, errorMessages,
				errorMessage, getBranchId(), getVaccineId(), getBookingDate(), getBookingSlot(), getPaymentMethod(),
				getName(), getErrorMessages(), getBookingId(), getClass(), hashCode(), super.toString());
	}
	
}
