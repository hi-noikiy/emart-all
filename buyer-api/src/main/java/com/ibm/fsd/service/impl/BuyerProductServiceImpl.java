package com.ibm.fsd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ibm.fsd.entity.ProductEntity;
import com.ibm.fsd.models.Pages;
import com.ibm.fsd.repository.BuyerProductRepository;
import com.ibm.fsd.service.BuyerProductService;

/**
 * 
 * buyer product service
 *
 */
@Service
public class BuyerProductServiceImpl implements BuyerProductService {

	@Autowired
	BuyerProductRepository buyerProductRepository;
	
	@Override
	public Page<ProductEntity> findAll(Pageable pageable) {
		return buyerProductRepository.findAll(pageable);
	}

	@Override
	public Page<ProductEntity> findAllProduct(Pages pages, Pageable pageable) {
		return buyerProductRepository.findAllList(pages, pageable);
	}
	
	@Override
	public ProductEntity findByOne(String code) {
		return buyerProductRepository.findByCode(code);
	}
}
