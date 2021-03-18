/**
 * 
 */
package com.localhost.vaccination.entity;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "AVAILABILITY")
public class AvailabilityEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2111818910364886548L;

	@Id
	@GeneratedValue
	@Column(name = "AVAILABILITY_ID")
	private Integer id;
	
	@MapsId("branchId")
	@OneToOne()
	private BranchEntity branch;
	
	@Column(name = "DATES")
	private Date date;
	
	@Column(name = "SLOT")
	private String slot;
	
	/**
	 * default constructor
	 */
	public AvailabilityEntity() {
		// empty constructor
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the slot
	 */
	public String getSlot() {
		return slot;
	}

	/**
	 * @param slot the slot to set
	 */
	public void setSlot(String slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return String.format("AvailabilityEntity [id=%s, branch=%s, date=%s, slot=%s]", id, branch, date, slot);
	}
	
}
