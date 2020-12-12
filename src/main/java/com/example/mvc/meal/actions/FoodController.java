package com.example.mvc.meal.actions;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.FoodService;
import com.example.mvc.meal.services.TypeService;

@Controller
public class FoodController {
	FoodService foodService = new FoodService();

	@RequestMapping("/admin/food_list")
	public ModelAndView userList(String c_un,String type,String pageno) {
		String name = "";
		if(c_un!=null) {
			name="%"+c_un.trim()+"%";
		}else {
			name="%%";
		}

		String f_type = "";
		if(type!=null){
			f_type="%"+type.trim()+"%";
		}else{
			f_type="%%";
		}

		int no = 1;
		if(pageno!=null) {
			no=Integer.parseInt(pageno);
		}
		Map foods = foodService.getFoods(name,f_type,no);
		ModelAndView mv = new ModelAndView("admin/food_list");
		mv.addObject("c_un", c_un);
		mv.addObject("pageno", pageno);
		mv.addObject("foods", foods);
		return mv;
	}
	@RequestMapping("/admin/foodaddform")
	public String foodAddForm() {
		return "admin/food_add_form";
	}


	@RequestMapping("/admin/food_add")
	public String foodAdd(HttpServletRequest request) {
		String fn=request.getParameter("fn");
		String caipinte=request.getParameter("caipinte");
		String yuanliao=request.getParameter("yuanliao");
		String type=request.getParameter("type");
		String price=request.getParameter("price");
		String comment = request.getParameter("comment");
		Map<String,String> food=new HashMap();
		food.put("fn", fn);
		food.put("caipinte", caipinte);
		food.put("yuanliao", yuanliao);
		food.put("type", type);
		food.put("price", price);
		food.put("comment", comment);
		boolean result=foodService.addFood(food);
		if(result) {
			request.setAttribute("msg", "添加成功");
		}else {
			request.setAttribute("msg", "添加失败");
		}
		request.setAttribute("href", request.getContextPath()+"/admin/food_list.do");
		return "result";
	}

	//管理员界面的菜品删除
	@RequestMapping("/admin/del_food")
	public ModelAndView deleteFood(HttpServletRequest request, int id) {
		ModelAndView mv = new ModelAndView("result");
		boolean result = foodService.deleteFoodById(id);
		if(result) {
			mv.addObject("msg", "菜品删除成功");
		}else {
			mv.addObject("msg", "菜品删除成功");
		}
		mv.addObject("href", request.getContextPath()+"/admin/food_list.do");
		return mv;
	}
}
