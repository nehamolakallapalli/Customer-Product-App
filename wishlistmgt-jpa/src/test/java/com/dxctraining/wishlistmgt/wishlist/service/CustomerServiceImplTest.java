package com.dxctraining.customermgt.customer.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.customer.exceptions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({CustomerServiceImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceImplTest 
{
		@Autowired
		private ICustomerService customerService;

		@Autowired
		private EntityManager em;

		@Test
		public void testAdd_1() {
			Executable execute = () -> customerService.add(null);
			Assertions.assertThrows(InvalidArgumentException.class, execute);
		}

		@Test
		public void testAdd_2() {
			String name = "neha";
			Customer customer = new Customer(name);
			customer = customerService.add(customer);
			TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
			List<Customer> list = query.getResultList();
			Customer fetched = list.get(0);
			Assertions.assertEquals(customer.getId(), fetched.getId());
			Assertions.assertEquals(name, fetched.getName());
		}
		
		@Test
		public void testFindById_1() {
			Executable execute = () -> customerService.findCustomerById(0);
			Assertions.assertThrows(CustomerNotFoundException.class, execute);
		}

		@Test
		public void testFindById_2() {
			String name="ooha";
			Customer customer=new Customer(name);
			customer=em.merge(customer);
			Integer id=customer.getId();
			Customer fetched=customerService.findCustomerById(id);
			Assertions.assertEquals(id, fetched.getId());
			Assertions.assertEquals(customer.getName(),fetched.getName());
		}
}
