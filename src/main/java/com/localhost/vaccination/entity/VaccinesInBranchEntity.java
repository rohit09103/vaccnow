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

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rohit
 *
 */
@Entity
@Table(name = "VACCINES_BRANCHES")
@Getter
@Setter
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


	@Override
	public String toString() {
		return String.format("VaccinesInBranchEntity [id=%s, branch=%s, vaccine=%s, quantity=%s]", id, branch, vaccine,
				quantity);
	}
	
}
