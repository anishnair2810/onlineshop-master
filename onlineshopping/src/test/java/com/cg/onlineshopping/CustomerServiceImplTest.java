package com.cg.onlineshopping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineshopping.entities.Customer;
import com.cg.onlineshopping.exception.CustomerAlreadyExistsException;
import com.cg.onlineshopping.exception.CustomerNotFoundException;

import com.cg.onlineshopping.repository.ICustomerRepository;
import com.cg.onlineshopping.service.impl.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {
	@Autowired
	private CustomerServiceImpl service;

	@MockBean
	private ICustomerRepository repo;
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	 void testCreateCustomer() throws CustomerAlreadyExistsException {

		Customer cust = new Customer(null,null,null,null,null);
		cust.setFirstName("Anish");
		cust.setLastName("Nair");
		cust.setMobileNumber("9893860242");
		cust.setEmail("anishnair.32@gmail.com");
		cust.setAddress(null);

		Mockito.when(repo.save(cust)).thenReturn(cust);
		assertThat(service.addCustomer(cust)).isEqualTo(cust);

	}
	@Test
	void testGetCustomer() throws CustomerNotFoundException {

		Customer cust = new Customer(null, null, null, null, null);
		cust.setFirstName("abcd");
		cust.setLastName("xyz");
		cust.setMobileNumber("1234567");
		cust.setEmail("abcd.32@gmail.com");
		cust.setAddress(null);

		Optional<Customer> ocust = Optional.of(cust);
		Mockito.when(repo.findById(1)).thenReturn(ocust);
		assertThat(service.viewCustomer(1)).isEqualTo(ocust.get());

	}
	@Test
	void testGetAllCustomers() {

		Customer cust1 = new Customer(null, null, null, null, null);
		cust1.setFirstName("Anish");
		cust1.setLastName("Nair");
		cust1.setMobileNumber("9893860242");
		cust1.setEmail("anishnair.32@gmail.com");
		cust1.setAddress(null);

		Customer cust2 = new Customer(null, null, null, null, null);
		cust2.setFirstName("abcd");
		cust2.setLastName("xyz");
		cust2.setMobileNumber("1234567");
		cust2.setEmail("abcd.32@gmail.com");
		cust2.setAddress(null);

		List<Customer> listCust = new ArrayList<>();
		listCust.add(cust1);
		listCust.add(cust2);
		Mockito.when(repo.findAll()).thenReturn(listCust);
		assertThat(service.viewAllCustomers()).isEqualTo(listCust);
	}
	
	@Test
	void testUpdateCustomer() throws CustomerNotFoundException {
		Customer cust = new Customer(null, null, null, null, null);
		cust.setFirstName("Anish");
		cust.setLastName("Nair");
		cust.setMobileNumber("9893860242");
		cust.setEmail("anishnair.32@gmail.com");
		cust.setAddress(null);
		
		Optional<Customer> oCust = Optional.of(cust);
		Mockito.when(repo.findById(1)).thenReturn(oCust);
		oCust.get().setFirstName("abcd");
		Mockito.when(repo.findById(1)).thenReturn(oCust);
		assertThat(service.viewCustomer(1).getFirstName()).isEqualTo("abcd");

	}
	
	@Test
	public void testDeleteCustomer() {
		Customer cust = new Customer(null, null, null, null, null);
		cust.setFirstName("Anish");
		cust.setLastName("Nair");
		cust.setMobileNumber("9893860242");
		cust.setEmail("anishnair.32@gmail.com");
		cust.setAddress(null);
		
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(cust));
		Mockito.when(repo.existsById(cust.getCustomerId())).thenReturn(false);

		assertFalse(repo.existsById(cust.getCustomerId()));
	}
}
