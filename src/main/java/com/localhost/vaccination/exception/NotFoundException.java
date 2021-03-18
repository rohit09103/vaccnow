/**
 * 
 */
package com.localhost.vaccination.exception;

/**
 * Exception class to thrown object now found excpetion.
 * @author rohitsharma08
 *
 */
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8920382269142996116L;
	
	/**
	 * default constructor.
	 */
	public NotFoundException() {
		super();
	}
	
	/**
	 * parameterized constructor.
	 */
	public NotFoundException(String message) {
		super(message);
	}

}
