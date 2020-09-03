package com.dxctraining.productmgt.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.productmgt.product.entities.Product;
import com.dxctraining.productmgt.product.exceptions.InvalidArgumentException;
import com.dxctraining.productmgt.product.service.*;;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import(ProductServiceImpl.class)
public class ProductServiceImplTest 
{
		
		@Autowired
		private IProductService productService;

		@Test
		public void testAdd_1() {
			Executable execute=()->productService.add(null);
			Assertions.assertThrows(InvalidArgumentException.class, execute);
		}
		
		@Test
		public void testAdd_2() {
			String name = "AAA";
			Product product = new Product(name);
			product = productService.add(product);
			Product fetched = productService.findProductById(product.getId());
			Assertions.assertEquals(product.getId(),fetched.getId());
			Assertions.assertEquals(name,fetched.getName());
		}
		
		@Test
		public void testFindById_1() {
			Executable execute=()->productService.findProductById(null);
			Assertions.assertThrows(InvalidArgumentException.class, execute);
		}
		
		@Test
		public void testFindById_2() {
			String name = "BBB";
			Product product = new Product(name);
			product = productService.add(product);
			String id = product.getId();
			Product fetched = productService.findProductById(id);
			Assertions.assertEquals(fetched.getId(), id);
		}
}
