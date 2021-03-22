/**
 * 
 */
package com.localhost.vaccination.exception.handler;

import static com.localhost.vaccination.constants.VaccinationConstants.ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR;
import static com.localhost.vaccination.constants.VaccinationConstants.ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR;
import static com.localhost.vaccination.constants.VaccinationConstants.NOT_FOUND_EXCEPTION_DESCRIPTION;
import static com.localhost.vaccination.constants.VaccinationConstants.NOT_FOUND_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.localhost.vaccination.exception.NotFoundException;
import com.localhost.vaccination.model.CommonErrorResponse;

/**
 * Class to handle all exceptions across all controllers.
 * 
 * @author rohitsharma08
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

	/**
	 * Method to handle not found exception.
	 * 
	 * @param notFoundException
	 * @return
	 */
	public ResponseEntity<CommonErrorResponse> handleNotFoundException(NotFoundException notFoundException) {
		LOGGER.error("Error occured while processing your resource with exception: {}",notFoundException.getMessage());
		CommonErrorResponse commonErrorResponse = new CommonErrorResponse(NOT_FOUND_EXCEPTION_MESSAGE,
				NOT_FOUND_EXCEPTION_DESCRIPTION, NOT_FOUND.value());
		return ResponseEntity.status(NOT_FOUND).body(commonErrorResponse);
	}

	/**
	 * Method to handle all generic exception.
	 * 
	 * @param exception
	 * @return
	 */
	public ResponseEntity<CommonErrorResponse> handleException(Exception exception) {
		LOGGER.error("Error occured while executing your rest request with exception: {}",exception.getMessage());
		CommonErrorResponse commonErrorResponse = new CommonErrorResponse(ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR,
				ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(commonErrorResponse);
	}

}
