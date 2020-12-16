package com.example.mvc.meal.services;

import java.util.List;
import java.util.Map;

import com.example.mvc.meal.daos.CartDao;

public class CartService {
    CartDao cartDao = new CartDao();

    public boolean addCart(Map<String, String> cart_thing) {
        return cartDao.addCart(cart_thing);
    }

    public Map getAll(int no) {
        return cartDao.getAll(no);
    }
}
