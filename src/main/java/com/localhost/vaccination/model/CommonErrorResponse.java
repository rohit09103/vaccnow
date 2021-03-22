/**
 * 
 */
package com.localhost.vaccination.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to have fields for common error messages.
 * 
 * @author rohitsharma08
 *
 */
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class CommonErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5683811713249368851L;
	private String errorMessage;
	private String errorDescription;
	private int statusCode;

	/**
	 * default constructor
	 */
	public CommonErrorResponse() {
		// empty constructor
	}

	/**
	 * @param errorMessage
	 * @param errorDescription
	 * @param statusCode
	 */
	public CommonErrorResponse(String errorMessage, String errorDescription, int statusCode) {
		this.errorMessage = errorMessage;
		this.errorDescription = errorDescription;
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return String.format("CommonErrorResponse [errorMessage=%s, errorDescription=%s, statusCode=%s]", errorMessage,
				errorDescription, statusCode);
	}

}
