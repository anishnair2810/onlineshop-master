package com.cg.onlineshopping.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.exception.CustomerAlreadyExistsException;

public interface ICustomerRepository extends JpaRepository<Customer,Integer>{}
	
	
	