package com.ibm.fsd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsd.entity.Category;
import com.ibm.fsd.repository.CategoryRepository;


/**
 * 
 * category service
 *
 */
@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	/**
	 * category find all
	 */
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	/**
	 * category find one
	 */
	public Category findAll(String categoryCode) {
		return categoryRepository.findByCategoryCode(categoryCode);
	} 
	
	/**
	 * category save
	 * 
	 * @param category
	 * @return category
	 */
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
