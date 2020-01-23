package com.abdulrehman.managee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulrehman.managee.model.Company;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 1, 2020
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	Optional<Company> findByDisplayName(String displayName);

	Optional<Company> findByEmail(String email);

	Optional<Company> findByCode(String code);
}
