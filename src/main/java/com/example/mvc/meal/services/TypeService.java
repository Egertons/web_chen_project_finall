package com.example.mvc.meal.services;

import java.util.List;
import java.util.Map;

import com.example.mvc.meal.daos.TypeDao;

public class TypeService {
    TypeDao typeDao = new TypeDao();

    public Map findtypeById(String id) {
        return typeDao.findtypeById(id);
    }
}
