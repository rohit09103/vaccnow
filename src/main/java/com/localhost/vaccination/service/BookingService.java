/**
 * 
 */
package com.localhost.vaccination.service;

import java.util.Date;
import java.util.List;

import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.model.Booking;
import com.localhost.vaccination.model.BranchBookings;

/**
 * Service class to perform business logic and return the booking status.
 * 
 * @author rohitsharma08
 *
 */
public interface BookingService {

	/**
	 * Create a booking for the branch and vaccine.
	 * 
	 * @param booking
	 * @return
	 */
	public Booking createBooking(Booking booking);

	/**
	 * Method to fetch all bookings corresponding to one branch.
	 * 
	 * @param branchId
	 * @return
	 */
	 BranchBookings fetchAllBookingsForBranch(Integer branchId);

	/**
	 * Fetch all bookings for a specific period or date.
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param date
	 * @return
	 */
	 List<Booking> fetchAllBookingsForDateOrPeriod(Date fromDate, Date toDate, Date date, StatusType statusType);

}
