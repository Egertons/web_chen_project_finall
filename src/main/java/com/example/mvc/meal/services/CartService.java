package com.example.mvc.meal.services;

import java.util.List;
import java.util.Map;

import com.example.mvc.meal.daos.CartDao;

public class CartService {
    CartDao cartDao = new CartDao();

    public int addCart(int user_id,String[] ids) {
        return cartDao.addCart(user_id,ids);
    }

    public Map getAll(int no) {
        return cartDao.getAll(no);
    }

    public List getCartByUserid(int user_id) {
        return cartDao.getCartByUserid(user_id);
    }

    public int removeCartsByIds(String[] ids) {
        return cartDao.removeCartsByIds(ids);
    }

    public List<Map> getAllCart() {
        return cartDao.getAllCart();
    }
}
