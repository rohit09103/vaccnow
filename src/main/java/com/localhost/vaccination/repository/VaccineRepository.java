/**
 * 
 */
package com.localhost.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localhost.vaccination.entity.VaccineEntity;

/**
 * 
 * Repository to fetch Vaccine Data in db.
 * @author Rohit
 *
 */
@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity, Integer> {

}
