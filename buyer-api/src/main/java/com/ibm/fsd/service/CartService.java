package com.ibm.fsd.service;


import java.util.List;

import com.ibm.fsd.entity.Cart;

/**
 * Cart Service
 */
public interface CartService {

	List<Cart> findAll();
	Cart create(Cart cart);
	Cart findByProductCode(String productCode);
	void delete(String productCode);
	void deleteUserId(int userId);
}
