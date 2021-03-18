/**
 * 
 */
package com.localhost.vaccination.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.localhost.vaccination.dao.AvailabilityDao;
import com.localhost.vaccination.dao.BranchDao;
import com.localhost.vaccination.mapper.AvailabilityMapper;
import com.localhost.vaccination.mapper.BranchMapper;
import com.localhost.vaccination.model.Availability;
import com.localhost.vaccination.model.Branch;
import com.localhost.vaccination.service.BranchService;

/**
 * Implementation class for branch service interface.
 * 
 * @author Rohit
 *
 */
@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private BranchMapper branchMapper;

	@Autowired
	private AvailabilityMapper availabilityMapper;

	@Autowired
	private AvailabilityDao availabilityDao;

	@Override
	public List<Branch> getAllBranches() {
		return branchMapper.mapListBranchEntityToListBranchModel(branchDao.getAllBranches());
	}

	@Override
	public List<Branch> getAllVaccinesPerBranch() {
		return branchMapper.mapVaccinesBranchEntitiesToBranchModel(branchDao.getAllAvailableVaccinesForBranches());
	}

	@Override
	public Branch getAllVaccinesForBranch(Integer branchId) {
		return branchMapper
				.mapVaccinesBranchEntitiesToSingleBranchModel(branchDao.getAllAvailableVaccinesForBranch(branchId));
	}

	@Override
	public Availability getAllAvailabilityForBranch(Integer branchId) {
		return availabilityMapper.mapAvailabilityEntityToModel(availabilityDao.getAvailabilityForBranch(branchId));
	}

}
