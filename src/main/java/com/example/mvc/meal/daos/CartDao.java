package com.example.mvc.meal.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mvc.framework.db.JDBCTemplate;

public class CartDao {
    public int addCart(int user_id,String[] ids) {
        int count = 0;
        try {
            String sql = "insert into diningcar(userid,foodid) values(?,?)";
            if (ids!=null){
                for (String id : ids){
                    boolean res = JDBCTemplate.update(sql,user_id,id);
                    if (res){
                        count+=1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
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

    public List getCartByUserid(int user_id) {
        String sql="select fd.*,dc.id as dcid,ft.typename from food fd,diningcar dc,foodtype ft where fd.id=dc.foodid and fd.type=ft.id and dc.userid=?";
        return JDBCTemplate.queryForList(sql, user_id);
    }

    public int removeCartsByIds(String[] ids) {
        String sql="delete from diningcar where id=?";
        int count=0;
        if(ids!=null) {
            for(String id:ids) {
                boolean res=JDBCTemplate.update(sql,id);
                if(res) count++;
            }
        }
        return count;
    }

    public List<Map> getAllCart() {
        String sql = "select distinct u.id,u.username from user u join diningcar dc on u.id = dc.userid";
        List<Map> users = JDBCTemplate.queryForList(sql);
        for (Map m : users){
            m.put("cart",getCartByUserid(Integer.valueOf(m.get("id").toString())));
        }
        return users;
    }
}
