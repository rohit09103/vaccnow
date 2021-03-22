/**
 * 
 */
package com.localhost.vaccination.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.localhost.vaccination.entity.AvailabilityEntity;

import javassist.NotFoundException;

/**
 * Interface to expose methods to fetch data from db for availability
 * 
 * @author rohitsharma08
 *
 */
public interface AvailabilityDao {
	/**
	 * Method to fetch availability for a branch.
	 * 
	 * @param branchId
	 * @return
	 * @throws NotFoundException
	 */
	public Map<Date, List<AvailabilityEntity>> getAvailabilityForBranch(Integer branchId);
	
	/**
	 * Method to fetch availability for a branch on a date.
	 * @param branchId
	 * @param date
	 * @return
	 */
	List<AvailabilityEntity> getAvailabilityForBranchOnDate(Integer branchId, Date date);
	
	/**
	 * Remove this availability from db.
	 * used in case where we are doing a booking and a slot is no longer available.
	 * @param availabilityEntity
	 * @return
	 */
	void removeAvailability(AvailabilityEntity availabilityEntity);
	
	

}
