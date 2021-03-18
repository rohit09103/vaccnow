/**
 * 
 */
package com.localhost.vaccination.daoimpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

import com.localhost.vaccination.dao.impl.BookingDaoImpl;
import com.localhost.vaccination.entity.BookingEntity;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccineEntity;
import com.localhost.vaccination.enums.PaymentMethodType;
import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.repository.BookingRepository;

/**
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class BookingDaoImplTest {

	@InjectMocks
	private BookingDaoImpl bookingDaoImpl;

	@Mock
	private BookingRepository bookingRepository;

	@Test
	public void testSaveBooking() {
		when(bookingRepository.save(any(BookingEntity.class))).thenReturn(prepareBookingEntity());
		BookingEntity bookingEntity = bookingDaoImpl.saveBooking(new BookingEntity());
		assertThat(bookingEntity.getId()).isEqualTo(1);
		assertThat(bookingEntity.getBranch().getId()).isEqualTo(1);

	}

	@Test
	public void testFetchAllBookingsForBranchId() {
		when(bookingRepository.findAll()).thenReturn(prepareBookingEntitys());
		List<BookingEntity> bookingEntities = bookingDaoImpl.fetchAllBookingsForBranchId(1);
		assertThat(bookingEntities.size()).isEqualTo(3);
		assertThat(bookingEntities.get(0).getBranch().getId()).isEqualTo(1);
		assertThat(bookingEntities.get(0).getId()).isEqualTo(1);
	}

	@Test
	public void testFetchAllBookingsForDayScheduled() {
		when(bookingRepository.findAll()).thenReturn(prepareBookingEntitys());
		List<BookingEntity> bookingEntities = bookingDaoImpl.fetchAllBookingsForDayOrPeriod(null, null,
				new Date(new java.util.Date().getTime()), StatusType.SCHEDULED);
		assertThat(bookingEntities.size()).isEqualTo(3);
		assertThat(bookingEntities.get(1).getBranch().getId()).isEqualTo(1);
		assertThat(bookingEntities.get(1).getId()).isEqualTo(3);
	}

	@Test
	public void testFetchAllBookingsForDayCompleted() {
		when(bookingRepository.findAll()).thenReturn(prepareBookingEntitys());
		List<BookingEntity> bookingEntities = bookingDaoImpl.fetchAllBookingsForDayOrPeriod(null, null,
				new Date(new java.util.Date().getTime()), StatusType.COMPLETED);
		assertThat(bookingEntities.size()).isEqualTo(1);
		assertThat(bookingEntities.get(0).getBranch().getId()).isEqualTo(1);
		assertThat(bookingEntities.get(0).getId()).isEqualTo(2);
	}

	@Test
	public void testFetchAllBookingsFromDateToDate() {
		when(bookingRepository.findAll()).thenReturn(prepareBookingEntitys());
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DATE, -5);
		Calendar to = Calendar.getInstance();
		to.add(Calendar.DATE, +5);
		List<BookingEntity> bookingEntities = bookingDaoImpl.fetchAllBookingsForDayOrPeriod(
				new Date(from.getTimeInMillis()), new Date(to.getTimeInMillis()), null, StatusType.SCHEDULED);
		assertThat(bookingEntities.size()).isEqualTo(3);
		assertThat(bookingEntities.get(1).getBranch().getId()).isEqualTo(1);
		assertThat(bookingEntities.get(1).getId()).isEqualTo(3);
	}

	private BranchEntity prepareBranchEntity() {
		BranchEntity branchOne = new BranchEntity();
		branchOne.setId(1);
		branchOne.setLocation("Delhi");
		branchOne.setName("Community Center");
		branchOne.setPhone("0987654321");
		return branchOne;
	}

	private BookingEntity prepareBookingEntity() {
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setAmount(200.20);
		bookingEntity.setId(1);
		bookingEntity.setBranch(prepareBranchEntity());
		bookingEntity.setVaccine(new VaccineEntity());
		bookingEntity.setEmail("name@yopmail.com");
		bookingEntity.setName("name");
		bookingEntity.setStatusType(StatusType.SCHEDULED);
		bookingEntity.setPaymentMethodType(PaymentMethodType.CASH);
		return bookingEntity;
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
}
