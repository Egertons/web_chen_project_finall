package com.example.mvc.meal.daos;
/**
 * 该类为购物车的“数据操纵层”
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

import com.example.mvc.framework.db.JDBCTemplate;

public class CartDao {

    //添加类型
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

    //通过用户ID获取特定的购物车
    public List getCartByUserid(int user_id) {
        String sql="select fd.*,dc.id as dcid,ft.typename from food fd,diningcar dc,foodtype ft where fd.id=dc.foodid and fd.type=ft.id and dc.userid=?";
        return JDBCTemplate.queryForList(sql, user_id);
    }

    //通过用户ID移除特定的购物车
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

    //获取全部购物车订单
    public List<Map> getAllCart() {
        String sql = "select distinct u.id,u.username from user u join diningcar dc on u.id = dc.userid";
        List<Map> users = JDBCTemplate.queryForList(sql);
        for (Map m : users){
            m.put("cart",getCartByUserid(Integer.valueOf(m.get("id").toString())));
        }
        return users;
    }
}
