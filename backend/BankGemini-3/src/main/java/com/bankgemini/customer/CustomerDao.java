package com.bankgemini.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Long>{	

	
	
	public Customer  findByCustomerId(Long customerId);
	public Customer save(Customer customer);
	public List<Customer> findAll();
	
	public boolean existsByAadharNumber(String aadhar);
	public boolean existsByEmail(String email);
	public boolean existsByPanNumber(String pan);
	public boolean existsByUserName(String userName);
	public Customer findByUserName(String userName);
	
	void deleteByCustomerId(Long id);
	
}
