package com.example.mvc.meal.services;
/**
 * 该类为Cart的“服务控制层”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import java.util.List;
import java.util.Map;

import com.example.mvc.meal.daos.CartDao;

public class CartService {
    CartDao cartDao = new CartDao();

    //添加类型
    public int addCart(int user_id,String[] ids) {
        return cartDao.addCart(user_id,ids);
    }

    //获取类型基于ID
    public List getCartByUserid(int user_id) {
        return cartDao.getCartByUserid(user_id);
    }

    //删除类型
    public int removeCartsByIds(String[] ids) {
        return cartDao.removeCartsByIds(ids);
    }

    //获取全部类型
    public List<Map> getAllCart() {
        return cartDao.getAllCart();
    }
}
