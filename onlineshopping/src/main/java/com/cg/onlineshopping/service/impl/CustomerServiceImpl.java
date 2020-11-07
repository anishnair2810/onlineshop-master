package com.cg.onlineshopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.exception.CustomerAlreadyExistsException;
import com.cg.onlineshopping.exception.CustomerNotFoundException;
import com.cg.onlineshopping.repository.ICustomerRepository;
import com.cg.onlineshopping.service.ICustomerService;


@Transactional
@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerRepository repo;

	@Override
	public Customer addCustomer(Customer cust) throws CustomerAlreadyExistsException {
		boolean exists=cust.getCustomerId()!=0 && repo.existsById(cust.getCustomerId());
		if(exists) {
			throw new CustomerAlreadyExistsException("Customer already exists for id=" + cust.getCustomerId());
		}
		cust=repo.save(cust);
		return cust;
		 
	}
	
	public Customer findById(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optional=repo.findById(customerId);
		if(!optional.isPresent())
			throw new CustomerNotFoundException("Can't find, Customer not found for id="+customerId);
	return optional.get();
	}
	public void deleteById(int customerId) throws CustomerNotFoundException {
		Customer cust=findById(customerId);
		repo.delete(cust);
	}
	@Override
	public Customer updateCustomer(Customer cust) throws CustomerNotFoundException {
		boolean exists=cust.getCustomerId()!=0 && repo.existsById(cust.getCustomerId());
		if(!exists) {
			throw new CustomerNotFoundException("Can't find, Customer not found for id="+cust.getCustomerId());
		}
		return repo.save(cust);
	}

	@Override
	public Customer removeCustomer(Customer add) throws CustomerNotFoundException {
		return add;

		
	}

	@Override
	public List<Customer> viewAllCustomers() {
		List<Customer> addresses=new ArrayList<Customer>();
		repo.findAll().forEach(addresses::add);
		

		return addresses;
	}

	@Override
	public Customer viewAddress(Customer add) {
		
		return null;
	}
}