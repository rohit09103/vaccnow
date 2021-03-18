/**
 * 
 */
package com.localhost.vaccination.controller;

import static com.localhost.vaccination.constants.VaccinationConstants.ATLEAST_ONE_IS_EXPECTED;
import static com.localhost.vaccination.constants.VaccinationConstants.ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR;
import static com.localhost.vaccination.constants.VaccinationConstants.ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR;
import static com.localhost.vaccination.constants.VaccinationConstants.INVALID_DATES;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.exception.NotFoundException;
import com.localhost.vaccination.model.Booking;
import com.localhost.vaccination.model.BranchBookings;
import com.localhost.vaccination.service.BookingService;

/**
 * Controller to handle all booking rest apis.
 * 
 * @author rohitsharma08
 *
 */
@RestController
@RequestMapping(path = "/")
public class BookingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	@PostMapping(path = "/booking", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Booking> bookAppointment(@Valid @RequestBody Booking booking) {

		try {
			booking = bookingService.createBooking(booking);
			// case of failure in service class
			if (null != booking.getErrorMessages() && !booking.getErrorMessages().isEmpty()) {
				Booking responseBooking = new Booking();
				responseBooking.setErrorMessages(booking.getErrorMessages());
				return ResponseEntity.badRequest().body(responseBooking);
			}
			return ResponseEntity.status(CREATED).body(booking);
		} catch (Exception exception) {
			LOGGER.error("Failure occured while processing booking with exception:{} ", exception.getMessage());
			List<String> errorMesasages = new ArrayList<>();
			errorMesasages.add(ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR);
			booking = new Booking();
			booking.setErrorMessages(errorMesasages);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(booking);
		}
	}

	/**
	 * Method to fetch all the bookings for the branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/booking/branches/{branchId}/report", produces = "application/json")
	public ResponseEntity<BranchBookings> getAllBookingsForBranches(
			@PathVariable(required = true, name = "branchId") Integer branchId) {
		LOGGER.info("Invoking booking service to fetch all bookings for given branch: {}.", branchId);
		BranchBookings branchBookings = null;
		try {
			if (null == branchId)
				throw new NotFoundException("Branch Id null.");
			branchBookings = bookingService.fetchAllBookingsForBranch(branchId);
		} catch (NotFoundException foundException) {
			LOGGER.error("Booking for branchId with id:{} not found: {}", branchId, foundException.getMessage());
			return ResponseEntity.notFound().build();
		} catch (Exception exception) {
			LOGGER.error("Exception occured while fetching bookings for a branch with id:{}, exception {}", branchId,
					exception.getMessage());
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new BranchBookings(
					ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR, ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR));
		}
		return ResponseEntity.ok(branchBookings);
	}

	/**
	 * Method to fetch all the bookings for the branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/booking/report", produces = "application/json")
	public ResponseEntity<List<Booking>> getAllBookingsForDate(
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate,
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		LOGGER.info("Invoking booking service to fetch all bookings.");
		return performGetOnService(fromDate, toDate, date, StatusType.SCHEDULED);
	}
	
	/**
	 * Method to fetch all the bookings for the branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/booking/confirmed", produces = "application/json")
	public ResponseEntity<List<Booking>> getAllConfirmedBookingsForDate(
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate,
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		LOGGER.info("Invoking booking service to fetch all confirmed bookings.");
		return performGetOnService(fromDate, toDate, date, StatusType.COMPLETED);
	}

	/**
	 * @param fromDate
	 * @param toDate
	 * @param date
	 * @param statusType
	 * @return
	 */
	private ResponseEntity<List<Booking>> performGetOnService(Date fromDate, Date toDate, Date date,
			StatusType statusType) {
		List<Booking> bookings;
		try {

			List<String> errorMessages = validateParams(fromDate, toDate, date);
			if (errorMessages.isEmpty()) {
				bookings = bookingService.fetchAllBookingsForDateOrPeriod(fromDate, toDate, date, statusType);
			} else {
				bookings = new ArrayList<>();
				Booking booking = new Booking();
				booking.setErrorMessages(errorMessages);
				bookings.add(booking);
				return ResponseEntity.badRequest().body(bookings);
			}
		} catch (Exception exception) {
			LOGGER.error("Exception occured while fetching bookings for a date, exception {}", exception.getMessage());
			bookings = new ArrayList<>();
			List<String> errorMessages = new ArrayList<>();
			errorMessages.add(ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR);
			Booking booking = new Booking();
			booking.setErrorMessages(errorMessages);
			bookings.add(booking);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(bookings);
		}
		return ResponseEntity.ok(bookings);
	}

	/**
	 * Method to validate if correct date params are passed or not.
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param date
	 */
	private List<String> validateParams(Date fromDate, Date toDate, Date date) {
		List<String> errorMessages = new ArrayList<>();
		boolean isInvalidPair = (null != fromDate || null != toDate) && null != date;
		boolean isAllPresent = null != fromDate && null != toDate && null != date;
		boolean isAllAbsent = null == fromDate && null == toDate && null == date;
		if (isAllPresent || isAllAbsent || isInvalidPair) {
			errorMessages.add(ATLEAST_ONE_IS_EXPECTED);
		}
		if (null != fromDate && null != toDate && fromDate.after(toDate)) {
			errorMessages.add(INVALID_DATES);
		}
		return errorMessages;
	}

	

}
