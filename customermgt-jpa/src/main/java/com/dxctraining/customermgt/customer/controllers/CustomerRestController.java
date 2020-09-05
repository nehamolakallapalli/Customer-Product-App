package com.dxctraining.customermgt.customer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dxctraining.customermgt.customer.dto.*;
import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.customer.service.ICustomerService;
import com.dxctraining.customermgt.customer.util.CustomerUtil;

@RestController
@RequestMapping("/customers")
public class CustomerRestController 
{
		@Autowired
		private ICustomerService customerService;
		
		@Autowired
		private CustomerUtil customerUtil;

		@PostMapping("/add")
		@ResponseStatus(HttpStatus.CREATED)
		public CustomerDto addr(@RequestBody CreateCustomerRequest requestData) {
			Customer customer=new Customer(requestData.getName());
			customer = customerService.add(customer);
			CustomerDto response = customerUtil.customerDto(customer);
			return response;
		}

		@GetMapping("/get/{id}")
		@ResponseStatus(HttpStatus.OK)
		public CustomerDto findCustomerById(@PathVariable("id") int id) {
			Customer customer = customerService.findCustomerById(id);
			CustomerDto response = customerUtil.customerDto(customer);
			return response;
		}

		@GetMapping("/allCustomers")
		@ResponseStatus(HttpStatus.OK)
		public List<CustomerDto> fetchAll(){
			List<Customer>list=customerService.allCustomers();
			List<CustomerDto> response=new ArrayList<>();
			for(Customer customer:list) {
				CustomerDto dto=customerUtil.customerDto(customer);
				response.add(dto);
			}
			return response;
		}
}
