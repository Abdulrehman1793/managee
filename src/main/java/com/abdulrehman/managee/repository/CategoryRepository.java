package com.abdulrehman.managee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulrehman.managee.model.Category;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Feb 5, 2020
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
