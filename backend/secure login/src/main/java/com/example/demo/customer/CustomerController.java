package com.example.demo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
//@CrossOrigin()
@RequestMapping("/web")
public class CustomerController {
	@Autowired
	CustomerDao customerDao;
	
	public CustomerController() {
		super();
		
		
		System.out.println("calling controller");
		// TODO Auto-generated constructor stub
	}
	@PostMapping("/add")
	public Customer createCustomer(@RequestBody Customer customer ) {
		
		return customerDao.save(customer);
	}
	@GetMapping("/getAll")
	
	@ResponseBody
	public List<Customer> displayCustomer() {
		System.out.println("data coming");
		return customerDao.findAll();
	}

}
