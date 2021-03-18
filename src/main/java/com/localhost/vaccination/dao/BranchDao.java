/**
 * 
 */
package com.localhost.vaccination.dao;

import java.util.List;
import java.util.Map;

import com.localhost.vaccination.entity.BranchEntity;
import com.localhost.vaccination.entity.VaccinesInBranchEntity;

import javassist.NotFoundException;

/**
 * Class to hold methods to access repository and fetch data from DB.
 * 
 * @author Rohit
 *
 */
public interface BranchDao {

	/**
	 * Method to fetch all branches from repository.
	 * 
	 * @return
	 */
	public List<BranchEntity> getAllBranches();

	/**
	 * Method to fetch all available vaccines from all branches from repository
	 * 
	 * @return
	 */
	public Map<Integer, List<VaccinesInBranchEntity>> getAllAvailableVaccinesForBranches();

	/**
	 * Method to fetch all available vaccines for a specific branch from repository
	 * 
	 * @return
	 * @throws NotFoundException
	 */
	public Map<Integer, List<VaccinesInBranchEntity>> getAllAvailableVaccinesForBranch(Integer branchId);
	
	/**
	 * Method to fetch a single record corresponding to a branch and a vaccine.
	 * @param branchId
	 * @param vaccineId
	 * @return
	 */
	public VaccinesInBranchEntity getQuantityForVaccineInBranch(Integer branchId, Integer vaccineId);
	
	/**
	 * Method to update the quantity of vaccine when the booking happens.
	 * @param vaccinesInBranchEntity
	 */
	public void udpateQuantityForBooking(VaccinesInBranchEntity vaccinesInBranchEntity);
}
