/**
 * 
 */
package com.localhost.vaccination.service;

import java.util.List;

import com.localhost.vaccination.model.Availability;
import com.localhost.vaccination.model.Branch;



/**
 * Service class to perform business logic, invoke Dao layer and map entity to
 * model for controller.
 * 
 * @author Rohit
 *
 */
public interface BranchService {

	/**
	 * Method to fetch the details from dao layer and map them to model.
	 * @return
	 */
	 List<Branch> getAllBranches();
	/**
	 * Method to fetch all available vaccines for all the branches
	 * @return
	 */
	 List<Branch> getAllVaccinesPerBranch();
	
	/**
	 * Method to fetch all vaccines available for a branch identified by branchId
	 * @param branchId
	 * @return
	 */
	 Branch getAllVaccinesForBranch(Integer branchId);
	
	/**
	 * Method to fetch all vaialable slots for a branch
	 * @param branchId
	 * @return
	 */
	 Availability getAllAvailabilityForBranch(Integer branchId);

}
