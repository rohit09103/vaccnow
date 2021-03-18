/**
 * 
 */
package com.localhost.vaccination.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.localhost.vaccination.entity.BookingEntity;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccineEntity;
import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.model.Booking;
import com.localhost.vaccination.model.BranchBookings;
import com.localhost.vaccination.model.PaymentMethod;

/**
 * Class to map booking entity to booking model and vice-versa.
 * 
 * @author rohitsharma08
 *
 */
@Component
public class BookingMapper {

	/**
	 * Method to map each filed from booking model to entity.
	 * 
	 * @param booking
	 * @return
	 */
	public BookingEntity mapBookingModelToBookingEntity(Booking booking, BranchEntity branchEntity,
			VaccineEntity vaccineEntity) {
		BookingEntity bookingEntity = new BookingEntity();

		bookingEntity.setBranch(branchEntity);
		bookingEntity.setVaccine(vaccineEntity);
		bookingEntity.setAmount(booking.getPaymentMethod().getAmount());
		bookingEntity.setDate(new Date(booking.getBookingDate().getTime()));
		bookingEntity.setPaymentMethodType(booking.getPaymentMethod().getPaymentMethodType());
		bookingEntity.setCcv(
				null != booking.getPaymentMethod().getCcv() ? booking.getPaymentMethod().getCcv().toString() : null);
		bookingEntity.setCreditCardNumber(booking.getPaymentMethod().getCreditCardNumber());
		bookingEntity.setExpiry(booking.getPaymentMethod().getExpiryDate());
		bookingEntity.setPaymentMethodId(booking.getPaymentMethod().getPayemntMethodId());
		bookingEntity.setStatusType(StatusType.SCHEDULED);
		bookingEntity.setName(booking.getName());
		bookingEntity.setEmail(booking.getEmail());

		return bookingEntity;
	}

	/**
	 * Method to map all bookings for a branch to a branch booking object.
	 * 
	 * @param bookingEntities
	 * @return
	 */
	public BranchBookings mapBookingEntitiesToBranchBooking(List<BookingEntity> bookingEntities) {
		BranchBookings branchBookings = new BranchBookings();
		if (null != bookingEntities && !bookingEntities.isEmpty()) {
			branchBookings.setBranchId(bookingEntities.get(0).getBranch().getId());
			branchBookings.setLocation(bookingEntities.get(0).getBranch().getLocation());
			branchBookings.setName(bookingEntities.get(0).getBranch().getName());
			branchBookings.setPhoneNumber(bookingEntities.get(0).getBranch().getLocation());
			List<Booking> bookings = new ArrayList<>();
			bookingEntities.forEach(booking -> mapBookingEntitiesToBooking(booking, bookings));
			branchBookings.setBookings(bookings);
		}
		return branchBookings;
	}

	/**
	 * Method to map booking details from booking entity.
	 * 
	 * @param bookingEntities
	 * @param bookings
	 * @return
	 */
	private void mapBookingEntitiesToBooking(BookingEntity bookingEntity, List<Booking> bookings) {
		Booking booking = new Booking();
		populateBookingData(bookingEntity, booking);
		bookings.add(booking);
	}

	/**
	 * Method to populate fields of booking model
	 * 
	 * @param bookingEntity
	 * @param booking
	 */
	private void populateBookingData(BookingEntity bookingEntity, Booking booking) {
		booking.setBookingDate(bookingEntity.getDate());
		booking.setBookingId(bookingEntity.getId());
		booking.setName(bookingEntity.getName());
		booking.setEmail(bookingEntity.getEmail());
		booking.setVaccineId(bookingEntity.getVaccine().getId());
		PaymentMethod paymentMethod = new PaymentMethod(bookingEntity.getPaymentMethodId(),
				bookingEntity.getPaymentMethodType(), bookingEntity.getAmount(), bookingEntity.getCreditCardNumber(),
				null == bookingEntity.getCcv() ? null : Integer.valueOf(bookingEntity.getCcv()),
				bookingEntity.getExpiry());
		booking.setPaymentMethod(paymentMethod);
		booking.setStatusType(bookingEntity.getStatusType());
	}

	/**
	 * Map all fields from entity bookings to model bookings
	 * 
	 * @param bookingEntities
	 * @return
	 */
	public List<Booking> mapEntityBookingListToModelBookingList(List<BookingEntity> bookingEntities) {
		List<Booking> bookings = new ArrayList<>();
		bookingEntities.forEach(bookingEntity -> {
			Booking booking = new Booking();
			booking.setBranchId(bookingEntity.getBranch().getId());
			populateBookingData(bookingEntity, booking);
			bookings.add(booking);
		});
		return bookings;
	}

}
