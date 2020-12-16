package com.example.mvc.meal.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mvc.framework.db.JDBCTemplate;

public class CartDao {
    public boolean addCart(Map<String, String> cart_thing) {
        try {
            String sql = "insert into diningcar(userid,foodid) values(?,?)";
            return JDBCTemplate.update(sql, cart_thing.get("user_id"),cart_thing.get("ids"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map getAll(int no) {
        String sql="select * from diningcar";//这里有问题
        try {
            return JDBCTemplate.getPage(sql, new String[] {}, no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
