package com.cg.onlineshopping.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cg.onlineshopping.entities.Address;


public class CreateCustomerRequest {
	
	
	private int customerId;
    public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@NotBlank @Size(min =2, max = 20)
	private String firstName;
    @NotBlank @Size(min =2, max = 20)
	private String lastName;
    @NotBlank @Size(min =2, max = 20)
	private String mobileNumber;
    @NotBlank @Size(min =2, max = 20)
	private String email;
    @NotBlank @Size(min =2, max = 20)
	private Address address;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
    
}
