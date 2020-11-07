package com.cg.onlineshopping.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.onlineshopping.dto.CustomerDetails;
import com.cg.onlineshopping.entities.Customer;

@Component
public class CustomerUtil {

	public List<CustomerDetails> toDetails(Collection<Customer> customers) {
		List<CustomerDetails> desired = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerDetails details = toDetails(customer);
			desired.add(details);
		}
		return desired;
	}

	public CustomerDetails toDetails(Customer customer) {
		return new CustomerDetails(customer.getCustomerId(),customer.getFirstName(), customer.getLastName(),customer.getMobileNumber(),customer.getEmail(),customer.getAddress());
	}

}
