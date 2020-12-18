package com.example.mvc.meal.services;
/**
 * 该类为Food的“服务控制层”
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

import com.example.mvc.meal.daos.FoodDao;

public class FoodService {

	FoodDao dao = new FoodDao();

	//获取热点菜品清单
	public List<Map> getHotFoods() {
		return dao.getHotFoods();
	}

	//获取特价菜品清单
	public List<Map> getSaleFoods() {
		return dao.getSalesFoods();
	}

	//获取厨师推荐菜品清单
	public List<Map> getRecommedFoods() {
		return dao.getRecommedFoods();
	}

	//基于搜索获取菜品清单
	public Map getFoods(String name ,String f_type, int no) {
		return dao.getFoods(name,f_type,no);
	}

	//添加菜品
	public boolean addFood(Map<String, String> food) {
		return dao.addFood(food);
	}

	//基于ID获取菜品清单
	public Map findfoodById(String id) {
		return dao.findfoodById(id);
	}

	//基于ID获取菜品类型
    public Map findfoodtype_id(String id) {
		return dao.findfoodtype_id(id);
    }

    //删除菜品
    public boolean deleteFoodById(int id) {
		return dao.deleteFoodById(id);
    }

    //基于ID获取菜品清单
	public Map findFoodById(int id) {
		return dao.findFoodById(id);
	}

	//更新菜品
    public boolean updateFood(Map food) {
		System.out.println("Service  updateFood已访问到");
		return dao.updateFood(food);
    }
}
