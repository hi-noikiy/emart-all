package com.ibm.fsd.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsd.entity.PurchaseHistory;
import com.ibm.fsd.repository.PurchaseHistoryRepository;
import com.ibm.fsd.service.PurchaseHistoryService;

/**
 * 
 * Purchase History service
 *
 */
@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

	@Autowired
	PurchaseHistoryRepository purchaseHistoryRepository;
	
	@Override
	public Page<PurchaseHistory> findAll(Pageable pageable, int userId) {
		return purchaseHistoryRepository.findByUserIdOrderByProductCodeAsc(userId, pageable);
	}

	@Override
	@Transactional
	public void save(List<PurchaseHistory> list) {
		for (PurchaseHistory data : list) {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			data.setPurchaseDate(dateformat.format(System.currentTimeMillis()));
			purchaseHistoryRepository.save(data);
		}
	}

	@Override
	public PurchaseHistory findByOne(int id) {
		return purchaseHistoryRepository.findById(id);
	}
}
