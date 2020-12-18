package com.example.mvc.meal.services;
/**
 * 该类为User的“服务控制层”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import java.util.Map;
import com.example.mvc.meal.daos.UserDao;

public class UserService {

	UserDao dao = new UserDao();

	//添加用户
    public boolean addUser(Map<String, String> user) {
        return dao.addUser(user);
    }

    //寻找用户(用于登录)
    public Map findUser(String un, String pw) {
        return dao.findUser(un,pw);
    }

    //获取全部用户清单(用于主页)
    public Map getUsers(String name, int no) {
        return dao.getUsers(name,no);
    }

    //删除用户
    public boolean deleteUserById(int id) {
        return dao.deleteUserById(id);
    }

    //寻找用于基于ID
    public Map findUserById(int id) {
        return dao.findUserById(id);
    }

    //更新用户
    public boolean updateUser(Map<String, String> user) {
        return dao.updateUser(user);
    }

    //普通用户的更新操作
    public boolean PuTongupdateUser(Map<String, String> user) {
        return dao.PuTongupdateUser(user);
    }
}