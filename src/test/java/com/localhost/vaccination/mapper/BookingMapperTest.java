/**
 * 
 */
package com.localhost.vaccination.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.localhost.vaccination.entity.BookingEntity;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccineEntity;
import com.localhost.vaccination.enums.PaymentMethodType;
import com.localhost.vaccination.enums.StatusType;
import com.localhost.vaccination.model.Booking;
import com.localhost.vaccination.model.BranchBookings;
import com.localhost.vaccination.model.PaymentMethod;

/**
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class BookingMapperTest {

	@InjectMocks
	private BookingMapper bookingMapper;

	@Test
	public void testMapBookingModelToBookingEntity() {
		BookingEntity bookingEntity = bookingMapper.mapBookingModelToBookingEntity(prepareBookingModel(),
				prepareBranchEntity(), prepareVaccineEntity());
		
		assertThat(bookingEntity.getAmount()).isEqualTo(200.21);
		assertThat(bookingEntity.getBranch().getId()).isEqualTo(1);
		assertThat(bookingEntity.getVaccine().getId()).isEqualTo(1);
	}
	
	@Test
	public void testMapBookingEntitiesToBranchBooking() {
		BranchBookings branchBookings = bookingMapper.mapBookingEntitiesToBranchBooking(prepareBookingEntitys());
		assertThat(branchBookings.getBranchId()).isEqualTo(1);
		assertThat(branchBookings.getBookings().size()).isEqualTo(1);
	}
	
	@Test
	public void testMapEntityBookingListToModelBookingList() {
		List<Booking> bookings = bookingMapper.mapEntityBookingListToModelBookingList(prepareBookingEntitys());
		assertThat(bookings.size()).isEqualTo(1);
		assertThat(bookings.get(0).getBranchId()).isEqualTo(1);
	}
	
	private List<BookingEntity> prepareBookingEntitys() {
		List<BookingEntity> bookingEntities  = new ArrayList<>();
		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setAmount(200.20);
		BranchEntity branchEntity = new BranchEntity();
		branchEntity.setId(1);
		branchEntity.setLocation("Delhi");
		branchEntity.setName("CommunityCenter");
		branchEntity.setPhone("9980765432");
		bookingEntity.setBranch(branchEntity);
		bookingEntity.setVaccine(new VaccineEntity());
		bookingEntity.setEmail("name@yopmail.com");
		bookingEntity.setName("name");
		bookingEntity.setStatusType(StatusType.COMPLETED);
		bookingEntity.setPaymentMethodType(PaymentMethodType.CASH);
		bookingEntities.add(bookingEntity);
		return bookingEntities;
	}

	private VaccineEntity prepareVaccineEntity() {
		VaccineEntity vaccine = new VaccineEntity();
		vaccine.setName("pfizer");
		vaccine.setId(1);
		return vaccine;
	}

	private BranchEntity prepareBranchEntity() {
		BranchEntity branchOne = new BranchEntity();
		branchOne.setId(1);
		branchOne.setLocation("Delhi");
		branchOne.setName("Community Center");
		branchOne.setPhone("0987654321");
		return branchOne;
	}

	private Booking prepareBookingModel() {
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
		return booking;
	}

}
