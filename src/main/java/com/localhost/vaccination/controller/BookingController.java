/**
 * 
 */
package com.localhost.vaccination.controller;

import static com.localhost.vaccination.constants.VaccinationConstants.ATLEAST_ONE_IS_EXPECTED;
import static com.localhost.vaccination.constants.VaccinationConstants.INVALID_DATES;
import static org.springframework.http.HttpStatus.CREATED;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller to handle all booking rest apis.
 * 
 * @author rohitsharma08
 *
 */
@RestController
@RequestMapping(path = "/vaccnow")
@Api(value = "/vaccnow")
public class BookingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	@PostMapping(path = "/booking")
	@ApiOperation(value = "Books a appointment for a vaccine in a branch.")
	public ResponseEntity<Booking> bookAppointment(@Valid @RequestBody Booking booking) {

		booking = bookingService.createBooking(booking);
		if (null != booking.getErrorMessages() && !booking.getErrorMessages().isEmpty()) {
			Booking responseBooking = new Booking();
			responseBooking.setErrorMessages(booking.getErrorMessages());
			return ResponseEntity.badRequest().body(responseBooking);
		}
		return ResponseEntity.status(CREATED).body(booking);
	}

	/**
	 * Method to fetch all the bookings for the branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/booking/branches/{branchId}/report")
	@ApiOperation(value = "Fetchs all bookings for a perticular branch.")
	public ResponseEntity<BranchBookings> getAllBookingsForBranches(
			@PathVariable(required = true, name = "branchId") Integer branchId) {
		LOGGER.info("Invoking booking service to fetch all bookings for given branch: {}.", branchId);
		BranchBookings branchBookings = null;
		if (null == branchId)
			throw new NotFoundException("Branch Id null.");
		branchBookings = bookingService.fetchAllBookingsForBranch(branchId);
		return ResponseEntity.ok(branchBookings);
	}

	/**
	 * Method to fetch all the bookings for the branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/booking/report")
	@ApiOperation(value = "Fetch all bookings on a specific date or in a period.")
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
	@ApiOperation(value = "Get all confirmed vaccinations for a specific date or in a period.")
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
		boolean isOnlyOnePresent = (null == fromDate && null != toDate) || (null != fromDate && null == toDate);
		boolean isBothTrue = isInvalidPair || isAllPresent;
		boolean isBothConditionTrue = isAllAbsent || isOnlyOnePresent;
		if (isBothTrue || isBothConditionTrue) {
			errorMessages.add(ATLEAST_ONE_IS_EXPECTED);
		}
		if (null != fromDate && null != toDate && fromDate.after(toDate)) {
			errorMessages.add(INVALID_DATES);
		}
		return errorMessages;
	}

}
