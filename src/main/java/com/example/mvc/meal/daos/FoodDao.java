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


	public boolean addFood(Map food) {
		String sql="insert into food ( foodname,feature,material,price,type,picture,hits,comment) values(?,?,?,?,?,?,0,?)";
		return JDBCTemplate.update(sql, food.get("fn"), food.get("fea"), food.get("mat"), food.get("price"), food.get("type"), food.get("img"), food.get("com"));
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

	public boolean deleteFoodById(int id) {
		try {
			String sql="delete from food where id = ?";
			return JDBCTemplate.update(sql,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Map findFoodById(int id) {
		Map result=null;
		String sql="select * from food where id=?";
		try {
			result= JDBCTemplate.queryForMap(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

    public boolean updateFood(Map food) {
		System.out.println(food);
		String sql = "update food set foodname=?,feature=?,material=?,price=?,type=?,picture=?,hits=?,comment=? where id=?";
		String fn = (String)food.get("f_un");
		System.out.println("FoodDao在修改菜品信息的时候已经获取到："+fn);
		return JDBCTemplate.update(sql, food.get("f_un"), food.get("fea"), food.get("mat"), food.get("price"), food.get("type"), food.get("img"),0, food.get("com"), food.get("id"));
    }
}
