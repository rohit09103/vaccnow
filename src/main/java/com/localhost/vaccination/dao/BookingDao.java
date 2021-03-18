/**
 * 
 */
package com.localhost.vaccination.dao;

import java.sql.Date;
import java.util.List;

import com.localhost.vaccination.entity.BookingEntity;
import com.localhost.vaccination.enums.StatusType;

/**
 * Interface to expose methods to book a slot for vaccination.
 * 
 * @author rohitsharma08
 *
 */
public interface BookingDao {
	
	/**
	 * Method to save booking into DB.
	 * @param bookingEntity
	 * @return
	 */
	public BookingEntity saveBooking(BookingEntity bookingEntity);
	
	/**
	 * Method to fetch all bookings of vaccination for branchId.
	 * @param branchId
	 * @return
	 */
	public List<BookingEntity> fetchAllBookingsForBranchId(Integer branchId);
	
	/**
	 * Fetch all bookings for given dates.
	 * @param fromDate
	 * @param toDate
	 * @param date
	 * @return
	 */
	public List< BookingEntity> fetchAllBookingsForDayOrPeriod(Date fromDate, Date toDate, Date date,StatusType statusType);
	
	

}
