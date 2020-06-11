package com.ibm.fsd.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.ibm.fsd.BuyerApiApplication;
import com.ibm.fsd.entity.Cart;
import com.ibm.fsd.entity.Evaluate;
import com.ibm.fsd.entity.PurchaseHistory;
import com.ibm.fsd.models.Pages;
import com.ibm.fsd.service.BuyerProductService;
import com.ibm.fsd.service.CartService;
import com.ibm.fsd.service.CategoryService;
import com.ibm.fsd.service.EvaluateService;
import com.ibm.fsd.service.JwtService;
import com.ibm.fsd.service.ManufacturerService;
import com.ibm.fsd.service.PurchaseHistoryService;
import com.ibm.fsd.service.SecurityService;

/**
 * user test
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BuyerApiApplication.class)
public class BuyerApiAllTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	BuyerProductService buyerProductService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	EvaluateService evaluateService;
	
	@Autowired
	PurchaseHistoryService purchaseHistoryService;

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
	public void test_buyer_product() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/buyer/product?page=1&size=3")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	@Test
	public void test_buyer_search() throws Exception {

		Pages page = new Pages();
		page.setType("1");
		page.setCode("0001");
		page.setPage(1);
		page.setSize(5);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(page);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/buyer/search")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	@Test
	public void test_buyer_product_code() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/buyer/product/0001")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}
	
	@Test
	public void test_buyer_category() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/buyer/category")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	@Test
	public void test_buyer_manufacturer() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/buyer/manufacturer")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}
	
	@Test
	public void test_buyer_cart() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/buyer/cart")).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}
	
	@Test
	public void test_buyer_addCart() throws Exception {

		this.test_addCart();
		
		this.test_addCart();
		
		this.test_cart_delete();
		
		this.test_cart_userId_delete();
	}
	
	@Test
	public void test_buyer_userid_deletecart() throws Exception {

		this.test_addCart();
		
		this.test_cart_userId_delete();
	}
	
	@Test
	public void test_buyer_deletecart() throws Exception {

		MvcResult delResult = mvc.perform(MockMvcRequestBuilders.delete("/api/buyer/deleteCart/aaaa")).andReturn();

		int status = delResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}
	
	@Test
	public void test_buyer_addEvaluate() throws Exception {

		Evaluate entity = new Evaluate();
		entity.setProductCode("test1");
		entity.setProductName("test");
		entity.setCommentContent("test");
		entity.setImageUrl("https://images-na.ssl-images-amazon.com/images/I/51S8VRHA2FL._SX357_BO1,204,203,200_.jpg");
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(entity);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/buyer/addEvaluate")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}
	
	@Test
	public void test_buyer_purchaseHistoryAdd() throws Exception {

		// add
		List<PurchaseHistory> purchaseHistory = new ArrayList<PurchaseHistory>();
		PurchaseHistory entity = new PurchaseHistory();
		entity.setProductCode("test1");
		entity.setProductName("test");
		entity.setImageUrl("https://images-na.ssl-images-amazon.com/images/I/51S8VRHA2FL._SX357_BO1,204,203,200_.jpg");
		entity.setPrice(12);
		entity.setQuantity(12);
		entity.setPurchaseDate("2020/06/05");
		entity.setUserId(2222);
		purchaseHistory.add(entity);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(purchaseHistory);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/buyer/purchaseHistoryAdd")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
		
		// select 
		mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/buyer/purchaseHistory?page=1&size=3&userId=2222")).andReturn();
		status = mvcResult.getResponse().getStatus();
		Assert.assertEquals("OK", 200, status);
		
		// select
		mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/buyer/purchaseHistory/1")).andReturn();
		status = mvcResult.getResponse().getStatus();
		Assert.assertEquals("OK", 200, status);
	}

	private void test_addCart() throws Exception {

		Cart cart = new Cart();
		cart.setProductCode("test1");
		cart.setProductName("name");
		cart.setPrice(1);
		cart.setStock(1);
		cart.setQuantity(1);
		cart.setDiscount(0);
		cart.setUserId(1111);
		cart.setImageUrl("https://images-na.ssl-images-amazon.com/images/I/51S8VRHA2FL._SX357_BO1,204,203,200_.jpg");
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cart);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/buyer/addCart")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

	private void test_cart_delete() throws Exception {
		MvcResult delResult = mvc.perform(MockMvcRequestBuilders.delete("/api/buyer/deleteCart/test1")).andReturn();

		int status = delResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}
	
	private void test_cart_userId_delete() throws Exception {
		MvcResult delResult = mvc.perform(MockMvcRequestBuilders.delete("/api/buyer/deleteUserId/1111")).andReturn();

		int status = delResult.getResponse().getStatus();

		// Result
		Assert.assertEquals("OK", 200, status);
	}

}