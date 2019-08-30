package com.example.demo.customer;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountNumber;
    private BigDecimal accountBalance;
     
    private String accountType;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "customerId")
    private Customer customer;
//    private long customerId;
    
	
    public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
//	public long getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerID(long customerId) {
//		this.customerId = customerId;
//	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	 public Customer getCustomer() { return customer; }
	 public void 
	  setCustomer(Customer customer) { this.customer = customer; } //
	

}
