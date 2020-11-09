package com.cg.onlineshopping.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.onlineshopping.entities.Customer;

public class CustomerControllerTest extends CustomerAbstractTest {
	 @Override
	   @Before
	   public void setUp() {
	      super.setUp();
	   }
	   @Test
	   public void viewAllCustomers() throws Exception {
	      String uri = "/customers";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      Customer[] productlist = super.mapFromJson(content, Customer[].class);
	      assertTrue(productlist.length > 0);
	   }
	   @Test
	   public void addCustomer() throws Exception {
	      String uri = "/customers";
	      Customer cust = new Customer(null, null, null, null, null);
			cust.setFirstName("Anish");
			cust.setLastName("Nair");
			cust.setMobileNumber("9893860242");
			cust.setEmail("anishnair.32@gmail.com");
			cust.setAddress(null);
			
	      	      String inputJson = super.mapToJson(cust);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Customer is added successfully");
	   }
	   @Test
	   public void updateCustomer() throws Exception {
	      String uri = "/customers/2";
	      Customer cust = new Customer(null, null, null, null, null);
	      cust.setFirstName("Anish");
			cust.setLastName("Nair");
			cust.setMobileNumber("9893860242");
			cust.setEmail("anishnair.32@gmail.com");
			cust.setAddress(null);
	      String inputJson = super.mapToJson(cust);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Customer is updated successsfully");
	   }
	   @Test
	   public void deleteCustomer() throws Exception {
	      String uri = "/customers/2";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Customer is deleted successsfully");
	   }
	}

