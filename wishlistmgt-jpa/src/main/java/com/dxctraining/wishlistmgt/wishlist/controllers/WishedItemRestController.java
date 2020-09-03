package com.dxctraining.wishlistmgt.wishlist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.dxctraining.wishlistmgt.wishlist.dto.*;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;
import com.dxctraining.wishlistmgt.wishlist.service.IWishedItemService;
import com.dxctraining.wishlistmgt.wishlist.util.*;

@RestController
@RequestMapping("/wisheditems")
public class WishedItemRestController 
{
		@Autowired
		private IWishedItemService service;
		
		@Autowired
		private RestTemplate restTemplate;
		
		@Autowired
		private WishedItemUtil wishedItemUtil;

		@PostMapping("/add")
		@ResponseStatus(HttpStatus.CREATED)
		public WishedItemDto addWishItem(@RequestBody CreateWishedItemRequest data) {
			WishedItem wishedItem = new WishedItem(data.getCustomerId(),data.getProductId());
			wishedItem = service.add(wishedItem);
			CustomerDto customer = fetchAllCustomersById(data.getCustomerId());
			ProductDto product=fetchAllProductsById(data.getProductId());
			WishedItemDto response = wishedItemUtil.wishedItemDto(wishedItem);
		}

		@GetMapping("/get/{id}")
		public WishedItemDto findById(@PathVariable("id") String id) {
			WishedItem wishedItem = service.findById(id);
			CustomerDto customer = fetchAllCustomersById(wishedItem.getCustomerId());
			ProductDto product=fetchAllProductsById(wishedItem.getProductId());
			WishedItemDto dto=wishedItemUtil.wishedItemDto(wishedItem);
			return dto;
		}

		public CustomerDto fetchAllCustomersById(int customerId) {
	        String url = "http://localhost:8586/customers/get/" + customerId;
	        CustomerDto dto = restTemplate.getForObject(url, CustomerDto.class);
	        return dto;
	    }
		
		public ProductDto fetchAllProductsById(String productId) {
	        String url = "http://localhost:8587/products/get/" + productId;
	        ProductDto dto = restTemplate.getForObject(url, ProductDto.class);
	        return dto;
	    }
}
