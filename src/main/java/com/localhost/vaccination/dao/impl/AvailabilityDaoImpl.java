/**
 * 
 */
package com.localhost.vaccination.dao.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localhost.vaccination.constants.VaccinationConstants;
import com.localhost.vaccination.dao.AvailabilityDao;
import com.localhost.vaccination.entity.AvailabilityEntity;
import com.localhost.vaccination.exception.NotFoundException;
import com.localhost.vaccination.repository.AvailabilityRepository;

/**
 * Dao impl to fetch data from db for availability for a branch
 * 
 * @author Rohit
 *
 */
@Component
public class AvailabilityDaoImpl implements AvailabilityDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AvailabilityEntity.class);

	@Autowired
	private AvailabilityRepository availabilityRepository;

	@Override
	public Map<Date, List<AvailabilityEntity>> getAvailabilityForBranch(Integer branchId) {
		List<AvailabilityEntity> availabilityEntities = availabilityRepository.findAll();
		if (null == availabilityEntities || availabilityEntities.isEmpty()) {
			throw new NotFoundException("nothing found for branchId");
		}
		// filter for past date is not added as of now.
		return availabilityRepository.findAll().stream()
				.filter(data -> data.getBranch().getId().compareTo(branchId) == VaccinationConstants.NUMERIC_ZERO)
				.collect(Collectors.groupingBy(AvailabilityEntity::getDate));
	}

	@Override
	public List<AvailabilityEntity> getAvailabilityForBranchOnDate(Integer branchId, Date date) {
		Map<Date, List<AvailabilityEntity>> map = getAvailabilityForBranch(branchId);
		List<AvailabilityEntity> availabilityEntities = performDateSearch(branchId, date, map);
		if (null == availabilityEntities || availabilityEntities.isEmpty()) {
			LOGGER.info("Null or empty availability for branchId:{}, date: {}", branchId, date);
		}
		return availabilityEntities;
	}

	/**
	 * @param branchId
	 * @param date
	 * @param map
	 * @return
	 */
	private List<AvailabilityEntity> performDateSearch(Integer branchId, Date date,
			Map<Date, List<AvailabilityEntity>> map) {
		List<AvailabilityEntity> availabilityEntities =null;
		LOGGER.info("DATE::::::: {}",date);
		LOGGER.info("MAPPP::::::::{}",map);
		Optional<Date> optionalDate =  map.keySet().stream().filter(key -> key.toString().contentEquals(date.toString())).findAny();
		if(optionalDate.isPresent()) {
			availabilityEntities =  getAvailabilityForBranch(branchId).get(optionalDate.get());
			
		}else {
			LOGGER.info("DATE::::::: {}",optionalDate);
		}
		
		return availabilityEntities;
	}

	@Override
	public void removeAvailability(AvailabilityEntity availabilityEntity) {
		LOGGER.info("Removing the slot from db: {}.", availabilityEntity);
		availabilityRepository.delete(availabilityEntity);
	}

}
