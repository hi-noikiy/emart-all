package com.ibm.fsd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsd.entity.ProductEntity;
import com.ibm.fsd.exception.BusinessException;
import com.ibm.fsd.repository.CategoryRepository;
import com.ibm.fsd.repository.SellerProductRepository;
import com.ibm.fsd.service.SellerProductService;


@Service
public class SellerProductServiceImpl implements SellerProductService {

	@Autowired
	SellerProductRepository sellerProductRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Page<ProductEntity> findAll(Pageable pageable) {
		return sellerProductRepository.findAll(pageable);
	}

	@Override
	public ProductEntity findByOne(String code) {
		return sellerProductRepository.findByCode(code);
	}

	@Override
	@Transactional
	public ProductEntity save(ProductEntity entity) {
		return sellerProductRepository.save(entity);
	}

	@Override
	@Transactional
	public int update(ProductEntity entity) {
		return sellerProductRepository.update(entity);
	}

	@Override
	@Transactional
	public void delete(String code) {
		ProductEntity entity = findByOne(code);
		if (entity == null) throw new BusinessException("10", "Product does not exit!");
		sellerProductRepository.delete(entity);
	}

	@Override
	public Page<ProductEntity> findAllProductCodeOrCategoryCode(String productCode, String categoryCode,
			Pageable pageable) {
		return sellerProductRepository.findByCodeOrCategoryCodeOrderByCodeAsc(productCode, categoryCode, pageable);
	}
}
