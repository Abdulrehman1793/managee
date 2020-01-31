package com.abdulrehman.managee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulrehman.managee.model.Product;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
