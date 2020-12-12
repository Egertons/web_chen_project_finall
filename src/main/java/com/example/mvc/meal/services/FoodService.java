package com.example.mvc.meal.services;

import java.util.List;
import java.util.Map;

import com.example.mvc.meal.daos.FoodDao;

public class FoodService {

	FoodDao dao = new FoodDao();
	public List<Map> getHotFoods() {
		return dao.getHotFoods();
	}

	public List<Map> getSaleFoods() {
		return dao.getSalesFoods();
	}

	public List<Map> getRecommedFoods() {
		return dao.getRecommedFoods();
	}


	public Map getFoods(String name ,String f_type, int no) {
		return dao.getFoods(name,f_type,no);
	}

	public boolean addFood(Map<String, String> food) {
		return dao.addFood(food);
	}

	public Map findfoodById(String id) {
		return dao.findfoodById(id);
	}

    public Map findfoodtype_id(String id) {
		return dao.findfoodtype_id(id);
    }
}
