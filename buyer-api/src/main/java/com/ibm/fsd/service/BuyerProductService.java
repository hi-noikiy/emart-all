package com.ibm.fsd.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ibm.fsd.entity.ProductEntity;
import com.ibm.fsd.models.Pages;


public interface BuyerProductService {

	Page<ProductEntity> findAll(Pageable pageable);
	Page<ProductEntity> findAllProduct(Pages pages, Pageable pageable);
	ProductEntity findByOne(String code);
}
