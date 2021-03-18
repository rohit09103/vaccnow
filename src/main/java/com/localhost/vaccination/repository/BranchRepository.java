/**
 * 
 */
package com.localhost.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localhost.vaccination.entity.BranchEntity;

/**
 * Repository fto fetch branch data from DB.
 * @author Rohit
 *
 */
@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

}
