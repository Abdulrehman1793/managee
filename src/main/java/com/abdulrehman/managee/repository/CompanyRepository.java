package com.abdulrehman.managee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query(value = "Select c.* from Company c, company_user_lnk cul Where c.id = cul.comp_id And cul.user_id = :userId", nativeQuery = true)
	List<Company> findAllCompanyByUserId(@Param("userId") Long userId);
}
