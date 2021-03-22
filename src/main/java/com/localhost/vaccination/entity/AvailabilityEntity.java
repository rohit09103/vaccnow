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

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rohit
 *
 */
@Entity
@Table(name = "AVAILABILITY")
@Getter
@Setter
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

	

	@Override
	public String toString() {
		return String.format("AvailabilityEntity [id=%s, branch=%s, date=%s, slot=%s]", id, branch, date, slot);
	}
	
}
