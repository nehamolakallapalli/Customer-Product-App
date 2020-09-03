package com.dxctraining.productmgt.product.service;
import java.util.List;

import com.dxctraining.productmgt.product.entities.Product;

public interface IProductService 
{
	Product add(Product product);

    Product findProductById(String id);

    List<Product> findProductByName(String name);
    
    List<Product> allProducts();
}
