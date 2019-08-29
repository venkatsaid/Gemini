package com.bankgemini.transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankgemini.account.Account;
import com.bankgemini.customer.Customer;

public interface TransactionDao extends JpaRepository<Transaction, Long>{
	

	BigDecimal minamount= new BigDecimal("5000.00");
	BigDecimal flagamount = new BigDecimal("10000.00");
	public Transaction  findByTransactionId(Long id);
	public Transaction save(Transaction transaction);
	
	public List<Transaction> findAll();
	
	public List<Transaction> findByFromAccount(Optional<Customer> optional);
	public List<Transaction> findByToAccount(Optional<Customer> optional);
	
	

}
