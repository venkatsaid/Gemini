package com.bankgemini.customer;

import java.lang.module.FindException;
import java.math.BigDecimal;
import java.math.MathContext;
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
	            sendEmailCustomerAdd(customer.getEmail(),customer.getUserName(),customer.getPassword());
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
				return "admin";
		}
		return "wrong credentials";
	}
	
	
	//################################################################################3
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
		Account acc= accountDao.save(account);
		try {
            //sendEmail(customer.getEmail(),customer.getUserName(),customer.getPassword());
            System.out.println("email sent");
            
        }catch(Exception ex) {
        	System.out.println("error in sending");
            
        }
		
		
		return acc;
	}
	
	
	//Transaction
	
	@GetMapping("/transaction/getAll")
	public List<Transaction> displayTransactions() {
		System.out.println("data coming");
		return transactionDao.findAll();
	}
	 
	@GetMapping("/transaction/{id}")
	public Transaction displayTransaction(@PathVariable(value = "id") Long id) {
		System.out.println("single transaction");
		return transactionDao.findByTransactionId(id);
	}
	
	@PostMapping("/transaction/add")
	public boolean createTransaction(@Valid @RequestBody Transaction transaction ) {
		
		//account.setCustomer(customer);
		Account fromaccount= accountDao.findByaccountNumber(transaction.getFromAccount().getAccountNumber());
		Account toaccount= accountDao.findByaccountNumber(transaction.getToAccount().getAccountNumber());
		
	      MathContext mc = new MathContext(2); // 2 precision
		BigDecimal chk=(BigDecimal) fromaccount.getAccountBalance().subtract(transaction.getAmount(),mc);
		System.out.println("test"+fromaccount.getAccountBalance().subtract(transaction.getAmount(),mc));
		System.out.println("chk"+chk);
		System.out.println("trans amount: "+ transaction.getAmount());
		System.out.println("from balancde:"+fromaccount.getAccountBalance());
		System.out.println("to balancde:"+toaccount.getAccountBalance());
		
		if(chk.compareTo(transactionDao.minamount)==0 || chk.compareTo(transactionDao.minamount)==1 )
		 {
//			fromaccount.setAccountBalance(fromaccount.getAccountBalance().subtract(transaction.getAmount()));
//			toaccount.setAccountBalance(toaccount.getAccountBalance().add(chk, mc));
			transaction.setTransactionType("DEBIT");
			transaction.setTransactionStatus("SUCCESS");
			transactionDao.save(transaction);
			System.out.println("funds transfered");
		
			if(transaction.getAmount().compareTo(transactionDao.flagamount)==1) {
				
				transaction.setFlag(true);
			}
			try {
	            //sendEmail(customer.getEmail(),customer.getUserName(),customer.getPassword());
	            System.out.println("email sent");
	            
	        }catch(Exception ex) {
	        	System.out.println("error in sending");
	            
	        }
				
			return true;
		}
		else {
			
			System.out.println("balance not available to transfer");
			return false;
		}
		
		
		
	}
	
//	@PostMapping("/transaction/add")
//	public Transaction createTransaction(@Valid @RequestBody Transaction transaction ) {
//		
//		//account.setCustomer(customer);
//		
//		try {
//            //sendEmail(customer.getEmail(),customer.getUserName(),customer.getPassword());
//            System.out.println("email sent");
//            
//        }catch(Exception ex) {
//        	System.out.println("error in sending");
//            
//        }
//		return transactionDao.save(transaction);
//	}
	
	
	
	
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
        helper.setText("Account created \n "+"Account number: "+ account.getAccountNumber()+" \n Account Type :"+ account.getAccountType());
        helper.setSubject("Welcome to the bank gemini!");
         
        sender.send(message);
        
    }
	
} 
