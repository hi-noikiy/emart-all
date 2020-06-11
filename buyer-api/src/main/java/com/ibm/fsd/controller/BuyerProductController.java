package com.ibm.fsd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.fsd.entity.Category;
import com.ibm.fsd.entity.Manufacturer;
import com.ibm.fsd.entity.ProductEntity;
import com.ibm.fsd.models.Pages;
import com.ibm.fsd.service.BuyerProductService;
import com.ibm.fsd.service.CategoryService;
import com.ibm.fsd.service.ManufacturerService;


/**
 * 
 * buyer product controller
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class BuyerProductController {
	
	@Autowired
	BuyerProductService buyerProductService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ManufacturerService manufacturerService;
	
	/**
	 * product all
	 * 
	 * @param page
	 * @param size
	 * 
	 * @return all data
	 */
	@RequestMapping(value = "/buyer/product", method = RequestMethod.GET)
	public Page<ProductEntity> findAll(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "3") Integer size) {
		return buyerProductService.findAll(PageRequest.of(page - 1, size));
	}
	
	/**
	 * product list
	 */
	@RequestMapping(value = "/buyer/search", method = RequestMethod.POST)
	public Page<ProductEntity> findAllProduct(@Valid @RequestBody Pages pages) {
		return buyerProductService.findAllProduct(pages, 
				PageRequest.of(pages.getPage() - 1, pages.getSize()));
	}
	
	/**
	 * product one
	 */
	@RequestMapping(value = "/buyer/product/{productCode}", method = RequestMethod.GET)
	public ResponseEntity<ProductEntity> findOne(@PathVariable("productCode") String productCode) {
		return ResponseEntity.ok(buyerProductService.findByOne(productCode));
	}
	
	/**
	 * 
	 * category type
	 */
	@RequestMapping(value = "/buyer/category", method = RequestMethod.GET)
	public List<Category> category() {
        return categoryService.findAll();
	}
	
	/**
	 * 
	 * manufacturer type
	 */
	@RequestMapping(value = "/buyer/manufacturer", method = RequestMethod.GET)
	public List<Manufacturer> manufacturer() {
        return manufacturerService.findAll();
	}
}
