package com.bankgemini.customer;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankgemini.account.Account;
import com.bankgemini.account.AccountDao;
import com.bankgemini.transaction.Transaction;
import com.bankgemini.transaction.TransactionDao;

@RestController
@RequestMapping("/bankapi")
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerDao customerDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	TransactionDao transactionDao;
	@Autowired
	private JavaMailSender sender;
	public CustomerController() {
		super();
		
		System.out.println("calling controller");
		// TODO Auto-generated constructor stub
	}
	
	
	
	@PostMapping("/customer/add")
	public Customer createCustomer(@Valid @RequestBody Customer customer ) {
		
		if(!customerDao.existsByEmail(customer.getEmail()) && !customerDao.existsByAadharNumber(customer.getAadharNumber()) && !customerDao.existsByPanNumber(customer.getPanNumber())) {
			customer.setConfirmationToken(UUID.randomUUID().toString());
			customer.setUserName(customer.getEmail());
			customer.setPassword(customer.getConfirmationToken().substring(0, 8));
			
			try {
	            sendEmail(customer.getEmail(),customer.getUserName(),customer.getPassword());
	            System.out.println("email sent");
	            
	        }catch(Exception ex) {
	        	System.out.println("error in sending");
	            
	        }
			return customerDao.save(customer);
			
		} 
		return null;
	}
	
	@GetMapping("/customer/getall")
	public List<Customer> displayCustomer() {
		System.out.println("data coming");
		return customerDao.findAll();
	}
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable(value = "id") Long id) {
	    return customerDao.findByCustomerId(id);
	}

	
	@PutMapping("/customer/update")
	public Customer updateCustomer(/* @PathVariable(value = "id") Long id, */
	                                        @Valid @RequestBody Customer customerDetails) {

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
		System.out.println("deleting the record");
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
			System.out.println("login: +"+customerDao.findByUserName(userName).getPassword().equals(pass));
			 if(customerDao.findByUserName(userName).getPassword().equals(pass)) {
				 return customerDao.findByUserName(userName);
			 }
		}
		return null;
	}
	
	@PostMapping("/banker/login")
	public String bankerlogin(@RequestParam String userName,String pass) {
		
		if(userName.equals("admin") && pass.equals("admin")) {
				
		}
		return "";
	}
	
	//Accounts
	
	@GetMapping("/account/getAll")
	public List<Account> displayAccounts() {
		System.out.println("data coming");
		return accountDao.findAll();
	}
	
	@GetMapping("/account/accno/{accid}")
	public Account displayAccount(@PathVariable(value = "accid") Long id) {
		System.out.println("data coming");
		return accountDao.findByaccountNumber(id);
	}
	@GetMapping("/account/customer/{custId}")
	public List<Account> displayAccountsbyCustomer(@PathVariable(value = "custId") Long id) {
		System.out.println("data coming account");
		return accountDao.findByCustomer(customerDao.findById(id));
	}
	
	@PostMapping("/account/add")
	public Account createAccount(@Valid @RequestBody Account account ) {
		
		//account.setCustomer(customer);
		
		try {
            //sendEmail(customer.getEmail(),customer.getUserName(),customer.getPassword());
            System.out.println("email sent");
            
        }catch(Exception ex) {
        	System.out.println("error in sending");
            
        }
		return accountDao.save(account);
	}
	
	
	//Transaction
	
	@GetMapping("/transaction/getAll")
	public List<Transaction> displayTransactions() {
		System.out.println("data coming");
		return transactionDao.findAll();
	}
	
	
	@PostMapping("/transaction/add")
	public Transaction createTransaction(@Valid @RequestBody Transaction transaction ) {
		
		//account.setCustomer(customer);
		
		try {
            //sendEmail(customer.getEmail(),customer.getUserName(),customer.getPassword());
            System.out.println("email sent");
            
        }catch(Exception ex) {
        	System.out.println("error in sending");
            
        }
		return transactionDao.save(transaction);
	}
	
	private void sendEmail(String email,String userName,String password) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo(email);
        helper.setText("Login credentials to login to bank service \n "+"user Name : "+ userName+"\n password: "+password);
        helper.setSubject("Welcome to the bank gemini!");
         
        sender.send(message);
    }
	
} 
