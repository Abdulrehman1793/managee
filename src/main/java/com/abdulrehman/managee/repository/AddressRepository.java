package com.abdulrehman.managee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulrehman.managee.model.Address;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 21, 2020
 */
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
