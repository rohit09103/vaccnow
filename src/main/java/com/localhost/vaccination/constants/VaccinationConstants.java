/**
 * 
 */
package com.localhost.vaccination.constants;

/**
 * Class to hold all constants
 * 
 * @author Rohit
 *
 */
public final class VaccinationConstants {
	
	// default constructor.
	private VaccinationConstants() {
		// empty constructor
	}

	public static final String ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR = "InternalServerError";
	public static final String ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR = "Something went wrong, please try after sometime.";
	public static final int NUMERIC_ZERO = 0;
	public static final int NUMERIC_ONE = 1;
	public static final String INVALID_AMOUNT_MESSAGE = "Your amount should only have 2 decimal places.";
	public static final String INVALID_CC_MESSAGE = "Invalid CC";
	public static final String BOOKING_SLOT_NOT_AVAILABLE = "Booking slot provided is not available.";
	public static final String VACCINE_IS_NOT_AVAILABLE = "Vaccine is not available in provided branch.";
	public static final String ATLEAST_ONE_IS_EXPECTED = "Atleast/atmost one of the fromDate/toDate or date is required.";
	public static final String INVALID_DATES = "'from' date can not be after 'to' Date.";
	public static final String NOT_FOUND_EXCEPTION_MESSAGE = "NotFoundError";
	public static final String NOT_FOUND_EXCEPTION_DESCRIPTION = "The resource you are requesting is not available.";
	public static final String BASE_PACKAGE = "com.localhost.vaccination";
}
