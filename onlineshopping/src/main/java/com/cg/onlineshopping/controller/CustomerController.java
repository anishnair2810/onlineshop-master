package com.cg.onlineshopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.exception.CustomerAlreadyExistsException;
import com.cg.onlineshopping.exception.CustomerNotFoundException;

import com.cg.onlineshopping.model.CreateCustomerRequest;
import com.cg.onlineshopping.model.CustomerDetails;

import com.cg.onlineshopping.model.UpdateCustomerRequest;

import com.cg.onlineshopping.service.ICustomerService;
import com.cg.onlineshopping.util.CustomerUtil;

@RestController
public class CustomerController{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private ICustomerService service;
	
	@Autowired
	private CustomerUtil customerutil;
	

	@PostMapping("customer/add")
	public ResponseEntity<CustomerDetails> addCustomer(@RequestBody @Valid CreateCustomerRequest requestData)
			throws CustomerAlreadyExistsException {
		try {
			Customer cust=service.addCustomer(new Customer(requestData.getFirstName(),requestData.getLastName(),requestData.getMobileNumber(),requestData.getEmail(),requestData.getAddress()));
			cust = service.addCustomer(cust);
			CustomerDetails details = customerutil.toDetails(cust);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to add customer:{} errorlog: ", requestData.getFirstName(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("customer/update")
	public ResponseEntity<CustomerDetails> update(@RequestBody @Valid UpdateCustomerRequest requestData)
			throws CustomerNotFoundException {
		try {
			Customer cust=service.addCustomer(new Customer(requestData.getFirstName(),requestData.getLastName(),requestData.getMobileNumber(),requestData.getEmail(),requestData.getAddress()));
			cust.setCustomerId(requestData.getCustomerId());
			cust = service.updateCustomer(cust);
			CustomerDetails details = customerutil.toDetails(cust);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to update address for customerId:{} errlog: ", requestData.getCustomerId(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	@RequestMapping("customer/viewall")
	public ResponseEntity<List<Customer>> viewAllCustomers() {
		try {
			return new ResponseEntity<>(service.viewAllCustomers(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to view all the customers: ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("customer/get/id/{id}")
	public ResponseEntity<CustomerDetails> viewCustomer(@PathVariable("id") Integer customerId)
			throws CustomerNotFoundException {
		try {
			Customer cust = service.viewCustomer(customerId);
			CustomerDetails details = customerutil.toDetails(cust);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to view customer for customerId:{} errlog", customerId, e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("customer/remove/{customerId}")
	public ResponseEntity<Void> removeCustomer(@PathVariable("customerId") Integer customerId)
			throws CustomerNotFoundException {
		try {
			service.removeCustomer( customerId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			LOGGER.error("unable to delete customer for customerId:{} errlog",  customerId, e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
}