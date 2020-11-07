package com.cg.onlineshopping.service;

import java.util.List;

import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.exception.CustomerAlreadyExistsException;
import com.cg.onlineshopping.exception.CustomerNotFoundException;


public interface ICustomerService {

	public Customer addCustomer(Customer cust) throws CustomerAlreadyExistsException;
	public Customer updateCustomer(Customer cust) throws CustomerNotFoundException;
	public Customer removeCustomer(Customer cust) throws CustomerNotFoundException;
	public List<Customer> viewAllCustomers() ;
	public Customer  viewAddress(Customer cust);
	public Customer findById(int customerId) throws CustomerNotFoundException;

}
