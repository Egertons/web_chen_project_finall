package com.example.mvc.meal.daos;

import java.util.Map;
import com.example.mvc.framework.db.JDBCTemplate;

public class UserDao {
    public boolean addUser(Map<String, String> user) {
        try {
            String sql="insert into user(username,password,ident,telephone,address) values(?,?,?,?,?)";
            return JDBCTemplate.update(sql, user.get("un"),user.get("pw"),user.get("ident"),user.get("tel"),user.get("addr"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map findUser(String un, String pw) {
        String sql="select * from user where username=? and password=?";
        try {
            return JDBCTemplate.queryForMap(sql,un,pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map getUsers(String name, int no) {
        String sql="select * from user where username like ?";
        try {
            return JDBCTemplate.getPage(sql, new String[] {name}, no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteUserById(int id) {
        try {
            String sql="delete from user where id=?";
            return JDBCTemplate.update(sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map findUserById(int id) {
        Map result=null;
        String sql="select * from user where id=?";
        try {
            result= JDBCTemplate.queryForMap(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUser(Map<String, String> user) {
        try {
            String sql="update  user set username=?,password=?,ident=?,telephone=?,address=? where id=?";
            return JDBCTemplate.update(sql, user.get("un"),user.get("pw"),user.get("ident"),user.get("tel"),user.get("addr"),user.get("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
