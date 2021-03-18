/**
 * 
 */
package com.localhost.vaccination.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.localhost.vaccination.enums.PaymentMethodType;
import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.model.Booking;
import com.localhost.vaccination.model.BranchBookings;
import com.localhost.vaccination.model.PaymentMethod;
import com.localhost.vaccination.service.BookingService;

/**
 * Test class for booking controller
 * 
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class BookingControllerTest {

	@InjectMocks
	private BookingController bookingController;

	@Mock
	private BookingService bookingService;

	@Test
	public void testGetAllBookingsForBranches() {
		when(bookingService.fetchAllBookingsForBranch(anyInt())).thenReturn(prepareBranchBooking());
		ResponseEntity<BranchBookings> branchBookings = bookingController.getAllBookingsForBranches(1);
		assertThat(branchBookings.getStatusCode().compareTo(HttpStatus.OK)).isEqualTo(0);
		assertThat(branchBookings.getBody().getBookings().size()).isEqualTo(1);
	}

	@Test
	public void testGetAllBookingsForBranchesNotFound() {
		ResponseEntity<BranchBookings> branchBookings = bookingController.getAllBookingsForBranches(null);
		assertThat(branchBookings.getStatusCode().compareTo(HttpStatus.NOT_FOUND)).isEqualTo(0);
	}

	@Test
	public void testGetAllBookingsForDate() {
		when(bookingService.fetchAllBookingsForDateOrPeriod(any(), any(), any(Date.class),
				any(StatusType.class))).thenReturn(prepareListofBookings());
		ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookingsForDate(null, null, new Date());
		assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.OK)).isEqualTo(0);
		assertThat(responseEntity.getBody().size()).isEqualTo(2);
	}
	
	@Test
	public void testGetAllConfirmedBookingsForDate() {
		when(bookingService.fetchAllBookingsForDateOrPeriod(any(), any(), any(Date.class),
				any(StatusType.class))).thenReturn(prepareListofBookings());
		ResponseEntity<List<Booking>> responseEntity = bookingController.getAllConfirmedBookingsForDate(null, null, new Date());
		assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.OK)).isEqualTo(0);
		assertThat(responseEntity.getBody().size()).isEqualTo(2);
	}
	
	@Test
	public void testGetAllBookingsForDateValidationFailure1() {
		ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookingsForDate(null, null, null);
		assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.BAD_REQUEST)).isEqualTo(0);
	}
	
	
	@Test
	public void testGetAllBookingsForDateValidationFailure4() {
		ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookingsForDate(new Date(), null, new Date());
		assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.BAD_REQUEST)).isEqualTo(0);
	}
	
	@Test
	public void testGetAllBookingsForDateValidationFailure5() {
		ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookingsForDate(null, new Date(), new Date());
		assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.BAD_REQUEST)).isEqualTo(0);
	}
	
	@Test
	public void testGetAllBookingsForDateValidationFailure6() {
		ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookingsForDate(new Date(), new Date(), new Date());
		assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.BAD_REQUEST)).isEqualTo(0);
	}
	
	@Test
	public void testGetAllBookingsForDateValidationFailure2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookingsForDate(new Date(),cal.getTime() , null);
		assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.BAD_REQUEST)).isEqualTo(0);
	}
	
	@Test
	public void testGetAllBookingsForDateException() {
		when(bookingService.fetchAllBookingsForDateOrPeriod(any(Date.class), any(Date.class), any(Date.class),
				any(StatusType.class))).thenThrow(new RuntimeException());
		ResponseEntity<List<Booking>> responseEntity = bookingController.getAllBookingsForDate(null, null, new Date());
		assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.INTERNAL_SERVER_ERROR)).isEqualTo(0);
	}

	private BranchBookings prepareBranchBooking() {
		BranchBookings branchBookings = new BranchBookings();
		branchBookings.setBranchId(1);
		branchBookings.setLocation("delhi");
		branchBookings.setName("CommunityCenter");
		branchBookings.setPhoneNumber("7890654321");
		List<Booking> bookings = new ArrayList<>();
		branchBookings.setBookings(bookings);
		Booking booking = new Booking();
		booking.setBookingDate(new Date());
		booking.setName("name");
		booking.setEmail("name@email.com");
		booking.setStatusType(StatusType.SCHEDULED);
		booking.setVaccineId(2);
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setAmount(200.21);
		paymentMethod.setPaymentMethodType(PaymentMethodType.CASH);
		booking.setPaymentMethod(paymentMethod);
		bookings.add(booking);

		return branchBookings;
	}
	
	private List<Booking> prepareListofBookings(){
		List<Booking> bookings = new ArrayList<>();
		Booking booking = new Booking();
		booking.setBookingDate(new Date());
		booking.setName("name");
		booking.setEmail("name@email.com");
		booking.setStatusType(StatusType.SCHEDULED);
		booking.setBranchId(1);
		booking.setVaccineId(2);
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setAmount(200.21);
		paymentMethod.setPaymentMethodType(PaymentMethodType.CASH);
		booking.setPaymentMethod(paymentMethod);
		bookings.add(booking);
		
		booking = new Booking();
		booking.setBookingDate(new Date());
		booking.setBranchId(2);
		booking.setName("some");
		booking.setEmail("some@email.com");
		booking.setStatusType(StatusType.SCHEDULED);
		booking.setVaccineId(3);
		booking.setPaymentMethod(paymentMethod);
		bookings.add(booking);
		return bookings;
	}
}
