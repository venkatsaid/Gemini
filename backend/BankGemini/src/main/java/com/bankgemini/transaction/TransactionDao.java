package com.bankgemini.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, Integer>{
	
	

}
