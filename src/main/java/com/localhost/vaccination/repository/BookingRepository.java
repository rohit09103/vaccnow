/**
 * 
 */
package com.localhost.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localhost.vaccination.entity.BookingEntity;

/**
 * Repository to fetch data from DB.
 * @author Rohit
 *
 */
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

}
