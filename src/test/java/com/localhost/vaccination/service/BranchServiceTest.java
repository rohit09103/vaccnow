/**
 * 
 */
package com.localhost.vaccination.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.localhost.vaccination.dao.AvailabilityDao;
import com.localhost.vaccination.dao.BranchDao;
import com.localhost.vaccination.entity.AvailabilityEntity;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccineEntity;
import com.localhost.vaccination.entity.VaccinesInBranchEntity;
import com.localhost.vaccination.mapper.AvailabilityMapper;
import com.localhost.vaccination.mapper.BranchMapper;
import com.localhost.vaccination.model.Availability;
import com.localhost.vaccination.model.AvailabilityDatesAndSlot;
import com.localhost.vaccination.model.Branch;
import com.localhost.vaccination.model.Vaccine;
import com.localhost.vaccination.service.impl.BranchServiceImpl;

/**
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class BranchServiceTest {

	@InjectMocks
	private BranchServiceImpl branchServiceImpl;

	@Mock
	private BranchDao branchDao;

	@Mock
	private BranchMapper branchMapper;

	@Mock
	private AvailabilityMapper availabilityMapper;

	@Mock
	private AvailabilityDao availabilityDao;

	@Test
	public void testGetAllBranches() {
		when(branchDao.getAllBranches()).thenReturn(prepareBranchEntities());
		when(branchMapper.mapListBranchEntityToListBranchModel(anyList())).thenReturn(prepareBranchModels());
		List<Branch> branchs = branchServiceImpl.getAllBranches();
		assertThat(branchs.size()).isEqualTo(2);
		assertThat(branchs.get(1).getBranchId()).isEqualTo(2);

	}

	@Test
	public void testGetAllVaccinesPerBranch() {
		when(branchDao.getAllAvailableVaccinesForBranches()).thenReturn(prepareVaccinesInBranchEntities());
		when(branchMapper.mapVaccinesBranchEntitiesToBranchModel(anyMap())).thenReturn(prepareBranchModels());
		List<Branch> branchs = branchServiceImpl.getAllVaccinesPerBranch();
		assertThat(branchs.size()).isEqualTo(2);
		assertThat(branchs.get(1).getBranchId()).isEqualTo(2);
	}

	@Test
	public void testGetAllVaccinesForBranch() {
		when(branchDao.getAllAvailableVaccinesForBranch(anyInt())).thenReturn(prepareVaccinesInBranchEntities());
		when(branchMapper.mapVaccinesBranchEntitiesToSingleBranchModel(anyMap()))
				.thenReturn(prepareBranchModels().get(0));
		Branch branch = branchServiceImpl.getAllVaccinesForBranch(1);
		assertThat(branch.getVaccines().size()).isEqualTo(3);
		assertThat(branch.getBranchId()).isEqualTo(1);
	}
	
	@Test
	public void testGetAllAvailabilityForBranch() {
		when(availabilityDao.getAvailabilityForBranch(anyInt())).thenReturn(prepareMapAvailabilityEntity());
		when(availabilityMapper.mapAvailabilityEntityToModel(anyMap())).thenReturn(prepareAvailability());
		Availability availability = branchServiceImpl.getAllAvailabilityForBranch(1);
		assertThat(availability.getBranchId()).isEqualTo(1);
		assertThat(availability.getDates().size()).isEqualTo(1);
		assertThat(availability.getDates().get(0).getSlots().size()).isEqualTo(2);
	}

	/**
	 * 
	 */
	private List<BranchEntity> prepareBranchEntities() {
		List<BranchEntity> branches = new ArrayList<>();
		BranchEntity branchOne = new BranchEntity();
		branchOne.setId(1);
		branchOne.setLocation("Delhi");
		branchOne.setName("Community Center");
		branchOne.setPhone("0987654321");
		branches.add(branchOne);

		branchOne = new BranchEntity();
		branchOne.setId(2);
		branchOne.setLocation("Mumbai");
		branchOne.setName("general hospital");
		branchOne.setPhone("890754321");
		branches.add(branchOne);
		return branches;
	}

	private List<Branch> prepareBranchModels() {
		List<Branch> branches = new ArrayList<>();
		Branch branchOne = new Branch();
		branchOne.setBranchId(1);
		branchOne.setLocation("Delhi");
		branchOne.setName("Community Center");
		branchOne.setPhoneNumber("0987654321");
		branches.add(branchOne);

		branchOne = new Branch();
		branchOne.setBranchId(2);
		branchOne.setLocation("Mumbai");
		branchOne.setName("general hospital");
		branchOne.setPhoneNumber("890754321");
		branches.add(branchOne);
		
		return setupBranchvaccines(branches);
	}

	private Map<Integer, List<VaccinesInBranchEntity>> prepareVaccinesInBranchEntities() {
		return prepareListVaccinesInBranchEntity().stream()
				.collect(Collectors.groupingBy(vacc -> vacc.getBranch().getId()));
	}

	private List<VaccinesInBranchEntity> prepareListVaccinesInBranchEntity() {
		List<VaccinesInBranchEntity> vaccinesInBranchEntities = new ArrayList<>();
		VaccinesInBranchEntity vaccinesInBranchEntity = new VaccinesInBranchEntity();
		vaccinesInBranchEntity.setBranch(prepareBranchEntity());
		vaccinesInBranchEntity.setVaccine(prepareVaccineEntity());
		vaccinesInBranchEntity.setQuantity(123);
		vaccinesInBranchEntities.add(vaccinesInBranchEntity);
		vaccinesInBranchEntity = new VaccinesInBranchEntity();
		vaccinesInBranchEntity.setBranch(prepareBranchEntity());
		VaccineEntity vaccine = new VaccineEntity();
		vaccine.setName("polio");
		vaccine.setId(2);
		vaccinesInBranchEntity.setVaccine(vaccine);
		vaccinesInBranchEntity.setQuantity(243);
		vaccinesInBranchEntities.add(vaccinesInBranchEntity);
		return vaccinesInBranchEntities;
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

	private List<Branch> setupBranchvaccines(List<Branch> branchs) {
		branchs.forEach(branch -> {
			List<Vaccine> vaccines = new ArrayList<>();
			Vaccine vaccine = new Vaccine();
			vaccine.setName("pfizer");
			vaccine.setVaccineId(1);
			vaccines.add(vaccine);

			vaccine = new Vaccine();
			vaccine.setName("covid shield");
			vaccine.setVaccineId(2);
			vaccines.add(vaccine);

			vaccine = new Vaccine();
			vaccine.setName("polio");
			vaccine.setVaccineId(3);
			vaccines.add(vaccine);
			branch.setVaccines(vaccines);
		});
		return branchs;
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
		availability.setId(2);
		availability.setBranch(prepareBranchEntity());
		availability.setDate(new Date(new java.util.Date().getTime()));
		availability.setSlot("10:00-10:15");
		return availabilityEntities;
	}
	
	private Availability prepareAvailability() {
		Availability availability = new Availability();
		availability.setBranchId(1);
		availability.setBranchLocation("Delhi");
		availability.setBranchName("Community Center");
		availability.setBranchPhone("4321567890");
		List<AvailabilityDatesAndSlot> availabilityDatesAndSlots = new ArrayList<>();
		AvailabilityDatesAndSlot availabilityDatesAndSlot = new AvailabilityDatesAndSlot();
		availabilityDatesAndSlot.setDate(new Date(new java.util.Date().getTime()));
		List<String> slots = new ArrayList<>();
		slots.add("09:00-09:15");
		slots.add("10:00-10:15");
		availabilityDatesAndSlot.setSlots(slots);
		availabilityDatesAndSlots.add(availabilityDatesAndSlot);
		availability.setDates(availabilityDatesAndSlots);
		return availability;
	}

}
