package com.cg.onlineshopping.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cg.onlineshopping.exception.CustomerAlreadyExistsException;
import com.cg.onlineshopping.exception.CustomerNotFoundException;
import com.cg.onlineshopping.util.CustomerUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cg.onlineshopping.model.CreateCustomerRequest;


import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.model.CreateCustomerRequest;
import com.cg.onlineshopping.service.ICustomerService;
import com.cg.onlineshopping.service.impl.CustomerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

	@Mock
	ICustomerService service;

	@Mock
	CustomerUtil customerutil;

	@InjectMocks
	CustomerController controller;

	
	   @Before
	   public void setUp() {
		      
	   }
	   
	   @Test
	   public void testAddCustomerSuccess() {
		   //Mock Service Layer
		   
		    Address add = new Address();
			add.setStreetNo("15th");
			add.setBuildingName("Asian");
			add.setCity("hissar");
			add.setState("Haryana");
			add.setCountry("India");
			add.setPincode("122022");
			Customer cust1 = new Customer("Anish", "Nair", "12345", "Sample@Gmail.com", add);
			cust1.setCustomerId(123);
			when(service.addCustomer(Mockito.any(Customer.class))).thenReturn(cust1);

			//Create Sample CreateCustomerRequest that needs to be passed in controller
			CreateCustomerRequest req = new CreateCustomerRequest();
			req.setAddress(add);
			req.setEmail("Sample@Gmail.com");
			req.setFirstName("Anish");
			req.setLastName("Nair");
			req.setMobileNumber("12345");
			
			//Call Controller and assert
			Assert.assertEquals(HttpStatus.OK, controller.addCustomer(req).getStatusCode());
			
	   }

	   @Test
	   public void testAddCustomerFailure() {
	   	//Mock Service Layer
		   when(service.addCustomer(Mockito.any(Customer.class))).thenThrow(new CustomerAlreadyExistsException("Sample error"));

		   //Create Sample CreateCustomerRequest that needs to be passed in controller
		   CreateCustomerRequest req = new CreateCustomerRequest();
		   req.setEmail("Sample@Gmail.com");
		   req.setFirstName("Anish");
		   req.setLastName("Nair");
		   req.setMobileNumber("12345");

		   //Call controller and assert
		   Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.addCustomer(req).getStatusCode());
	   }
	   
	   
	   @Test
	   public void testViewAllCustomersSuccessRequest() throws Exception {
		   
		   //Mock service layer call
		   Customer cust1 = new Customer("Anish", "Nair", "12345", "Sample@Gmail.com", null);
		   Customer cust2 = new Customer("Anish2", "Nair2", "12345", "Sample2@Gmail.com", null);
		   List<Customer> allCustList = new ArrayList();
		   allCustList.add(cust1);
		   allCustList.add(cust2);
		   when(service.viewAllCustomers()).thenReturn(allCustList);
		   
		   //Call controller and assert
		   Assert.assertEquals(HttpStatus.OK, controller.viewAllCustomers().getStatusCode());
		   
	   }
	   
	   @Test
	   public void testViewAllCustomersFailureScenario() throws Exception {
		   
		   //Mock Service Layer exception
		   when(service.viewAllCustomers()).thenThrow(new Exception("Sample error"));
		   
		   //Call Controller
		   ResponseEntity<List<Customer>> response = controller.viewAllCustomers();
		   
		   //Assert
		   Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	   }

	   @Test
	   public void testDeleteCustomerSuccessScenario() {
		   //Mock Service Layer
		   Customer cust1 = new Customer("Anish", "Nair", "12345", "Sample@Gmail.com", null);
		   List<Customer> allCustList = new ArrayList();
		   allCustList.add(cust1);
		   when(service.removeCustomer(cust1.getCustomerId())).thenReturn(null);
		   //Call Controller
		   ResponseEntity<Void> response = controller.removeCustomer(cust1.getCustomerId());

		   //Call controller and assert
		   Assert.assertEquals(HttpStatus.OK, controller.removeCustomer(cust1.getCustomerId()).getStatusCode());
	   }
	@Test
	public void testDeleteCustomerFailureScenario() {

		//Mock Service Layer exception
		Customer cust1 = new Customer("Anish", "Nair", "12345", "Sample@Gmail.com", null);
		List<Customer> allCustList = new ArrayList();
		allCustList.add(cust1);
		when(service.removeCustomer(cust1.getCustomerId())).thenThrow(new CustomerNotFoundException("Sample error"));

		//Call Controller
		ResponseEntity<Void> response = controller.removeCustomer(cust1.getCustomerId());

		//Assert
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	/*
		 * @Test public void addCustomer() throws Exception { String uri = "/customers";
		 * Customer cust = new Customer(null, null, null, null, null);
		 * cust.setFirstName("Anish"); cust.setLastName("Nair");
		 * cust.setMobileNumber("9893860242"); cust.setEmail("anishnair.32@gmail.com");
		 * cust.setAddress(null);
		 * 
		 * String inputJson = super.mapToJson(cust); MvcResult mvcResult =
		 * mvc.perform(MockMvcRequestBuilders.post(uri)
		 * .contentType(MediaType.APPLICATION_JSON_VALUE)
		 * .content(inputJson)).andReturn();
		 * 
		 * int status = mvcResult.getResponse().getStatus(); assertEquals(201, status);
		 * String content = mvcResult.getResponse().getContentAsString();
		 * assertEquals(content, "Customer is added successfully"); }
		 * 
		 * @Test public void updateCustomer() throws Exception { String uri =
		 * "/customers/2"; Customer cust = new Customer(null, null, null, null, null);
		 * cust.setFirstName("Anish"); cust.setLastName("Nair");
		 * cust.setMobileNumber("9893860242"); cust.setEmail("anishnair.32@gmail.com");
		 * cust.setAddress(null); String inputJson = super.mapToJson(cust); MvcResult
		 * mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
		 * .contentType(MediaType.APPLICATION_JSON_VALUE)
		 * .content(inputJson)).andReturn();
		 * 
		 * int status = mvcResult.getResponse().getStatus(); assertEquals(200, status);
		 * String content = mvcResult.getResponse().getContentAsString();
		 * assertEquals(content, "Customer is updated successsfully"); }
		 * 
		 * @Test public void deleteCustomer() throws Exception { String uri =
		 * "/customers/2"; MvcResult mvcResult =
		 * mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn(); int status =
		 * mvcResult.getResponse().getStatus(); assertEquals(200, status); String
		 * content = mvcResult.getResponse().getContentAsString(); assertEquals(content,
		 * "Customer is deleted successsfully"); }
		 */
	}

