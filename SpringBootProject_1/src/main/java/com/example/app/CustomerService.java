package com.example.app;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.*;

@Service
public class CustomerService {
	
	public CustomerService() {
		super();
	}

	@Autowired
	CustormerRepository customerRepository;
	
	 @Autowired
	 private CusRepInterface repo;
	
	public List<Customer> selectAllCustomer(){
		return customerRepository.findAll();
	}
	
	public void save(Customer c) {
		repo.save(c);
	}
	
	public List<Customer> findById(Integer c) {
		List<Customer> cusList = customerRepository.findById(c);
		return cusList;
	}
	
	public void updateCutomerById(Customer c) {
		customerRepository.updateCustomer(c);
	}
	
	public void deleteSingleCutomerById(Integer id) {
		customerRepository.deleteSigleCustomer(id);
	}
	
}
