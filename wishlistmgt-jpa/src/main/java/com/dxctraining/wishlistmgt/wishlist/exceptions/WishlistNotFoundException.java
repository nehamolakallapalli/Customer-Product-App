package com.dxctraining.wishlistmgt.wishlist.exceptions;

public class WishlistNotFoundException extends RuntimeException
{
    public WishlistNotFoundException(String msg)
    {
        super(msg);
    }
}
