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

/**
 * Class to hold booking request.
 * @author rohitsharma08
 *
 */
@JsonAutoDetect
@JsonInclude(value = Include.NON_NULL)
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
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * @return the bookingSlot
	 */
	public String getBookingSlot() {
		return bookingSlot;
	}
	/**
	 * @param bookingSlot the bookingSlot to set
	 */
	public void setBookingSlot(String bookingSlot) {
		this.bookingSlot = bookingSlot;
	}
	/**
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
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
	 * @return the errorMessages
	 */
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	/**
	 * @param errorMessages the errorMessages to set
	 */
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	/**
	 * @return the bookingId
	 */
	public Integer getBookingId() {
		return bookingId;
	}
	/**
	 * @param bookingId the bookingId to set
	 */
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	/**
	 * @return the statusType
	 */
	public StatusType getStatusType() {
		return statusType;
	}
	/**
	 * @param statusType the statusType to set
	 */
	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return String.format(
				"Booking [bookingId=%s, branchId=%s, vaccineId=%s, bookingDate=%s, bookingSlot=%s, paymentMethod=%s, name=%s, errorMessages=%s, errorMessage=%s, getBranchId()=%s, getVaccineId()=%s, getBookingDate()=%s, getBookingSlot()=%s, getPaymentMethod()=%s, getName()=%s, getErrorMessages()=%s, getBookingId()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				bookingId, branchId, vaccineId, bookingDate, bookingSlot, paymentMethod, name, errorMessages,
				errorMessage, getBranchId(), getVaccineId(), getBookingDate(), getBookingSlot(), getPaymentMethod(),
				getName(), getErrorMessages(), getBookingId(), getClass(), hashCode(), super.toString());
	}
	
}
