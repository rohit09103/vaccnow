/**
 * 
 */
package com.localhost.vaccination.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rohit
 *
 */
@Entity
@Table(name = "BRANCHES")
public class BranchEntity implements Serializable,Comparable<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7046910467695787021L;

	@Id
	@GeneratedValue
	@Column(name = "BRANCH_ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "PHONE")
	private String phone;
	

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int compareTo(Integer o) {
		if(null == this.getId() || null == o)
			return 0;
		return this.getId().compareTo(o);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, location, name, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BranchEntity other = (BranchEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return String.format("BranchEntity [id=%s, name=%s, location=%s, phone=%s]", id, name, location, phone);
	}
	
	

}
