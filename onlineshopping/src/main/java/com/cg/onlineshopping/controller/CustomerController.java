package com.cg.onlineshopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineshopping.dto.AddressDetails;
import com.cg.onlineshopping.dto.CreateAddressRequest;
import com.cg.onlineshopping.dto.CreateCustomerRequest;
import com.cg.onlineshopping.dto.CustomerDetails;
import com.cg.onlineshopping.dto.UpdateAddressRequest;
import com.cg.onlineshopping.dto.UpdateCustomerRequest;
import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;
import com.cg.onlineshopping.exception.CustomerAlreadyExistsException;
import com.cg.onlineshopping.exception.CustomerNotFoundException;
import com.cg.onlineshopping.service.ICustomerService;
import com.cg.onlineshopping.util.CustomerUtil;

@RestController
public class CustomerController{
	@Autowired
	private ICustomerService service;
	
	@Autowired
	private CustomerUtil customerutil;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public CustomerDetails add(@RequestBody  @Valid CreateCustomerRequest requestData) throws CustomerAlreadyExistsException {
		Customer cust=service.addCustomer(new Customer(requestData.getFirstName(),requestData.getLastName(),requestData.getMobileNumber(),requestData.getEmail(),requestData.getAddress()));
		return customerutil.toDetails(cust);
	}
	@PutMapping("/update")
	public CustomerDetails update(@RequestBody UpdateCustomerRequest requestData ) throws AddressNotFoundException
	{
		Customer cust=new Customer(requestData.getFirstName(),requestData.getLastName(),requestData.getMobileNumber(),requestData.getEmail(),requestData.getAddress());
		cust.setCustomerId(requestData.getCustomerId());
		cust=service.updateCustomer(cust);
		return customerutil.toDetails(cust);
		
	}
	@RequestMapping("/viewall")
	public List<Customer> viewAllCustomers(){
		return service.viewAllCustomers();
	}
	@DeleteMapping("/remove/{id}")
	public Customer deleteCustomer(@PathVariable Customer cust) throws CustomerNotFoundException {
		return service.removeCustomer(cust);
	
	}
	
}