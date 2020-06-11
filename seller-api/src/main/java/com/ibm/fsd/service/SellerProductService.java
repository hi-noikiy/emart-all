package com.ibm.fsd.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ibm.fsd.entity.ProductEntity;

/**
 * 
 * ProductService
 *
 */
public interface SellerProductService {

	Page<ProductEntity> findAll(Pageable pageable);
	Page<ProductEntity> findAllProductCodeOrCategoryCode(
			String productCode, String categoryCode, Pageable pageable);
	ProductEntity findByOne(String code);
	ProductEntity save(ProductEntity entity);
	int update(ProductEntity entity);
	void delete(String code);
}
