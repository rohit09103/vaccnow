/**
 * 
 */
package com.localhost.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localhost.vaccination.entity.VaccinesInBranchEntity;

/**
 * Repository to fetch Vaccine data from DB.
 * @author Rohit
 *
 */
@Repository
public interface VaccineInBrnachReposityory extends JpaRepository<VaccinesInBranchEntity, Integer> {

}
