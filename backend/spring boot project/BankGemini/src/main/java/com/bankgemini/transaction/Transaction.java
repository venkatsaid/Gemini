package com.bankgemini.transaction;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bankgemini.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt",},allowGetters = true)

public class Transaction implements Comparable<Transaction>{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

	private BigDecimal amount;
    private String description;
        
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "accountNumber")
    Account fromAccount;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "accountNumber")
    Account toAccount;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    
    private String transactionType;
    
    private String transactionStatus;
    
    private boolean flag=false;
    
    public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
    
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public long getId() {
		return transactionId;
	}
	public void setId(long id) {
		this.transactionId = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
	public Account getFromAccount() {
		return fromAccount;
	}
	
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	public Account getToAccount() {
		return toAccount;
	}
	
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int compareTo(Transaction o) {
		
		if (getCreatedAt() == null || o.getCreatedAt() == null) {
		      return 0;
		    }
		    return getCreatedAt().compareTo(o.getCreatedAt());
		
	}
	
    
  
}
