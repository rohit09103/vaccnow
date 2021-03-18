/**
 * 
 */
package com.localhost.vaccination.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccineEntity;
import com.localhost.vaccination.entity.VaccinesInBranchEntity;
import com.localhost.vaccination.model.Branch;
import com.localhost.vaccination.model.Vaccine;

/**
 * Class to hold methods which map model to entity and vice-versa
 * 
 * @author Rohit
 *
 */
@Component
public class BranchMapper {

	/**
	 * Method to transform the brnach entity to branch model so it can be sent as
	 * http response.
	 * 
	 * @param branchEntity
	 * @return
	 */
	public List<Branch> mapListBranchEntityToListBranchModel(List<BranchEntity> branchEntities) {
		return branchEntities.stream().map(entity -> mapEntityToModel(entity)).collect(Collectors.toList());
	}

	/**
	 * Method to map each field from entity to model.
	 * 
	 * @param entity
	 * @param branchs
	 * @return
	 */
	private Branch mapEntityToModel(BranchEntity entity) {
		Branch branch = new Branch();
		branch.setBranchId(entity.getId());
		branch.setLocation(entity.getLocation());
		branch.setName(entity.getName());
		branch.setPhoneNumber(entity.getPhone());
		return branch;
	}

	/**
	 * Method to map all the fields from entity to branch model.
	 * 
	 * @param vaccinesInBranchEntities
	 * @return
	 */
	public List<Branch> mapVaccinesBranchEntitiesToBranchModel(
			Map<Integer, List<VaccinesInBranchEntity>> vaccinesInBranchEntities) {
		List<Branch> branchs = new ArrayList<>();
		vaccinesInBranchEntities.keySet()
				.forEach(branchId -> mapVaccinesAndBranchToModel(vaccinesInBranchEntities.get(branchId), branchs));
		return branchs;
	}

	/**
	 * method to map fields from entity to model to be sent to rest client.
	 * 
	 * @param list
	 * @param branchs
	 * @return
	 */
	private void mapVaccinesAndBranchToModel(List<VaccinesInBranchEntity> vaccinesInBranchEntities,
			List<Branch> branchs) {
		if (null != vaccinesInBranchEntities && !vaccinesInBranchEntities.isEmpty()) {
			Branch branch = mapEntityToModel(vaccinesInBranchEntities.get(0).getBranch());
			vaccinesInBranchEntities.forEach(
					vaccine -> mapVaccineEntityToBranchModel(vaccine.getVaccine(), vaccine.getQuantity(), branch));
			branchs.add(branch);
		}
	}

	/**
	 * Method to map fields for vaccine entity to branchVaccine model.
	 * 
	 * @param quantity
	 * @param vaccine
	 * @param branch
	 * @return
	 */
	private void mapVaccineEntityToBranchModel(VaccineEntity vaccineEntity, Integer quantity, Branch branch) {
		if (null == branch.getVaccines()) {
			branch.setVaccines(new ArrayList<Vaccine>());
		}
		Vaccine vaccine = new Vaccine();
		vaccine.setName(vaccineEntity.getName());
		vaccine.setVaccineId(vaccineEntity.getId());
		vaccine.setQuantity(quantity);
		branch.getVaccines().add(vaccine);
	}
	
	/**
	 * Method to map all the fields from entity to branch model.
	 * 
	 * @param vaccinesInBranchEntities
	 * @return
	 */
	public Branch mapVaccinesBranchEntitiesToSingleBranchModel(
			Map<Integer, List<VaccinesInBranchEntity>> vaccinesInBranchEntities) {
		List<Branch> branchs = new ArrayList<>();
		vaccinesInBranchEntities.keySet()
				.forEach(branchId -> mapVaccinesAndBranchToModel(vaccinesInBranchEntities.get(branchId), branchs));
		return branchs.get(0);
	}

}
