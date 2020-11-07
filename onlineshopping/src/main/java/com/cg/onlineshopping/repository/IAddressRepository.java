package com.cg.onlineshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;

public interface IAddressRepository extends JpaRepository<Address, Integer>{
	
	
	
}