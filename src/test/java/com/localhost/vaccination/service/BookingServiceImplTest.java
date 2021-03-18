/**
 * 
 */
package com.localhost.vaccination.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.localhost.vaccination.dao.AvailabilityDao;
import com.localhost.vaccination.dao.BookingDao;
import com.localhost.vaccination.dao.BranchDao;
import com.localhost.vaccination.entity.BookingEntity;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccineEntity;
import com.localhost.vaccination.enums.PaymentMethodType;
import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.mapper.BookingMapper;
import com.localhost.vaccination.model.Booking;
import com.localhost.vaccination.model.BranchBookings;
import com.localhost.vaccination.model.PaymentMethod;
import com.localhost.vaccination.service.impl.BookingServiceImpl;

/**
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class BookingServiceImplTest {

	@InjectMocks
	private BookingServiceImpl bookingServiceImpl;

	@Mock
	private BranchDao branchDao;

	@Mock
	private BookingMapper bookingMapper;

	@Mock
	private BookingDao bookingDao;

	@Mock
	private AvailabilityDao availabilityDao;

	@Test
	public void testFetchAllBookingsForBranch() {
		when(bookingDao.fetchAllBookingsForBranchId(anyInt())).thenReturn(prepareBookingEntitys());
		when(bookingMapper.mapBookingEntitiesToBranchBooking(anyList())).thenReturn(prepareBranchBooking());
		BranchBookings branchBookings = bookingServiceImpl.fetchAllBookingsForBranch(1);
		assertThat(branchBookings.getBookings().get(0).getVaccineId()).isEqualTo(2);
		assertThat(branchBookings.getBookings().size()).isEqualTo(1);
		assertThat(branchBookings.getBranchId()).isEqualTo(1);

	}

	@Test
	public void testFetchAllBookingsForDateScheduled() {
		when(bookingDao.fetchAllBookingsForDayOrPeriod(any(Date.class), any(Date.class), any(Date.class),
				any(StatusType.class))).thenReturn(prepareBookingEntitys());
		when(bookingMapper.mapEntityBookingListToModelBookingList(anyList())).thenReturn(prepareBookingModels());
		List<Booking> bookings = bookingServiceImpl.fetchAllBookingsForDateOrPeriod(null, null, new java.util.Date(), StatusType.SCHEDULED);
		assertThat(bookings.size()).isEqualTo(3);
		assertThat(bookings.get(1).getBranchId()).isEqualTo(1);
		assertThat(bookings.get(1).getVaccineId()).isEqualTo(3);
	}
	
	@Test
	public void testFetchAllBookingsForDateCompleted() {
		when(bookingDao.fetchAllBookingsForDayOrPeriod(any(Date.class), any(Date.class), any(Date.class),
				any(StatusType.class))).thenReturn(prepareBookingEntitys());
		when(bookingMapper.mapEntityBookingListToModelBookingList(anyList())).thenReturn(prepareBookingModels());
		List<Booking> bookings = bookingServiceImpl.fetchAllBookingsForDateOrPeriod(null, null, new java.util.Date(), StatusType.COMPLETED);
		assertThat(bookings.size()).isEqualTo(1);
		assertThat(bookings.get(0).getBranchId()).isEqualTo(1);
		assertThat(bookings.get(0).getVaccineId()).isEqualTo(2);
	}
	
	@Test
	public void testFetchAllBookingsForPeriod() {
		when(bookingDao.fetchAllBookingsForDayOrPeriod(any(Date.class), any(Date.class), any(Date.class),
				any(StatusType.class))).thenReturn(prepareBookingEntitys());
		when(bookingMapper.mapEntityBookingListToModelBookingList(anyList())).thenReturn(prepareBookingModels());
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DATE, -5);
		Calendar to = Calendar.getInstance();
		to.add(Calendar.DATE, +5);
		List<Booking> bookings = bookingServiceImpl.fetchAllBookingsForDateOrPeriod(from.getTime(), to.getTime(), null, StatusType.SCHEDULED);
		assertThat(bookings.size()).isEqualTo(3);
		assertThat(bookings.get(2).getBranchId()).isEqualTo(1);
		assertThat(bookings.get(2).getVaccineId()).isEqualTo(4);
	}

	private BranchEntity prepareBranchEntity() {
		BranchEntity branchOne = new BranchEntity();
		branchOne.setId(1);
		branchOne.setLocation("Delhi");
		branchOne.setName("Community Center");
		branchOne.setPhone("0987654321");
		return branchOne;
	}

	private List<BookingEntity> prepareBookingEntitys() {
		List<BookingEntity> bookingEntities = new ArrayList<>();
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setId(1);
		bookingEntity.setAmount(200.20);
		bookingEntity.setDate(new Date(new java.util.Date().getTime()));
		bookingEntity.setBranch(prepareBranchEntity());
		bookingEntity.setVaccine(new VaccineEntity());
		bookingEntity.setEmail("name@yopmail.com");
		bookingEntity.setName("name");
		bookingEntity.setStatusType(StatusType.SCHEDULED);
		bookingEntity.setPaymentMethodType(PaymentMethodType.CASH);
		bookingEntities.add(bookingEntity);
		bookingEntity = new BookingEntity();
		bookingEntity.setId(2);
		bookingEntity.setAmount(100.90);
		bookingEntity.setDate(new Date(new java.util.Date().getTime()));
		bookingEntity.setBranch(prepareBranchEntity());
		bookingEntity.setVaccine(new VaccineEntity());
		bookingEntity.setEmail("some@yopmail.com");
		bookingEntity.setName("some");
		bookingEntity.setStatusType(StatusType.COMPLETED);
		bookingEntity.setPaymentMethodType(PaymentMethodType.CASH);
		bookingEntities.add(bookingEntity);
		bookingEntity = new BookingEntity();
		bookingEntity.setId(3);
		bookingEntity.setAmount(130.20);
		bookingEntity.setDate(new Date(new java.util.Date().getTime()));
		bookingEntity.setBranch(prepareBranchEntity());
		bookingEntity.setVaccine(new VaccineEntity());
		bookingEntity.setEmail("random@yopmail.com");
		bookingEntity.setName("random");
		bookingEntity.setStatusType(StatusType.SCHEDULED);
		bookingEntity.setPaymentMethodType(PaymentMethodType.CASH);
		bookingEntities.add(bookingEntity);
		bookingEntity = new BookingEntity();
		bookingEntity.setId(4);
		bookingEntity.setAmount(50.20);
		bookingEntity.setDate(new Date(new java.util.Date().getTime()));
		bookingEntity.setBranch(prepareBranchEntity());
		bookingEntity.setVaccine(new VaccineEntity());
		bookingEntity.setEmail("here@yopmail.com");
		bookingEntity.setName("here");
		bookingEntity.setStatusType(StatusType.SCHEDULED);
		bookingEntity.setPaymentMethodType(PaymentMethodType.CASH);
		bookingEntities.add(bookingEntity);
		return bookingEntities;
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
		booking.setBookingDate(new Date(new java.util.Date().getTime()));
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
	
	private List<Booking> prepareBookingModels() {
		List<Booking> bookings = new ArrayList<>();
		Booking booking = new Booking();
		booking.setBookingDate(new java.util.Date());
		booking.setBranchId(1);
		booking.setVaccineId(1);
		booking.setEmail("name@yopmail.com");
		booking.setName("name");
		booking.setStatusType(StatusType.SCHEDULED);
		PaymentMethod paymentMethod = new PaymentMethod(null, PaymentMethodType.CASH, 200.22, null, null, null);
		booking.setPaymentMethod(paymentMethod);
		bookings.add(booking);
		booking = new Booking();
		booking.setBookingDate(new java.util.Date());
		booking.setBranchId(1);
		booking.setVaccineId(2);
		booking.setEmail("some@yopmail.com");
		booking.setName("some");
		booking.setStatusType(StatusType.COMPLETED);
		paymentMethod = new PaymentMethod(null, PaymentMethodType.CASH, 100.22, null, null, null);
		booking.setPaymentMethod(paymentMethod);
		bookings.add(booking);
		booking = new Booking();
		booking.setBookingDate(new java.util.Date());
		booking.setBranchId(1);
		booking.setVaccineId(3);
		booking.setEmail("random@yopmail.com");
		booking.setName("random");
		booking.setStatusType(StatusType.SCHEDULED);
		paymentMethod = new PaymentMethod(null, PaymentMethodType.CASH, 130.00, null, null, null);
		booking.setPaymentMethod(paymentMethod);
		bookings.add(booking);
		booking = new Booking();
		booking.setBookingDate(new java.util.Date());
		booking.setBranchId(1);
		booking.setVaccineId(4);
		booking.setEmail("here@yopmail.com");
		booking.setName("here");
		booking.setStatusType(StatusType.SCHEDULED);
		paymentMethod = new PaymentMethod(null, PaymentMethodType.CASH, 480.22, null, null, null);
		booking.setPaymentMethod(paymentMethod);
		bookings.add(booking);
		return bookings;
	}

}
