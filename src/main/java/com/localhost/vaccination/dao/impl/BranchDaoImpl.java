/**
 * 
 */
package com.localhost.vaccination.dao.impl;

import static com.localhost.vaccination.constants.VaccinationConstants.NUMERIC_ZERO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localhost.vaccination.dao.BranchDao;
import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccinesInBranchEntity;
import com.localhost.vaccination.exception.NotFoundException;
import com.localhost.vaccination.repository.BranchRepository;
import com.localhost.vaccination.repository.VaccineInBrnachReposityory;

/**
 * Implementation class for branch dao, to fetch data from branch repository.
 * 
 * @author Rohit
 *
 */
@Component
public class BranchDaoImpl implements BranchDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(BranchDaoImpl.class);

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private VaccineInBrnachReposityory vaccineInBranchReposityory;

	@Override
	public List<BranchEntity> getAllBranches() {
		return branchRepository.findAll();
	}

	@Override
	public Map<Integer, List<VaccinesInBranchEntity>> getAllAvailableVaccinesForBranches() {
		return vaccineInBranchReposityory.findAll().stream().filter(vaccien -> vaccien.getQuantity() > 0)
				.collect(Collectors.groupingBy(vaccien -> vaccien.getBranch().getId()));
	}

	@Override
	public Map<Integer, List<VaccinesInBranchEntity>> getAllAvailableVaccinesForBranch(Integer branchId) {
		List<VaccinesInBranchEntity> vaccinesInBranchEntities = vaccineInBranchReposityory.findAll();
		if ( vaccinesInBranchEntities.isEmpty()) {
			throw new NotFoundException("nothing found for branchId");
		}
		return vaccinesInBranchEntities.stream()
				.filter(vaccien -> vaccien.getQuantity() > NUMERIC_ZERO
						&& branchId.compareTo(vaccien.getBranch().getId()) == NUMERIC_ZERO)
				.collect(Collectors.groupingBy(vaccien -> vaccien.getBranch().getId()));
	}

	@Override
	public void udpateQuantityForBooking(VaccinesInBranchEntity vaccinesInBranchEntity) {
		LOGGER.info("Updating the quantity in db for: {}.", vaccinesInBranchEntity);
		// saving the entity again
		vaccineInBranchReposityory.save(vaccinesInBranchEntity);
	}

	@Override
	public VaccinesInBranchEntity getQuantityForVaccineInBranch(Integer branchId, Integer vaccineId) {
		Optional<VaccinesInBranchEntity> vaccineInBranchEntityOptional = vaccineInBranchReposityory.findAll().stream()
				.filter(vacc -> isQuantityAvailable(branchId, vaccineId, vacc)).findAny();
		if (vaccineInBranchEntityOptional.isPresent()) {
			return vaccineInBranchEntityOptional.get();
		}
		LOGGER.info("Not able to find any records matching, branchId: {}, vaccineId: {}", branchId, vaccineId);
		return null;
	}

	/**
	 * Method to check if non-zero quantity is available in DB for branch and
	 * vaccine Id.
	 * 
	 * @param branchId
	 * @param vaccineId
	 * @param vacc
	 * @return
	 */
	private boolean isQuantityAvailable(Integer branchId, Integer vaccineId, VaccinesInBranchEntity vacc) {
		boolean isAvailable = vacc.getBranch().getId().compareTo(branchId) == NUMERIC_ZERO
				&& vacc.getVaccine().getId().compareTo(vaccineId) == NUMERIC_ZERO;
		return isAvailable && vacc.getQuantity().intValue() != NUMERIC_ZERO;
	}
}
