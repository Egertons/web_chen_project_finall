package com.example.mvc.meal.services;

import java.util.List;
import java.util.Map;

import com.example.mvc.meal.daos.TypeDao;

public class TypeService {
    TypeDao typeDao = new TypeDao();

    public Map findtypeById(String id) {
        return typeDao.findtypeById(id);
    }

    public Map getTypes(String name, int no) {
        return typeDao.getTypes(name,no);
    }

    public boolean addType(Map<String, String> type) {
        return typeDao.addType(type);
    }

    public boolean deleteTypeById(int id) {
        return typeDao.deleteTypeById(id);
    }

    public boolean updateType(Map<String, String> type) {
        return typeDao.updateType(type);
    }

    public Map findTypeById(int id) {
        return typeDao.findTypeById(id);
    }
}
