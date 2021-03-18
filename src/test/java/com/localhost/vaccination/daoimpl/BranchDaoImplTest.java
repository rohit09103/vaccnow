/**
 * 
 */
package com.localhost.vaccination.daoimpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

import com.localhost.vaccination.dao.impl.BranchDaoImpl;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccineEntity;
import com.localhost.vaccination.entity.VaccinesInBranchEntity;
import com.localhost.vaccination.repository.BranchRepository;
import com.localhost.vaccination.repository.VaccineInBrnachReposityory;

/**
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class BranchDaoImplTest {
	
	@InjectMocks
	private BranchDaoImpl branchDaoImpl;
	
	@Mock
	private VaccineInBrnachReposityory vaccineInBrnachReposityory;
	
	@Mock
	private BranchRepository branchRepository;
	
	@Test
	public void testGetAllBranches() {
		when(branchRepository.findAll()).thenReturn(prepareBranchEntities());
		List<BranchEntity> branchEntities = branchDaoImpl.getAllBranches();
		assertThat(branchEntities.size()).isEqualTo(2);
		assertThat(branchEntities.get(0).getId()).isEqualTo(1);
		assertThat(branchEntities.get(1).getId()).isEqualTo(2);
		
	}
	
	@Test
	public void testGetAvailabilityForBranchOnDate() {
		when(vaccineInBrnachReposityory.findAll()).thenReturn(prepareListVaccinesInBranchEntity());
		Map<Integer,List<VaccinesInBranchEntity>> map = branchDaoImpl.getAllAvailableVaccinesForBranches();
		assertThat(map.size()).isEqualTo(1);
		assertThat(map.get(1).size()).isEqualTo(2);
		assertThat(map.get(1).get(0).getQuantity()).isEqualTo(123);
	}
	
	@Test
	public void testGetQuantityForVaccineInBranch() {
		when(vaccineInBrnachReposityory.findAll()).thenReturn(prepareListVaccinesInBranchEntity());
		VaccinesInBranchEntity vaccinesInBranchEntity = branchDaoImpl.getQuantityForVaccineInBranch(1, 2);
		assertThat(vaccinesInBranchEntity.getBranch().getId()).isEqualTo(1);
		assertThat(vaccinesInBranchEntity.getVaccine().getId()).isEqualTo(2);
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
}
