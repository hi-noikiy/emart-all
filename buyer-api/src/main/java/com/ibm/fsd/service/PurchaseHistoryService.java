package com.ibm.fsd.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ibm.fsd.entity.PurchaseHistory;

/**
 * Purchase History Service
 */
public interface PurchaseHistoryService {

	Page<PurchaseHistory> findAll(Pageable pageable, int userId);
	void save(List<PurchaseHistory> list);
	PurchaseHistory findByOne(int id);
}
