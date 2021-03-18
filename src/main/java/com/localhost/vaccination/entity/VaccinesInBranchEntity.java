/**
 * 
 */
package com.localhost.vaccination.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Rohit
 *
 */
@Entity
@Table(name = "VACCINES_BRANCHES")
public class VaccinesInBranchEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8531611308485919814L;

	@Id
	@GeneratedValue
	@Column(name = "vaccines_branches_id")
	private Integer id;
	
	@MapsId("branchId")
	@OneToOne()
	private BranchEntity branch;
	
	@MapsId("vaccineId")
	@OneToOne()
	private VaccineEntity vaccine;
	
	private Integer quantity;


	/**
	 * @return the branch
	 */
	public BranchEntity getBranch() {
		return branch;
	}

	/**
	 * @param branch the branch to set
	 */
	public void setBranch(BranchEntity branch) {
		this.branch = branch;
	}

	/**
	 * @return the vaccine
	 */
	public VaccineEntity getVaccine() {
		return vaccine;
	}

	/**
	 * @param vaccine the vaccine to set
	 */
	public void setVaccine(VaccineEntity vaccine) {
		this.vaccine = vaccine;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return String.format("VaccinesInBranchEntity [id=%s, branch=%s, vaccine=%s, quantity=%s]", id, branch, vaccine,
				quantity);
	}
	
}
