package com.bankgemini.account;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankgemini.customer.Customer;

public interface AccountDao extends JpaRepository<Account, Long>{
	
	
	public Account  findByaccountNumber(Long id);
	public Account save(Account account);
	public List<Account> findAll();
	
	public List<Account> findByCustomer(Optional<Customer> optional);
	void deleteByAccountNumber(Long id);
	

}
