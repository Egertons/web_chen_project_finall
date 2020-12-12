package com.example.mvc.meal.daos;

import java.util.List;
import java.util.Map;

import com.example.mvc.framework.db.JDBCTemplate;

public class FoodDao {

	public List<Map> getHotFoods() {
		String sql="select * from food order by hits limit 1,4";
		return JDBCTemplate.queryForList(sql);
	}
	public List<Map> getSalesFoods() {
		String sql="select * from food order by price limit 1,4";
		return JDBCTemplate.queryForList(sql);
	}
	public List<Map> getRecommedFoods() {
		String sql="select * from food order by comment limit 1,4";
		return JDBCTemplate.queryForList(sql);
	}

	public Map getFoods(String name,String f_type, int no) {
		String sql="select * from food where foodname like ? and type like ?";
		try {
			return JDBCTemplate.getPage(sql, new String[]{name,f_type}, no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	//这里可能有问题
	public boolean addFood(Map<String, String> food) {
		int type = 7;
		String y_ty = food.get("type");
		switch (y_ty) {
			case "主食":
				type = 3;
				break;
			case "凉菜":
				type = 2;
				break;
			case "副食":
				type = 6;
				break;
			case "家常":
				type = 1;
				break;
			case "饮品":
				type = 4;
				break;
		}
		try {
			String sql="insert into food(foodname,feature,material,price,type,picture,hits,comment) values(?,?,?,?,?,null,0,0)";
			return JDBCTemplate.update(sql, food.get("fn"),food.get("caipinte"),food.get("yuanliao"),food.get("price"),type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Map findfoodById(String id) {
		Map result = null;
		String sql="select * from food where id=?";
		try {
			result= JDBCTemplate.queryForMap(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map findfoodtype_id(String id) {
		Map result = null;
		String sql="select type from food where id=?";
		try {
			result= JDBCTemplate.queryForMap(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
