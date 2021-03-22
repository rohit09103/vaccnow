/**
 * 
 */
package com.localhost.vaccination.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rohit
 *
 */
@Embeddable
@Getter
@Setter
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

}
