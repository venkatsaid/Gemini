package com.bankgemini.controller;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.UUID;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankgemini.account.Account;
import com.bankgemini.account.AccountDao;
import com.bankgemini.customer.Customer;
import com.bankgemini.customer.CustomerDao;
import com.bankgemini.transaction.Transaction;
import com.bankgemini.transaction.TransactionDao;

@RestController
@RequestMapping("/bankapi")
@CrossOrigin("*")
public class Controller {
	@Autowired
	CustomerDao customerDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	TransactionDao transactionDao;
	@Autowired
	private JavaMailSender sender;
	
	public Controller() {
		super();
		
		
	}
	
	//Customer apis
	
	@PostMapping("/customer/add")
	public Customer createCustomer(@Valid @RequestBody Customer customer ) {
		
		if(!customerDao.existsByEmail(customer.getEmail()) && !customerDao.existsByAadharNumber(customer.getAadharNumber()) && !customerDao.existsByPanNumber(customer.getPanNumber())) {
			customer.setConfirmationToken(UUID.randomUUID().toString());
			customer.setUserName(customer.getEmail());
			customer.setPassword(customer.getConfirmationToken().substring(0, 8));
			
			
			try {
	            sendEmailCustomerAdd(customer.getEmail(),customer.getUserName(),customer.getPassword());
	           
	            
	        }catch(Exception ex) {
	        	
	            
	        }
			return customerDao.save(customer);
			
		} 
		return null;
	}
	
