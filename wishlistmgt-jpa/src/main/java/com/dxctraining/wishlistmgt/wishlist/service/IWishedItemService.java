package com.dxctraining.wishlistmgt.wishlist.service;

import java.util.List;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;

public interface IWishedItemService 
{
	WishedItem add(WishedItem wishedItem);
	WishedItem findById(String id);
	List<WishedItem> allWishedItems();
}
