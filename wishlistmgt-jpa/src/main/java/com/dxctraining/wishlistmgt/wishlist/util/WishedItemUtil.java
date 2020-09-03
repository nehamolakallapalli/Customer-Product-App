package com.dxctraining.wishlistmgt.wishlist.util;

import org.springframework.stereotype.Component;

import com.dxctraining.wishlistmgt.wishlist.dto.WishedItemDto;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;

@Component
public class WishedItemUtil 
{		
		public WishedItemDto wishedItemDto(WishedItem wishedItem,int customerId,String customerName,String productId,String productName) 
		{
			WishedItemDto dto=new WishedItemDto(wishedItem.getId());
			dto.setCustomerId(customerId);
			dto.setCustomerName(customerName);
			dto.setProductId(productId);
			dto.setProductName(productName);
			return dto;
		}
}