	@GetMapping("/customer/getall")
	public List<Customer> displayCustomer() {
		
		return customerDao.findAll();
	}
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") Long id) {
	    return customerDao.findByCustomerId(id);
	}

	
	@PutMapping("/customer/update")
	public Customer updateCustomer(@Valid @RequestBody Customer customerDetails) {

	    Customer customer = customerDao.findByCustomerId(customerDetails.getCustomerId());
	           
	    customer.setFirstName(customerDetails.getFirstName());
	    customer.setLastName(customerDetails.getLastName());
	    customer.setAddress(customerDetails.getAddress());
	    customer.setDob(customerDetails.getDob());
	    customer.setEmail(customerDetails.getEmail());
	    customer.setPhone(customerDetails.getPhone());
	    customer.setPincode(customerDetails.getPincode());
	    customer.setPanNumber(customerDetails.getPanNumber());
	    customer.setUserName(customerDetails.getUserName());
	    
	    Customer updatedCustomer = customerDao.save(customer);
	    return updatedCustomer;
	}
	
	@DeleteMapping("/customer/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		
		customerDao.deleteById(id);
	}
	@GetMapping("/getpass/{id}")
	public String password(@PathVariable Long id) {
		
		
		return "Password"+customerDao.findByCustomerId(id).getPassword();
	}
	
	//login
	@PostMapping("/customer/login")
	public Customer login(@RequestParam String userName,String pass) {
		
		if(customerDao.existsByUserName(userName)) {
			
			 if(customerDao.findByUserName(userName).getPassword().equals(pass)) {
				 return customerDao.findByUserName(userName);
			 }
		}
		return null;
	}
	
	@PostMapping("/banker/login")
	public String bankerlogin(@RequestParam String userName,String pass) {
		
		if(userName.equals("admin") && pass.equals("admin")) {
				return "admin";
		}
		return "wrong credentials";
	}
	
	
	//################################################################################3
	//Accounts api 
	
	@GetMapping("/account/getAll")
	public List<Account> displayAccounts() {
		
		return accountDao.findAll();
	}
	
	@GetMapping("/account/accno/{accid}")
	public Account displayAccount(@PathVariable(value = "accid") Long id) {
		
		return accountDao.findByaccountNumber(id);
	}
	@GetMapping("/account/customer/{custId}")
	public List<Account> displayAccountsbyCustomer(@PathVariable(value = "custId") Long id) {
		
		return accountDao.findByCustomer(customerDao.findById(id));
	}
	
	@PostMapping("/account/add")
	public Account createAccount(@RequestParam Long customerId, BigDecimal accountBalance,String accountType ) {
		
		
		Account account= new Account();
		account.setAccountBalance(accountBalance);
		account.setAccountType(accountType);
		account.setCustomer(customerDao.findByCustomerId(customerId));
		accountDao.save(account);
		try {
            
			sendEmailAccountAdd(account);
            
            
        }catch(Exception ex) {
        	
            
        }
		
		
		return account;
	}
	
	

	//#######################3333#
	//Transaction API
	
	@GetMapping("/transaction/getall")
	public List<Transaction> displayTransactions() {
		
		return transactionDao.findAll();
	}
	 
	
	@GetMapping("/transaction/{id}")
	public Transaction displayTransaction(@PathVariable(value = "id") Long id) {
		
		return transactionDao.findByTransactionId(id);
	}
	
	@GetMapping("/transaction/customer/{id}")
	public List<Transaction> displayTransactionsBycustomerId(@PathVariable(value = "id") Long id) {
		return transactionDao.getLastTenTransaction(id);
		
	}
	


	
	@PostMapping("/transaction/add") public Transaction createTransactionbyParams(@RequestParam BigDecimal amount,Long fromaccount,Long toaccount,String description) {

		Transaction transaction= new Transaction();


		transaction.setDescription(description); //
		transaction.setFromAccount(accountDao.findByaccountNumber(fromaccount)); //
		transaction.setToAccount(accountDao.findByaccountNumber(toaccount));

		Account fromaccount1= accountDao.findByaccountNumber(fromaccount); Account
		toaccount1= accountDao.findByaccountNumber(toaccount);

		MathContext mc = new MathContext(2); // 2 precision 
		BigDecimal chk=(BigDecimal) fromaccount1.getAccountBalance().subtract(amount,mc);		
		if(chk.compareTo(transactionDao.minamount)==0 || chk.compareTo(transactionDao.minamount)==1 ) { 

			transaction.setAmount(amount);  
			transaction.setDescription(description);
			transaction.setFromAccount(fromaccount1);
			transaction.setToAccount(toaccount1);
			transaction.setTransactionType("DEBIT");
			transaction.setTransactionStatus("SUCCESS");


			if(transaction.getAmount().compareTo(transactionDao.flagamount)==1) {

				transaction.setFlag(true); 
			}
			
			Transaction trans1= transactionDao.save(transaction);
			try { 
				sendEmailFromAccount(trans1);
				sendEmailToAccount(trans1);

			}catch(Exception ex) { 

			}

			return trans1; 
		} 
		else {


			return null; 
		}
	}



	private void sendEmailCustomerAdd(String email,String userName,String password) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo(email);
        helper.setText("Login credentials to login to bank service \n "+"user Name : "+ userName+"\n password: "+password);
        helper.setSubject("Welcome to the bank gemini!");
         
        sender.send(message);
        
    }
	
	private void sendEmailAccountAdd(Account account) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String email= customerDao.findByCustomerId(account.getCustomer().getCustomerId()).getEmail();
        helper.setTo(email);
        helper.setText("Account created \n "+"Account number: "+ account.getAccountNumber()+" \n Account Type :"+ account.getAccountType() +"\n created at: "+ account.getCreatedAt());
        helper.setSubject(" :) Account created ");
         
        sender.send(message);
        
    }
	
	private void sendEmailFromAccount(Transaction transaction) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String email= customerDao.findByCustomerId(transaction.getFromAccount().getCustomer().getCustomerId()).getEmail();
        helper.setTo(email);
        helper.setText("Transaction \n "+"Amount:"+transaction.getAmount()+"\n debited from your Account number: "+ transaction.getFromAccount().getAccountNumber()+" \n To Account Number :"+ transaction.getToAccount().getAccountNumber() +"\n  at: "+ transaction.getCreatedAt());
        helper.setSubject(" :) Transaction of debit success! ");
         
        sender.send(message);
        
    }
	
	private void sendEmailToAccount(Transaction transaction) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String email= customerDao.findByCustomerId(transaction.getToAccount().getCustomer().getCustomerId()).getEmail();
        helper.setTo(email);
        helper.setText("Transaction \n "+"Amount:"+transaction.getAmount()+"\n credited to your Account number: "+ transaction.getToAccount().getAccountNumber()+" \n From Account Number :"+ transaction.getFromAccount().getAccountNumber() +"\n  at: "+ transaction.getCreatedAt());
        helper.setSubject(" :) Amount credited to your account success! ");
         
        sender.send(message);
        
    }
	
	
	
	
	
	
} 
