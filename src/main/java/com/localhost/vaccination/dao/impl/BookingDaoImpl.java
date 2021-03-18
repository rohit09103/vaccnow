/**
 * 
 */
package com.localhost.vaccination.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localhost.vaccination.dao.BookingDao;
import com.localhost.vaccination.entity.BookingEntity;
import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.repository.BookingRepository;

/**
 * Class to perform db update for a booking and fetch bookings.
 * 
 * @author rohitsharma08
 *
 */
@Component
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public BookingEntity saveBooking(BookingEntity bookingEntity) {
		return bookingRepository.save(bookingEntity);
	}

	@Override
	public List<BookingEntity> fetchAllBookingsForBranchId(Integer branchId) {
		return bookingRepository.findAll().stream()
				.filter(booking -> booking.getBranch().getId().compareTo(branchId) == 0 && booking.getStatusType().compareTo(StatusType.SCHEDULED) == 0).collect(Collectors.toList());
	}

	@Override
	public List<BookingEntity> fetchAllBookingsForDayOrPeriod(Date fromDate, Date toDate, Date date,StatusType statusType) {
		List<BookingEntity> bookingEntities = bookingRepository.findAll();
		if (null != fromDate) {
			return bookingEntities.stream()
					.filter(booking -> booking.getDate().after(fromDate) && booking.getDate().before(toDate))
					.filter(booking -> statusType != null ? booking.getStatusType().compareTo(statusType) == 0 : true)
					.collect(Collectors.toList());
		} else {
			return bookingEntities.stream()
					.filter(booking -> booking.getDate().toString().contentEquals(date.toString()))
					.filter(booking -> statusType != null ? booking.getStatusType().compareTo(statusType) == 0 : true)
					.collect(Collectors.toList());
		}
	}

}
