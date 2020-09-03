package com.dxctraining.productmgt.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxctraining.productmgt.product.dao.IProductDao;
import com.dxctraining.productmgt.product.entities.Product;
import com.dxctraining.productmgt.product.exceptions.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService
{
	    @Autowired
	    private IProductDao dao;

	    @Override
	    public Product add(Product product) {
	    	validate(product);
	       product= dao.save(product);
	       return product;
	    }

	    private void validate(Product product) {
			if(product == null) {
				throw new InvalidArgumentException("product should not be null");
			}	
		}

		@Override
	    public Product findProductById(String id) {
			validateId(id);
	      Optional<Product>optional= dao.findById(id);
	      boolean exist=optional.isPresent();
	      if(!exist){
	          throw new ProductNotFoundException("product not found for id="+id);
	      }
	      Product product=optional.get();
	       return product;
	    }

		private void validateId(String id) {
			if(id == null) {
				throw new InvalidArgumentException("id should not be null");
			}
			
		}

		@Override
		public List<Product> findProductByName(String name) {
			List<Product>list = dao.findByName(name);
			return list;
		}

		@Override
		public List<Product> allProducts() {
			List<Product>list = dao.findAll();
			return list;
		}
}
