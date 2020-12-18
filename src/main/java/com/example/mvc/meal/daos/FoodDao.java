package com.example.mvc.meal.daos;
/**
 * 该类为Food的“数据操纵层”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import java.util.List;
import java.util.Map;

import com.example.mvc.framework.db.JDBCTemplate;

public class FoodDao {

	//获取热点菜品清单
	public List<Map> getHotFoods() {
		String sql="select * from food order by hits limit 1,4";
		return JDBCTemplate.queryForList(sql);
	}

	//获取热点特价清单
	public List<Map> getSalesFoods() {
		String sql="select * from food order by price limit 1,4";
		return JDBCTemplate.queryForList(sql);
	}

	//获取厨师推荐清单
	public List<Map> getRecommedFoods() {
		String sql="select * from food order by comment limit 1,4";
		return JDBCTemplate.queryForList(sql);
	}

	//用于菜品首页的清单获取(包括搜索逻辑)
	public Map getFoods(String name,String f_type, int no) {
		String sql="select * from food where foodname like ? and type like ?";
		try {
			return JDBCTemplate.getPage(sql, new String[]{name,f_type}, no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//添加菜品
	public boolean addFood(Map food) {
		String sql="insert into food ( foodname,feature,material,price,type,picture,hits,comment) values(?,?,?,?,?,?,0,?)";
		return JDBCTemplate.update(sql, food.get("fn"), food.get("fea"), food.get("mat"), food.get("price"), food.get("type"), food.get("img"), food.get("com"));
	}

	//通过ID寻找特定菜品
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

	//获取特定ID的菜品类型
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

	//删除菜品
	public boolean deleteFoodById(int id) {
		try {
			String sql="delete from food where id = ?";
			return JDBCTemplate.update(sql,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//寻找菜品
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

	//更新菜品
    public boolean updateFood(Map food) {
		System.out.println(food);
		String sql = "update food set foodname=?,feature=?,material=?,price=?,type=?,picture=?,hits=?,comment=? where id=?";
		String fn = (String)food.get("f_un");
		System.out.println("FoodDao在修改菜品信息的时候已经获取到："+fn);
		return JDBCTemplate.update(sql, food.get("f_un"), food.get("fea"), food.get("mat"), food.get("price"), food.get("type"), food.get("img"),0, food.get("com"), food.get("id"));
    }
}
