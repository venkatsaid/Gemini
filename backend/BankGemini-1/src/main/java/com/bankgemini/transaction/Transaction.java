package com.bankgemini.transaction;

import java.math.BigDecimal;
import java.math.BigInteger;
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
public class Transaction {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
//    private BigInteger fromAccount;
//    private BigInteger toAccount;
    private BigDecimal amount;
    //private Date date;
    private String description;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "accountNumber")
    Account fromAccount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "accountNumber")
    Account toAccount;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    
	public long getId() {
		return transactionId;
	}
	public void setId(long id) {
		this.transactionId = id;
	}
//	public BigInteger getFromAccount() {
//		return fromAccount;
//	}
//	public void setFromAccount(BigInteger fromAccount) {
//		this.fromAccount = fromAccount;
//	}
//	public BigInteger getToAccount() {
//		return toAccount;
//	}
//	public void setToAccount(BigInteger toAccount) {
//		this.toAccount = toAccount;
//	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
    
  
}
