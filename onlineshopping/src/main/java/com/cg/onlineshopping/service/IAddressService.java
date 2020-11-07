package com.cg.onlineshopping.service;

import java.util.List;

import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;


public interface IAddressService {

	public Address addAddress(Address add) throws AddressAlreadyExistsException;
	public Address updateAddress(Address add) throws AddressNotFoundException;
	public Address removeAddress(Address add) throws AddressNotFoundException;
	public List<Address> viewAllAddress() ;
	public Address  viewAddress(Address add);
	public Address findById(int addressId) throws AddressNotFoundException;
	
	
	
}