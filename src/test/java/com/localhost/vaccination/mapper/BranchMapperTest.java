/**
 * 
 */
package com.localhost.vaccination.mapper;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccineEntity;
import com.localhost.vaccination.entity.VaccinesInBranchEntity;
import com.localhost.vaccination.model.Branch;

/**
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class BranchMapperTest {

	@InjectMocks
	private BranchMapper branchMapper;

	@Test
	public void testMapListBranchEntityToListBranchModel() {
		List<Branch> branchs = branchMapper.mapListBranchEntityToListBranchModel(branchEntitiesSetup());
		assertThat(branchs.size()).isEqualTo(2);
		assertThat(branchs.get(1).getBranchId()).isEqualTo(2);
	}

	@Test
	public void testMapVaccinesBranchEntitiesToBranchModel() {
		List<Branch> branchs = branchMapper.mapVaccinesBranchEntitiesToBranchModel(prepareVaccinesInBranchEntities());
		assertThat(branchs.size()).isEqualTo(1);
		assertThat(branchs.get(0).getBranchId()).isEqualTo(1);
	}
	
	@Test
	public void testMapVaccinesBranchEntitiesToSingleBranchModel() {
		Branch branch = branchMapper.mapVaccinesBranchEntitiesToSingleBranchModel(prepareVaccinesInBranchEntities());
		assertThat(branch.getBranchId()).isEqualTo(1);
		assertThat(branch.getVaccines().size()).isEqualTo(1);
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

	/**
	 * 
	 */
	private List<BranchEntity> branchEntitiesSetup() {
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
