package com.dxctraining.wishlistmgt.wishlist.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.wishlistmgt.wishlist.exceptions.InvalidArgumentException;
import com.dxctraining.wishlistmgt.wishlist.exceptions.WishlistNotFoundException;
import com.dxctraining.wishlistmgt.wishlist.dao.IWishedItemDao;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class WishedItemServiceImpl implements IWishedItemService
{
	    @Autowired
	    private IWishedItemDao dao;
	    
	    @Override
	    public WishedItem add(WishedItem wishedItem) {
	        validate(wishedItem);
	        wishedItem=dao.save(wishedItem);
	        return wishedItem;
	    }

		private void validate(WishedItem wishedItem) {
			if(wishedItem == null) {
				throw new InvalidArgumentException("wishedItem should not be null");
			}
		}
	    
	    @Override
		public WishedItem findById(String id) {
			Optional<WishedItem> optional = dao.findById(id);
			if (!optional.isPresent()) {
				throw new WishlistNotFoundException("supplier not found for id=" + id);
			}
			WishedItem wishedItem = optional.get();
			return wishedItem;
		}
	    @Override
	    public List<WishedItem> allWishedItems(){
	        List<WishedItem>list = dao.findAll();
	        return list;
	    }
}
