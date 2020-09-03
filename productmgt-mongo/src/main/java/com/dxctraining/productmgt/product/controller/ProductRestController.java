package com.dxctraining.productmgt.product.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dxctraining.productmgt.product.dto.*;
import com.dxctraining.productmgt.product.entities.Product;
import com.dxctraining.productmgt.product.service.IProductService;
import com.dxctraining.productmgt.product.util.ProductUtil;

@RestController
@RequestMapping("/products")
public class ProductRestController 
{
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ProductUtil productUtil; 
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDto addProduct(@RequestBody CreateProductRequest requestData) {
		Product product = new Product(requestData.getName());
		product = productService.add(product);
		ProductDto response = productUtil.productDto(product);
		return response;
	}
	
	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDto findProductById(@PathVariable("id")String id) 
	{
		Product product = productService.findProductById(id);
		ProductDto response = productUtil.productDto(product);
		return response;
	}
	
	@GetMapping("/get/product/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> fetchProductByName(@PathVariable("name") String name){
		List<Product>list = productService.findProductByName(name);
		List<ProductDto> response = new ArrayList<>();
		for(Product product:list) 
		{
			ProductDto productDto = productUtil.productDto(product);
			response.add(productDto);
		}
		return response;
	}
	
	@GetMapping("/allProducts")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> fetchAllProducts(){
		List<Product> list = productService.allProducts();
		List<ProductDto>response = new ArrayList<>();
		for(Product product:list) {
			ProductDto dto = productUtil.productDto(product);
			response.add(dto);
		}
		return response;
	}
}
