package com.example.mvc.meal.daos;

import java.util.List;
import java.util.Map;

import com.example.mvc.framework.db.JDBCTemplate;

public class TypeDao {
    public Map findtypeById(String id) {
        Map result = null;
        String sql="select * from foodtype where id = ?";
        try {
            result= JDBCTemplate.queryForMap(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
