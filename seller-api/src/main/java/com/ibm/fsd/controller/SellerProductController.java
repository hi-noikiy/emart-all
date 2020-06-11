package com.ibm.fsd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.ibm.fsd.exception.ErrorConstants;
import com.ibm.fsd.exception.RequestException;
import com.ibm.fsd.models.Pages;
import com.ibm.fsd.service.CategoryService;
import com.ibm.fsd.service.ManufacturerService;
import com.ibm.fsd.service.SellerProductService;


/**
 * 
 * Seller product controller
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class SellerProductController {
	
	@Autowired
	SellerProductService sellerProductService;
	
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
	@RequestMapping(value = "/seller/product", method = RequestMethod.GET)
	public Page<ProductEntity> findAll(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "3") Integer size) {
		return sellerProductService.findAll(PageRequest.of(page - 1, size));
	}
	
	/**
	 * find category
	 * 
	 * @param page
	 * @param size
	 * 
	 * @return all data
	 */
	@RequestMapping(value = "/seller/search", method = RequestMethod.POST)
	public Page<ProductEntity> findAllProductCodeOrCategoryCode(@Valid @RequestBody Pages pages) {
		return sellerProductService.findAllProductCodeOrCategoryCode(
				pages.getCode(), pages.getType(), PageRequest.of(pages.getPage() - 1, pages.getSize()));
	}

	/**
	 * product one
	 */
	@RequestMapping(value = "/seller/product/{productCode}", method = RequestMethod.GET)
	public ResponseEntity<ProductEntity> findOne(@PathVariable("productCode") String productCode) {
		return ResponseEntity.ok(sellerProductService.findByOne(productCode));
	}
	
	/**
	 * product add
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/seller/productAdd", method = RequestMethod.POST)
	public ResponseEntity productAdd(@Valid @RequestBody ProductEntity entity, BindingResult bindingResult) {
		
		// select product code exist
		ProductEntity productCodeNotExists = sellerProductService.findByOne(entity.getCode());
		
		if (productCodeNotExists != null) {
			bindingResult.rejectValue("code", "400001",
                            "the product code is existed.");
        }
		if (bindingResult.hasErrors()) {
            //return ResponseEntity.badRequest().body(bindingResult);
			throw new RequestException(ErrorConstants.ERROR_REQUEST_PARAM, bindingResult);
        }
		return ResponseEntity.ok(sellerProductService.save(entity));
	}
	
	/**
	 * product edit
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/seller/productEdit", method = RequestMethod.PUT)
	public ResponseEntity productEdit(@Valid @RequestBody ProductEntity entity) {
		return ResponseEntity.ok(sellerProductService.update(entity));
	}
	
	/**
	 * product delete
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/seller/productDelete/{productCode}", method = RequestMethod.DELETE)
	public ResponseEntity productDelete(@PathVariable("productCode") String productCode) {
		sellerProductService.delete(productCode);
        return ResponseEntity.ok().build();
	}
	
	/**
	 * 
	 * category type
	 */
	@RequestMapping(value = "/seller/category", method = RequestMethod.GET)
	public List<Category> category() {
        return categoryService.findAll();
	}
	
	/**
	 * 
	 * manufacturer type
	 */
	@RequestMapping(value = "/seller/manufacturer", method = RequestMethod.GET)
	public List<Manufacturer> manufacturer() {
        return manufacturerService.findAll();
	}
}
