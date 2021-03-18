/**
 * 
 */
package com.localhost.vaccination.controller;

import static com.localhost.vaccination.constants.VaccinationConstants.ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR;
import static com.localhost.vaccination.constants.VaccinationConstants.ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.ArrayList;
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

/**
 * Controller class to map all incoming http request to execution methods.
 * 
 * @author Rohit
 *
 */
@RestController
@RequestMapping(path = "/")
public class VaccinationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VaccinationController.class);

	@Autowired
	private BranchService branchService;

	/**
	 * Method to fetch all the branches and their details.
	 * 
	 * @return
	 */
	@GetMapping(path = "/branches", produces = "application/json")
	public ResponseEntity<List<Branch>> getBranches() {
		LOGGER.info("Invoking branch service to fetch all branches.");
		List<Branch> branches = null;
		try {
			branches = branchService.getAllBranches();
		} catch (Exception exception) {
			LOGGER.error("Exception occured while fetching branches: {}", exception.getMessage());
			List<Branch> errorResponse = new ArrayList<>();
			errorResponse.add(new Branch(ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR,
					ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR));
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorResponse);
		}
		return ResponseEntity.ok(branches);
	}

	/**
	 * Method to return all available vaccines per branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/branches/vaccines", produces = "application/json")
	public ResponseEntity<List<Branch>> getAllVaccinesPerBranch() {
		LOGGER.info("Invoking branch service to fetch all vaccines for all branches.");
		List<Branch> branches = null;
		try {
			branches = branchService.getAllVaccinesPerBranch();
		} catch (Exception exception) {
			LOGGER.error("Exception occured while fetching branches: {}", exception.getMessage());
			List<Branch> errorResponse = new ArrayList<>();
			errorResponse.add(new Branch(ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR,
					ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR));
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorResponse);
		}
		return ResponseEntity.ok(branches);
	}

	/**
	 * Method to return all available vaccines per branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/branches/{branchId}/vaccines", produces = "application/json")
	public ResponseEntity<Branch> getAllVaccinesForBranch(
			@PathVariable(required = true, name = "branchId") Integer branchId) {
		LOGGER.info("Invoking branch service to fetch all vaccines for a branches with ID:{}.", branchId);
		Branch branch = null;
		try {
			if (null == branchId)
				throw new NotFoundException("Branch Id null.");
			branch = branchService.getAllVaccinesForBranch(branchId);
		} catch (NotFoundException foundException) {
			LOGGER.error("Branch with id:{} not found: {}", branchId, foundException.getMessage());
			return ResponseEntity.notFound().build();
		} catch (Exception exception) {
			LOGGER.error("Exception occured while fetching vaccines for branch with id:{} : {}", branchId,
					exception.getMessage());
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new Branch(
					ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR, ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR));
		}
		return ResponseEntity.ok(branch);
	}

	/**
	 * Method to return all availability for branch.
	 * 
	 * @return
	 */
	@GetMapping(path = "/branches/{branchId}/availability", produces = "application/json")
	public ResponseEntity<Availability> getAllAvailabilityForBranch(
			@PathVariable(required = true, name = "branchId") Integer branchId) {
		LOGGER.info("Invoking branch service to fetch all availability for all branches.");
		Availability availability = null;
		try {
			if (null == branchId)
				throw new NotFoundException("Branch Id null.");
			availability = branchService.getAllAvailabilityForBranch(branchId);
		} catch (RuntimeException foundException) {
			LOGGER.error("Branch with id:{} not found: {}", branchId, foundException.getMessage());
			return ResponseEntity.notFound().build();
		} catch (Exception exception) {
			LOGGER.error("Exception occured while fetching availability for branch with id:{} : {}", branchId,
					exception.getMessage());
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new Availability(
					ERROR_MESSAGE_CODE_INTERNAL_SERVER_ERROR, ERROR_MESAGE_DESCRIPTION_INTERNAL_SERVER_ERROR));
		}
		return ResponseEntity.ok(availability);
	}

}
