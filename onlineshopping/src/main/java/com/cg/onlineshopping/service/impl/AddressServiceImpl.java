package com.cg.onlineshopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;
import com.cg.onlineshopping.repository.IAddressRepository;
import com.cg.onlineshopping.service.IAddressService;
@Transactional
@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired
	private IAddressRepository repo;

	@Override
	public Address addAddress(Address add) throws AddressAlreadyExistsException {
		boolean exists=add.getAddressId()!=0 && repo.existsById(add.getAddressId());
		if(exists) {
			throw new AddressAlreadyExistsException("address already exists for id=" + add.getAddressId());
		}
		add=repo.save(add);
		return add;
		 
	}
	
	public Address findById(int addressId) throws AddressNotFoundException {
		Optional<Address> optional=repo.findById(addressId);
		if(!optional.isPresent())
			throw new AddressNotFoundException("Can't find, address not found for id="+addressId);
	return optional.get();
	}
	public void deleteById(int addressId) throws AddressNotFoundException {
		Address add=findById(addressId);
		repo.delete(add);
	}
	@Override
	public Address updateAddress(Address add) throws AddressNotFoundException {
		boolean exists=add.getAddressId()!=0 && repo.existsById(add.getAddressId());
		if(!exists) {
			throw new AddressNotFoundException("Can't find, address not found for id="+add.getAddressId());
		}
		return repo.save(add);
	}

	@Override
	public Address removeAddress(Address add) throws AddressNotFoundException {
		return add;

		
	}

	@Override
	public List<Address> viewAllAddress() {
		List<Address> addresses=new ArrayList<Address>();
		repo.findAll().forEach(addresses::add);
		

		return addresses;
	}

	@Override
	public Address viewAddress(Address add) {
		
		return null;
	}

}
