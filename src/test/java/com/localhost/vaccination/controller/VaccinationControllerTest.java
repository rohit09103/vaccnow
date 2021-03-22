/**
 * 
 */
package com.localhost.vaccination.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.localhost.vaccination.exception.NotFoundException;
import com.localhost.vaccination.model.Availability;
import com.localhost.vaccination.model.AvailabilityDatesAndSlot;
import com.localhost.vaccination.model.Branch;
import com.localhost.vaccination.model.Vaccine;
import com.localhost.vaccination.service.BranchService;

/**
 * Test class for vaccination controller
 * @author rohitsharma08
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class VaccinationControllerTest{
	
	@InjectMocks
	private VaccinationController vaccinationController;
	
	@Mock
	private BranchService branchService;
	
	@Test
	public void testGetBranches() {
		when(branchService.getAllBranches()).thenReturn(branchesSetup());
		ResponseEntity<List<Branch>> branchesResponse = vaccinationController.getBranches();
		
		assertThat(branchesResponse.getStatusCode().compareTo(HttpStatus.OK)).isEqualTo(0);
		assertThat(branchesResponse.getBody().size()).isEqualTo(2);
		
	}
	
	@Test
	public void testGetBranchesException() {
		when(branchService.getAllBranches()).thenThrow(new RuntimeException());
		assertThrows(Exception.class, () ->{
			vaccinationController.getBranches();
		});
	}
	
	@Test
	public void testGetAllVaccinesPerBranch() {
		when(branchService.getAllVaccinesPerBranch()).thenReturn(setupBranchvaccines(branchesSetup()));
		ResponseEntity<List<Branch>> branchesResponse = vaccinationController.getAllVaccinesPerBranch();
		
		assertThat(branchesResponse.getStatusCode().compareTo(HttpStatus.OK)).isEqualTo(0);
		assertThat(branchesResponse.getBody().size()).isEqualTo(2);
		assertThat(branchesResponse.getBody().get(0).getVaccines().size()).isEqualTo(3);
	}
	
	@Test
	public void testGetAllVaccinesPerBranchException() {
		when(branchService.getAllVaccinesPerBranch()).thenThrow(new RuntimeException());
		assertThrows(Exception.class, () ->{
			vaccinationController.getAllVaccinesPerBranch();
		});
	}
	
	@Test
	public void testGetAllVaccinesForBranch() {
		when(branchService.getAllVaccinesForBranch(anyInt())).thenReturn(setupBranchvaccines(branchesSetup()).get(0));
		ResponseEntity<Branch> branchesResponse = vaccinationController.getAllVaccinesForBranch(1);
		
		assertThat(branchesResponse.getStatusCode().compareTo(HttpStatus.OK)).isEqualTo(0);
		assertThat(branchesResponse.getBody().getName()).isEqualToIgnoringCase("Community Center");
		assertThat(branchesResponse.getBody().getLocation()).isEqualToIgnoringCase("Delhi");
		assertThat(branchesResponse.getBody().getVaccines().size()).isEqualTo(3);
	}
	
	@Test
	public void testGetAllVaccinesForBranchNotFound() {
		assertThrows(NotFoundException.class, () ->{
			vaccinationController.getAllVaccinesForBranch(null);
		});
	}
	
	@Test
	public void testGetAllVaccinesForBranchException() {
		when(branchService.getAllVaccinesForBranch(anyInt())).thenThrow(new RuntimeException());
		assertThrows(Exception.class, () ->{
			vaccinationController.getAllVaccinesForBranch(1);
		});
	}
	
	@Test
	public void testGetAllAvailabilityForBranch() {
		when(branchService.getAllAvailabilityForBranch(anyInt())).thenReturn(setupAvailability());
		ResponseEntity<Availability> branchesResponse = vaccinationController.getAllAvailabilityForBranch(1);
		assertThat(branchesResponse.getStatusCode().compareTo(HttpStatus.OK)).isEqualTo(0);
		assertThat(branchesResponse.getBody().getBranchName()).isEqualToIgnoringCase("Community Center");
		assertThat(branchesResponse.getBody().getBranchLocation()).isEqualToIgnoringCase("Delhi");
		assertThat(branchesResponse.getBody().getBranchId()).isEqualTo(1);
		assertThat(branchesResponse.getBody().getDates().size()).isEqualTo(1);
		assertThat(branchesResponse.getBody().getDates().get(0).getSlots().size()).isEqualTo(2);
	}

	@Test
	public void testGetAllAvailabilityForBranchNotFound() {
		assertThrows(NotFoundException.class, () ->{
			vaccinationController.getAllAvailabilityForBranch(null);
		});
	}

	/**
	 * 
	 */
	private List<Branch> branchesSetup() {
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
		return branches;
	}
	
	private List<Branch> setupBranchvaccines(List<Branch> branchs){
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
	
	private Availability setupAvailability() {
		Availability availability = new Availability();
		availability.setBranchId(1);
		availability.setBranchLocation("Delhi");
		availability.setBranchName("Community Center");
		availability.setBranchPhone("4321567890");
		List<AvailabilityDatesAndSlot> availabilityDatesAndSlots = new ArrayList<>();
		AvailabilityDatesAndSlot availabilityDatesAndSlot = new AvailabilityDatesAndSlot();
		availabilityDatesAndSlot.setDate(new Date());
		List<String> slots = new ArrayList<>();
		slots.add("09:00-09:15");
		slots.add("10:00-10:15");
		availabilityDatesAndSlot.setSlots(slots);
		availabilityDatesAndSlots.add(availabilityDatesAndSlot);
		availability.setDates(availabilityDatesAndSlots);
		return availability;
	}

}
