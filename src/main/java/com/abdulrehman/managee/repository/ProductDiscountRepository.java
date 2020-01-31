package com.abdulrehman.managee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulrehman.managee.model.ProductDiscount;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Long> {

}
