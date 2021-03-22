/**
 * 
 */
package com.localhost.vaccination.model;

import static com.localhost.vaccination.constants.VaccinationConstants.INVALID_AMOUNT_MESSAGE;
import static com.localhost.vaccination.constants.VaccinationConstants.INVALID_CC_MESSAGE;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.localhost.vaccination.enums.PaymentMethodType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rohitsharma08
 *
 */
@JsonAutoDetect
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class PaymentMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184193367504581862L;
	private Integer id;
	private String payemntMethodId;
	@NotNull
	private PaymentMethodType paymentMethodType;
	@NotNull
	@Digits(integer = 3, fraction = 2, message = INVALID_AMOUNT_MESSAGE)
	private Double amount;
	@CreditCardNumber(message = INVALID_CC_MESSAGE)
	private String creditCardNumber;
	private Integer ccv;
	private String expiryDate;

	/**
	 * default constructor
	 */
	public PaymentMethod() {
		// empty constructor
	}

	/**
	 * @param payemntMethodId
	 * @param paymentMethodType
	 * @param amount
	 * @param creditCardNumber
	 * @param ccv
	 * @param expiryDate
	 */
	public PaymentMethod(String payemntMethodId, PaymentMethodType paymentMethodType, Double amount,
			String creditCardNumber, Integer ccv, String expiryDate) {
		this.payemntMethodId = payemntMethodId;
		this.paymentMethodType = paymentMethodType;
		this.amount = amount;
		this.creditCardNumber = creditCardNumber;
		this.ccv = ccv;
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return String.format("PaymentMethod [id=%s, paymentMethodType=%s, amount=%s]", id, paymentMethodType, amount);
	}

}
