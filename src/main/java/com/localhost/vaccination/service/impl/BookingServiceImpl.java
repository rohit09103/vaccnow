/**
 * 
 */
package com.localhost.vaccination.service.impl;

import static com.localhost.vaccination.constants.VaccinationConstants.BOOKING_SLOT_NOT_AVAILABLE;
import static com.localhost.vaccination.constants.VaccinationConstants.NUMERIC_ONE;
import static com.localhost.vaccination.constants.VaccinationConstants.VACCINE_IS_NOT_AVAILABLE;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localhost.vaccination.dao.AvailabilityDao;
import com.localhost.vaccination.dao.BookingDao;
import com.localhost.vaccination.dao.BranchDao;
import com.localhost.vaccination.entity.AvailabilityEntity;
import com.localhost.vaccination.entity.BookingEntity;
import com.localhost.vaccination.entity.VaccinesInBranchEntity;
import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.mapper.BookingMapper;
import com.localhost.vaccination.model.Booking;
import com.localhost.vaccination.model.BranchBookings;
import com.localhost.vaccination.service.BookingService;

/**
 * Service implementation to perform business logic and booking.
 * 
 * @author rohitsharma08
 *
 */
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private AvailabilityDao availabilityDao;

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private BookingMapper bookingMapper;

	@Override
	@Transactional
	public Booking createBooking(Booking booking) {
		List<String> errorMessages = new ArrayList<>();
		List<AvailabilityEntity> availabilityEntities = availabilityDao
				.getAvailabilityForBranchOnDate(booking.getBranchId(), new Date(booking.getBookingDate().getTime()));
		Optional<AvailabilityEntity> availabilityEntityOptional = checkAvailability(availabilityEntities,
				booking.getBookingSlot());

		if (availabilityEntityOptional.isPresent()) {
			// remove the slot if it is available as it is booked in this booking.
			availabilityDao.removeAvailability(availabilityEntityOptional.get());
		} else {
			// add error if slot is not available.
			errorMessages.add(BOOKING_SLOT_NOT_AVAILABLE);
		}

		VaccinesInBranchEntity vaccinesInBranchEntity = branchDao.getQuantityForVaccineInBranch(booking.getBranchId(),
				booking.getVaccineId());
		if (null != vaccinesInBranchEntity) {
			// reduce the quantity by 1 and update the DB.
			vaccinesInBranchEntity.setQuantity(vaccinesInBranchEntity.getQuantity() - NUMERIC_ONE);
			branchDao.udpateQuantityForBooking(vaccinesInBranchEntity);
		} else {
			// add error message if quantity is not present.
			errorMessages.add(VACCINE_IS_NOT_AVAILABLE);
		}
		if (errorMessages.isEmpty() && null != vaccinesInBranchEntity) {
			BookingEntity bookingEntity = bookingDao.saveBooking(bookingMapper.mapBookingModelToBookingEntity(booking,
					vaccinesInBranchEntity.getBranch(), vaccinesInBranchEntity.getVaccine()));
			booking.setBookingId(bookingEntity.getId());
		} else {
			// case of no booking and validation failures.
			booking.setErrorMessages(errorMessages);
		}
		return booking;
	}

	/**
	 * Method to check if provided slot is available.
	 * 
	 * @param availabilityEntities
	 * @param slot
	 * @return
	 */
	private Optional<AvailabilityEntity> checkAvailability(List<AvailabilityEntity> availabilityEntities, String slot) {
		if (null == availabilityEntities) {
			return Optional.empty();
		}
		return availabilityEntities.stream().filter(availEntity -> availEntity.getSlot().contentEquals(slot)).findAny();
	}

	@Override
	public BranchBookings fetchAllBookingsForBranch(Integer branchId) {
		return bookingMapper.mapBookingEntitiesToBranchBooking(bookingDao.fetchAllBookingsForBranchId(branchId));
	}

	@Override
	public List<Booking> fetchAllBookingsForDateOrPeriod(java.util.Date fromDate, java.util.Date toDate,
			java.util.Date date, StatusType statusType) {
		// date conversion from util to sql
		List<BookingEntity> bookingEntities = bookingDao.fetchAllBookingsForDayOrPeriod(
				null == fromDate ? null : new Date(fromDate.getTime()),
				null == toDate ? null : new Date(toDate.getTime()), null == date ? null : new Date(date.getTime()),
						statusType);
		return bookingMapper.mapEntityBookingListToModelBookingList(bookingEntities);
	}

}
