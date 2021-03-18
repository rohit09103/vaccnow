/**
 * 
 */
package com.localhost.vaccination.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.localhost.vaccination.entity.AvailabilityEntity;
import com.localhost.vaccination.model.Availability;
import com.localhost.vaccination.model.AvailabilityDatesAndSlot;

/**
 * Class to hold methods which map model to entity and vice-versa for
 * availability
 * 
 * @author Rohit
 *
 */
@Component
public class AvailabilityMapper {

	/**
	 * Method to transform the availability entity to availability model so it can
	 * be sent as http response.
	 * 
	 * @param branchEntity
	 * @return
	 */
	public Availability mapAvailabilityEntityToModel(Map<Date, List<AvailabilityEntity>> availabilityEntitiesMap) {
		if (null != availabilityEntitiesMap && !availabilityEntitiesMap.isEmpty()) {
			AvailabilityEntity availabilityEntity = availabilityEntitiesMap.values().stream().limit(1)
					.collect(Collectors.toList()).get(0).get(0);
			Availability availability = new Availability(availabilityEntity.getBranch().getId(),
					availabilityEntity.getBranch().getLocation(), availabilityEntity.getBranch().getName(),
					availabilityEntity.getBranch().getPhone());
			availability.setDates(availabilityEntitiesMap.keySet().stream()
					.map(entity -> mapEntityToModel(availabilityEntitiesMap.get(entity))).collect(Collectors.toList()));
			return availability;
		}
		return null;
	}

	/**
	 * Method to map each field from entity to model.
	 * 
	 * @param entity
	 * @param branchs
	 * @return
	 */
	private AvailabilityDatesAndSlot mapEntityToModel(List<AvailabilityEntity> entity) {
		AvailabilityDatesAndSlot availabilityDatesAndSlot = new AvailabilityDatesAndSlot();
		availabilityDatesAndSlot.setDate(entity.get(0).getDate());
		List<String> slots = new ArrayList<>();
		entity.forEach(timeSlot -> slots.add(timeSlot.getSlot()));
		availabilityDatesAndSlot.setSlots(slots);
		return availabilityDatesAndSlot;
	}

}
