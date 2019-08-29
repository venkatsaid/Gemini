package com.example.demo.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer>{	

	public Optional <Customer> findById(Integer id);
	
	public Customer save(Customer customer);
	public List<Customer> findAll();
	
	boolean existsByAadharNumber(Integer num);
	boolean existsByPanNumber(Integer num);
	
	void deleteByCustomerId(Integer id);
	
}
