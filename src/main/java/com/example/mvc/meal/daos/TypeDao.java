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

    public Map getTypes(String name, int no) {
        String sql="select * from foodtype where typename like ? ";
        try {
            return JDBCTemplate.getPage(sql, new String[]{name}, no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addType(Map<String, String> type) {
        try {
            String sql="insert into foodtype(typename) values(?)";
            return JDBCTemplate.update(sql, type.get("tn"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTypeById(int id) {
        try {
            String sql="delete from foodtype where id = ?";
            return JDBCTemplate.update(sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateType(Map<String, String> type) {
        try {
            String sql="update foodtype set typename = ? where id = ?";
            return JDBCTemplate.update(sql, type.get("tn"),type.get("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map findTypeById(int id) {
        Map result = null;
        String sql="select * from foodtype where id=?";
        try {
            result= JDBCTemplate.queryForMap(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
