package com.ibm.fsd.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.fsd.entity.PurchaseHistory;


/**
 * Purchase History Repository
 */
@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, String> {
	
	Page<PurchaseHistory> findByUserIdOrderByProductCodeAsc(int userId, Pageable pageable);
	PurchaseHistory findById(int id);
}
