package com.ibm.fsd.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ibm.fsd.SellerApiApplication;
import com.ibm.fsd.entity.ProductEntity;
import com.ibm.fsd.models.Pages;
import com.ibm.fsd.service.CategoryService;
import com.ibm.fsd.service.JwtService;
import com.ibm.fsd.service.ManufacturerService;
import com.ibm.fsd.service.SecurityService;
import com.ibm.fsd.service.SellerProductService;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//import static org.hamcrest.Matchers.equalTo;

/**
 * user test
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellerApiApplication.class)
public class SellerProductControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	SellerProductService sellerProductService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ManufacturerService manufacturerService;

	@Autowired
	JwtService jwtService;

	@Autowired
	SecurityService securityService;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void test_seller_product() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/seller/product?page=1&size=3")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	@Test
	public void test_seller_search() throws Exception {

		Pages page = new Pages();
		page.setType("1");
		page.setCode("0001");
		page.setPage(1);
		page.setSize(5);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(page);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/seller/search")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	@Test
	public void test_seller_product_code() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/seller/product/0001")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	@Test
	public void test_seller_productAdd() throws Exception {

		// add
		this.testAdd();

		// delete
		this.testDelete();
	}

	@Test
	public void test_seller_productAdd_error() throws Exception {

		// add
		this.testAdd();

		// add
		this.testAdd();

		// delete
		this.testDelete();
	}

	@Test
	public void test_seller_productEdit() throws Exception {

		// add
		this.testAdd();

		ProductEntity entity = new ProductEntity();
		entity.setCode("test1");
		entity.setName("testName");
		entity.setDescription("testDescription1");
		entity.setPrice(2);
		entity.setStock(2);
		entity.setDiscount(1);
		entity.setSalesVolume(11);
		entity.setActive("0");
		entity.setCategoryCode("1");
		entity.setManufacturerCode("1");
		entity.setImageUrl("https://images-na.ssl-images-amazon.com/images/I/51S8VRHA2FL._SX357_BO1,204,203,200_.jpg");
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(entity);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/seller/productEdit")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);

		// delete
		this.testDelete();
	}

	@Test
	public void test_seller_category() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/seller/category")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	@Test
	public void test_seller_manufacturer() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/seller/manufacturer")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	private void testAdd() throws Exception {

		ProductEntity entity = new ProductEntity();
		entity.setCode("test1");
		entity.setName("testName");
		entity.setDescription("testDescription");
		entity.setPrice(1);
		entity.setStock(1);
		entity.setDiscount(0);
		entity.setSalesVolume(10);
		entity.setActive("0");
		entity.setCategoryCode("1");
		entity.setManufacturerCode("1");
		entity.setImageUrl("https://images-na.ssl-images-amazon.com/images/I/51S8VRHA2FL._SX357_BO1,204,203,200_.jpg");
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(entity);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/seller/productAdd")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	private void testDelete() throws Exception {
		MvcResult delResult = mvc.perform(MockMvcRequestBuilders.delete("/api/seller/productDelete/test1")).andReturn();

		int status = delResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

}