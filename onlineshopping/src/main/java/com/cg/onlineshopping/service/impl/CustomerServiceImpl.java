package com.cg.onlineshopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.CustomerAlreadyExistsException;
import com.cg.onlineshopping.exception.CustomerNotFoundException;
import com.cg.onlineshopping.repository.ICustomerRepository;
import com.cg.onlineshopping.service.ICustomerService;


@Transactional
@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerRepository repo;
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public Customer addCustomer(Customer cust) throws CustomerAlreadyExistsException {
		LOGGER.info("adding Customer for user with customerId:{}", cust.getCustomerId());
		boolean exists = cust.getCustomerId() != 0 && repo.existsById(cust.getCustomerId());
		if (exists) {
			throw new CustomerAlreadyExistsException(
					String.format("address already exists for id= %d", cust.getCustomerId()));
		}
		cust = repo.save(cust);
		return cust;
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
	public List<Customer> viewAllCustomers() throws Exception {
		List<Customer> addresses=new ArrayList<Customer>();
		repo.findAll().forEach(addresses::add);
		

		return addresses;
	}

	@Override
	public Customer removeCustomer(Integer customerId) throws CustomerNotFoundException {
		LOGGER.info("removing Customer with customerId : {}", customerId);
		repo.deleteById(customerId);
		return null;
	}

	@Override
	public Customer findById(Integer customerId) throws CustomerNotFoundException {
		LOGGER.info("viewing customer with customerId:{}", customerId);
		Optional<Customer> optional=repo.findById(customerId);
		if(!optional.isPresent())
			throw new CustomerNotFoundException("Can't find, Customer not found for id="+customerId);
	return optional.get();
		
	}


	@Override
	public Customer viewCustomer(Integer customerId) {
		LOGGER.info("viewing customer with customerId:{}", customerId);
		Optional<Customer> optional=repo.findById(customerId);
		if(!optional.isPresent())
			throw new CustomerNotFoundException("Can't find, Customer not found for id="+customerId);
	return optional.get();
		
	}
	}




