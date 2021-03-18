/**
 * 
 */
package com.localhost.vaccination.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.localhost.vaccination.enums.PaymentMethodType;
import com.localhost.vaccination.enums.StatusType;

/**
 * Entity to hold booking data.
 * @author rohitsharma08
 *
 */
@Entity
@Table(name = "BOOKINGS")
public class BookingEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8616631166063958877L;

	@Id
	@GeneratedValue
	@Column(name = "BOOKING_ID")
	private Integer id;
	
	@OneToOne()
	private BranchEntity branch;
	
	@OneToOne()
	private VaccineEntity vaccine;
	
	@Column(name = "PAYMENT_TYPE")
	@Enumerated(EnumType.STRING)
	private PaymentMethodType paymentMethodType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusType statusType;
	
	@Column(name = "TIMESTAMP")
	private Date date;
	
	@Column(name = "PAYMENT_METHOD_ID")
	private String paymentMethodId;
	
	@Column(name = "CREDIT_CARD_NUMBER")
	private String creditCardNumber;
	
	@Column(name = "CVV")
	private String ccv;
	
	@Column(name = "EXPIRY")
	private String expiry;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the branch
	 */
	public BranchEntity getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(BranchEntity branch) {
		this.branch = branch;
	}

	/**
	 * @return the vaccine
	 */
	public VaccineEntity getVaccine() {
		return vaccine;
	}

	/**
	 * @param vaccine the vaccine to set
	 */
	public void setVaccine(VaccineEntity vaccine) {
		this.vaccine = vaccine;
	}

	/**
	 * @return the paymentMethodType
	 */
	public PaymentMethodType getPaymentMethodType() {
		return paymentMethodType;
	}

	/**
	 * @param paymentMethodType the paymentMethodType to set
	 */
	public void setPaymentMethodType(PaymentMethodType paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
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
	 * @return the paymentMethodId
	 */
	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	/**
	 * @param paymentMethodId the paymentMethodId to set
	 */
	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	/**
	 * @return the creditCardNumber
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * @param creditCardNumber the creditCardNumber to set
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * @return the ccv
	 */
	public String getCcv() {
		return ccv;
	}

	/**
	 * @param ccv the ccv to set
	 */
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	/**
	 * @return the expiry
	 */
	public String getExpiry() {
		return expiry;
	}

	/**
	 * @param expiry the expiry to set
	 */
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
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
				"BookingEntity [id=%s, branch=%s, vaccine=%s, paymentMethodType=%s, statusType=%s, date=%s, amount=%s]",
				id, branch, vaccine, paymentMethodType, statusType, date, amount);
	}
	
}
