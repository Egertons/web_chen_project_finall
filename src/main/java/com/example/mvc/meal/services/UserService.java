package com.example.mvc.meal.services;

import java.util.Map;
import com.example.mvc.meal.daos.UserDao;

public class UserService {
	UserDao dao = new UserDao();
    public boolean addUser(Map<String, String> user) {
        return dao.addUser(user);
    }

    public Map findUser(String un, String pw) {
        return dao.findUser(un,pw);
    }

    public Map getUsers(String name, int no) {
        return dao.getUsers(name,no);
    }

    public boolean deleteUserById(int id) {
        return dao.deleteUserById(id);
    }

    public Map findUserById(int id) {
        return dao.findUserById(id);
    }

    public boolean updateUser(Map<String, String> user) {
        return dao.updateUser(user);
    }
}