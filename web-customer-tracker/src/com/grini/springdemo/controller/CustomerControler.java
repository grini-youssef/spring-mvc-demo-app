package com.grini.springdemo.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grini.springdemo.dao.CustomerDAO;
import com.grini.springdemo.entity.Customer;
import com.grini.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerControler {
	
	//need to inject the our customer service
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomer(Model model) {
		
		//get the customers from the dao
		List<Customer> customers = customerService.getCustomers();
		
		//add the customers to the model
		model.addAttribute("customers", customers);
		
		return "list-costomers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer theCustomer = new Customer();
		model.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) { 
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int theId, Model model) {
		
		Customer customer = customerService.getCustomer(theId);
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		customerService.deleteCustomer(theId);
		
		
		return "redirect:/customer/list";
	}
	
}









