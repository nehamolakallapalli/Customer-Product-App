package com.dxctraining.wishlistmgt.wishlist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dxctraining.wishlistmgt.wishlist.entities.WishedItem;

public interface IWishedItemDao  extends JpaRepository<WishedItem,String> 
{

}
