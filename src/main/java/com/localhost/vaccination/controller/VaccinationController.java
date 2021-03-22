/**
 * 
 */
package com.localhost.vaccination.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.localhost.vaccination.exception.NotFoundException;
import com.localhost.vaccination.model.Availability;
import com.localhost.vaccination.model.Branch;
import com.localhost.vaccination.service.BranchService;

import io.swagger.annotations.ApiOperation;

/**
 * Controller class to map all incoming http request to execution methods.
 * 
 * @author Rohit
 *
 */
@RestController
@RequestMapping(path = "/vaccnow")
public class VaccinationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VaccinationController.class);

	@Autowired
	private BranchService branchService;

	/**
	 * Method to fetch all the branches and their details.
	 * 
	 * @return
	 */
	@GetMapping(path = "/branches")
	@ApiOperation(value = "Get All braches for a vaccination.")
	public ResponseEntity<List<Branch>> getBranches() {
		LOGGER.info("Invoking branch service to fetch all branches.");
		List<Branch> branches = null;
			branches = branchService.getAllBranches();
		return ResponseEntity.ok(branches);
	}

	/**
	 * Method to return all available vaccines per branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/branches/vaccines")
	@ApiOperation(value = "Get all available vaccines for all branches.")
	public ResponseEntity<List<Branch>> getAllVaccinesPerBranch() {
		LOGGER.info("Invoking branch service to fetch all vaccines for all branches.");
		List<Branch> branches = null;
			branches = branchService.getAllVaccinesPerBranch();
		return ResponseEntity.ok(branches);
	}

	/**
	 * Method to return all available vaccines per branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/branches/{branchId}/vaccines")
	@ApiOperation(value = "Get all available vaccines for a branch.")
	public ResponseEntity<Branch> getAllVaccinesForBranch(
			@PathVariable(required = true, name = "branchId") Integer branchId) {
		LOGGER.info("Invoking branch service to fetch all vaccines for a branches with ID:{}.", branchId);
		Branch branch = null;
			if (null == branchId)
				throw new NotFoundException("Branch Id null.");
			branch = branchService.getAllVaccinesForBranch(branchId);
		return ResponseEntity.ok(branch);
	}

	/**
	 * Method to return all availability for branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/branches/{branchId}/availability")
	@ApiOperation(value = "Get all available slots for a branch.")
	public ResponseEntity<Availability> getAllAvailabilityForBranch(
			@PathVariable(required = true, name = "branchId") Integer branchId) {
		LOGGER.info("Invoking branch service to fetch all availability for all branches.");
		Availability availability = null;
			if (null == branchId)
				throw new NotFoundException("Branch Id null.");
			availability = branchService.getAllAvailabilityForBranch(branchId);
		return ResponseEntity.ok(availability);
	}

}
