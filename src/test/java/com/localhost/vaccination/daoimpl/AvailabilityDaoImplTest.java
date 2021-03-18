/**
 * 
 */
package com.localhost.vaccination.daoimpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.localhost.vaccination.dao.impl.AvailabilityDaoImpl;
import com.localhost.vaccination.entity.AvailabilityEntity;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.repository.AvailabilityRepository;import ch.qos.logback.core.util.DatePatternToRegexUtil;

/**
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AvailabilityDaoImplTest {
	
	@InjectMocks
	private AvailabilityDaoImpl availabilityDaoImpl;
	
	@Mock
	private AvailabilityRepository availabilityRepository;
	
	@Test
	public void testGetAvailabilityForBranch() {
		when(availabilityRepository.findAll()).thenReturn(prepareAvailability());
		Map<Date, List<AvailabilityEntity>> map = availabilityDaoImpl.getAvailabilityForBranch(1);
		assertThat(map.size()).isEqualTo(1);
		
	}
	@Test
	public void testGetAvailabilityForBranchOnDate() {
		when(availabilityRepository.findAll()).thenReturn(prepareAvailability());
		List<AvailabilityEntity> availabilityEntities = availabilityDaoImpl.getAvailabilityForBranchOnDate(1, new Date(new java.util.Date().getTime()));
		assertThat(availabilityEntities.size()).isEqualTo(1);
		assertThat(availabilityEntities.get(0).getBranch().getId()).isEqualTo(1);
		assertThat(availabilityEntities.get(0).getId()).isEqualTo(1);
	}

	private List<AvailabilityEntity> prepareAvailability() {
		List<AvailabilityEntity> availabilityEntities = new ArrayList<>();
		AvailabilityEntity availability = new AvailabilityEntity();
		availability.setId(1);
		availability.setBranch(prepareBranchEntity());
		availability.setDate(new Date(new java.util.Date().getTime()));
		availability.setSlot("09:00-09:15");
		availabilityEntities.add(availability);
		availability = new AvailabilityEntity();
		availability.setId(2);
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
