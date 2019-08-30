package com.bankgemini.transaction;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.bankgemini.customer.Customer;

public interface TransactionDao extends JpaRepository<Transaction, Long>{
	

	BigDecimal minamount= new BigDecimal("5000.00");
	BigDecimal flagamount = new BigDecimal("10000.00");
	public Transaction  findByTransactionId(Long id);
	public Transaction save(Transaction transaction);	
	public List<Transaction> findAll();
	public List<Transaction> findByFromAccount(Customer optional);
	public List<Transaction> findByToAccount(Customer optional);
	@Query("from Transaction where ( fromAccount in (select accountNumber from Account where customer_customer_id = ?1 ) ) or ( toAccount in (select accountNumber from Account where customer_customer_id = ?1 ) )  order by transactionId desc") 
    public List<Transaction> getLastTenTransaction(Long id);
	
	
	

}
