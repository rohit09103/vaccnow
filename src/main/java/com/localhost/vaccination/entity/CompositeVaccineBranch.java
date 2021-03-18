/**
 * 
 */
package com.localhost.vaccination.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author Rohit
 *
 */
@Embeddable
public class CompositeVaccineBranch implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9164416070015766550L;

	private Integer branchId;
	
	private Integer vaccineId;
	
	
	public CompositeVaccineBranch() {
		
	}

	/**
	 * @param branchId
	 * @param vaccineId
	 */
	public CompositeVaccineBranch(Integer branchId, Integer vaccineId) {
		this.branchId = branchId;
		this.vaccineId = vaccineId;
	}



	/**
	 * @return the branchId
	 */
	public Integer getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the vaccineId
	 */
	public Integer getVaccineId() {
		return vaccineId;
	}

	/**
	 * @param vaccineId the vaccineId to set
	 */
	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
	}
	
	

}
