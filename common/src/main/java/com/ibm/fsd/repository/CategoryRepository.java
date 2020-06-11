package com.ibm.fsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.fsd.entity.Category;

/**
 * category repository
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	
	/** find category code */
	Category findByCategoryCode(String categoryCode);
}
