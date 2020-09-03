package com.dxctraining.customermgt.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.customermgt.customer.dao.ICustomerDao;
import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.customer.exceptions.*;

@Transactional
@Service
public class CustomerServiceImpl implements ICustomerService
{
		@Autowired
		private ICustomerDao dao;

		@Override
		public Customer findCustomerById(int id) {
			Optional<Customer> optional = dao.findById(id);
			boolean exist = optional.isPresent();
			if (!exist) {
				throw new CustomerNotFoundException("customer not found for id=" + id);
			}
			Customer customer = optional.get();
			return customer;
		}

		@Override
		public Customer add(Customer customer) {
			validate(customer);
			customer = dao.save(customer);
			return customer;
		}
		private void validate(Customer customer) {
			if(customer == null) {
				throw new InvalidArgumentException("customer should not be null");
			}	
		}
		@Override
		public List<Customer> findByName(String name) {
			List<Customer> customer = dao.findByName(name);
			return customer;
		}

		@Override
		public List<Customer> allCustomers() {
			List<Customer> allCustomers = dao.findAll();
			return allCustomers;
		}
}
