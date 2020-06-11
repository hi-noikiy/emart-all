package com.ibm.fsd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ibm.fsd.entity.Cart;
import com.ibm.fsd.service.CartService;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class CartController {
    
	@Autowired
	CartService cartService;
	
	/**
	 * cart find all
	 */
	@RequestMapping(value = "/buyer/cart", method = RequestMethod.GET)
	public ResponseEntity<List<Cart>> findAll() {
		return ResponseEntity.ok(cartService.findAll());
	}
	
	/**
	 * add cart
	 */
	@RequestMapping(value = "/buyer/addCart", method = RequestMethod.POST)
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
		Cart one = cartService.findByProductCode(cart.getProductCode());
		if (one != null) {
			one.setQuantity(cart.getQuantity());
			return ResponseEntity.ok(cartService.create(one));
		}
		return ResponseEntity.ok(cartService.create(cart));
	}
	
	/**
	 * delete cart 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/buyer/deleteCart/{productCode}", method = RequestMethod.DELETE)
	public ResponseEntity deleteCart(@PathVariable("productCode") String productCode) {
		cartService.delete(productCode);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * delete cart 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/buyer/deleteUserId/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("userId") int userId) {
		cartService.deleteUserId(userId);
		return ResponseEntity.ok().build();
	}

}
