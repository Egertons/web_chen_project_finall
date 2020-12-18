package com.example.mvc.meal.daos;
/**
 * 该类为User的“数据操纵层”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import java.util.Map;
import com.example.mvc.framework.db.JDBCTemplate;

public class UserDao {

    //添加用户
    public boolean addUser(Map<String, String> user) {
        try {
            String sql="insert into user(username,password,ident,telephone,address) values(?,?,?,?,?)";
            return JDBCTemplate.update(sql, user.get("un"),user.get("pw"),user.get("ident"),user.get("tel"),user.get("addr"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //查找用户(用于登录)
    public Map findUser(String un, String pw) {
        String sql="select * from user where username=? and password=?";
        try {
            return JDBCTemplate.queryForMap(sql,un,pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取用户清单
    public Map getUsers(String name, int no) {
        String sql="select * from user where username like ?";
        try {
            return JDBCTemplate.getPage(sql, new String[] {name}, no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //删除用户
    public boolean deleteUserById(int id) {
        try {
            String sql="delete from user where id=?";
            return JDBCTemplate.update(sql,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //查找用户基于ID
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

    //修改用户
    public boolean updateUser(Map<String, String> user) {
        try {
            String sql="update user set username=?,password=?,ident=?,telephone=?,address=? where id=?";
            return JDBCTemplate.update(sql, user.get("un"),user.get("pw"),user.get("ident"),user.get("tel"),user.get("addr"),user.get("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //普通用户修改信息
    public boolean PuTongupdateUser(Map<String, String> user) {
        try {
            String sql="update user set username=?,password=?,telephone=?,address=? where id=?";
            return JDBCTemplate.update(sql, user.get("un"),user.get("pw"),user.get("tel"),user.get("addr"),user.get("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
