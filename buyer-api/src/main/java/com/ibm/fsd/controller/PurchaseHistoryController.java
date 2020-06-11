package com.ibm.fsd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ibm.fsd.entity.PurchaseHistory;
import com.ibm.fsd.service.PurchaseHistoryService;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class PurchaseHistoryController {
    
	@Autowired
	PurchaseHistoryService purchaseHistoryService;
	
	/**
	 * purchase history find all
	 */
	@RequestMapping(value = "/buyer/purchaseHistory", method = RequestMethod.GET)
	public Page<PurchaseHistory> findAll(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "3") Integer size,
            @RequestParam(value = "userId", defaultValue = "0") Integer userId) {
		return purchaseHistoryService.findAll(PageRequest.of(page - 1, size), userId);
	}
	
	/**
	 * purchase history add
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/buyer/purchaseHistoryAdd", method = RequestMethod.POST)
	public ResponseEntity purchaseHistoryAdd(@RequestBody List<PurchaseHistory> purchaseHistory) {
		purchaseHistoryService.save(purchaseHistory);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * evaluate find one
	 */
	@RequestMapping(value = "/buyer/purchaseHistory/{id}", method = RequestMethod.GET)
	public ResponseEntity<PurchaseHistory> findByOne(@PathVariable("id") int id) {
		return ResponseEntity.ok(purchaseHistoryService.findByOne(id));
	}
}
