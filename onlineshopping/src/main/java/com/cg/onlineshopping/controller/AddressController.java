package com.cg.onlineshopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
import com.cg.onlineshopping.dto.UpdateAddressRequest;
import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;
import com.cg.onlineshopping.service.IAddressService;
import com.cg.onlineshopping.util.AddressUtil;
@Validated
@RequestMapping("/address")
@RestController
public class AddressController {
	@Autowired 
	private IAddressService service;
	@Autowired
	private AddressUtil addressUtil;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public AddressDetails add(@RequestBody  @Valid CreateAddressRequest requestData) throws AddressAlreadyExistsException {
		Address add=service.addAddress(new Address(requestData.getStreetNo(),requestData.getBuildingName(),requestData.getCity(),requestData.getState(),requestData.getCountry(),requestData.getPincode()));
		return addressUtil.toDetails(add);
		
	}
	@PutMapping("/update")
	public AddressDetails update(@RequestBody UpdateAddressRequest requestData ) throws AddressNotFoundException
	{
		Address add=new Address(requestData.getStreetNo(),requestData.getBuildingName(),requestData.getCity(),requestData.getState(),requestData.getCountry(),requestData.getPincode());
		add.setAddressId(requestData.getAddressId());
		add=service.updateAddress(add);
		return addressUtil.toDetails(add);
		
	}
	@GetMapping("/get/id/{id}")
	public AddressDetails fetchCustomer(@PathVariable("id") int addressId) throws AddressNotFoundException
	{
		Address add = service.findById(addressId);
		return addressUtil.toDetails(add);
		
	}
	@RequestMapping("/viewall")
	public List<Address> viewAllAddress(){
		return service.viewAllAddress();
	}
	@DeleteMapping("/remove/{id}")
	public Address deleteAddress(@PathVariable Address add) throws AddressNotFoundException {
		return service.removeAddress(add);
	
	}
	
}
