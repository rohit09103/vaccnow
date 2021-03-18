/**
 * 
 */
package com.localhost.vaccination.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.localhost.vaccination.entity.AvailabilityEntity;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.model.Availability;
import com.localhost.vaccination.model.AvailabilityDatesAndSlot;

/**
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AvailabilityMapperTest {
	
	@InjectMocks
	private AvailabilityMapper availabilityMapper;
	
	@Test
	public void testMapAvailabilityEntityToModel() {
		Availability availability = availabilityMapper.mapAvailabilityEntityToModel(prepareMapAvailabilityEntity());
		assertThat(availability.getBranchId()).isEqualTo(1);
		assertThat(availability.getDates().get(0).getSlots().size()).isEqualTo(1);
	}

	private Map<Date, List<AvailabilityEntity>> prepareMapAvailabilityEntity() {
		return setupAvailabilityEntities().stream().collect(Collectors.groupingBy(avail -> avail.getDate()));
	}
	
	private List<AvailabilityEntity> setupAvailabilityEntities() {
		List<AvailabilityEntity> availabilityEntities = new ArrayList<>();
		AvailabilityEntity availability = new AvailabilityEntity();
		availability.setId(1);
		availability.setBranch(prepareBranchEntity());
		availability.setDate(new Date(new java.util.Date().getTime()));
		availability.setSlot("09:00-09:15");
		availabilityEntities.add(availability);
		availability = new AvailabilityEntity();
		availability.setId(1);
		availability.setBranch(prepareBranchEntity());
		availability.setDate(new Date(new java.util.Date().getTime()));
		availability.setSlot("10:00-10:15");
		return availabilityEntities;
	}
	
	private BranchEntity prepareBranchEntity() {
		BranchEntity branchOne = new BranchEntity();
		branchOne.setId(1);
		branchOne.setLocation("Delhi");
		branchOne.setName("Community Center");
		branchOne.setPhone("0987654321");
		return branchOne;
	}

}
