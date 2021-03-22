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

import lombok.Getter;
import lombok.Setter;

/**
 * Entity to hold booking data.
 * @author rohitsharma08
 *
 */
@Entity
@Table(name = "BOOKINGS")
@Getter
@Setter
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

	@Override
	public String toString() {
		return String.format(
				"BookingEntity [id=%s, branch=%s, vaccine=%s, paymentMethodType=%s, statusType=%s, date=%s, amount=%s]",
				id, branch, vaccine, paymentMethodType, statusType, date, amount);
	}
	
}
