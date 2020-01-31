package com.abdulrehman.managee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulrehman.managee.model.ProductPriceHistory;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 29, 2020
 */
@Repository
public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory, Long> {

}
