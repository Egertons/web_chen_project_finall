package com.example.mvc.meal.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.TypeService;

@Controller
public class TypeController {
	TypeService typeService = new TypeService();

	@RequestMapping("/admin/type_list")
	public ModelAndView typeList(String t_un,String pageno) {
		String name = "";
		System.out.println("这里接收到的检索菜品类型为："+t_un);
		if(t_un!=null) {
			name="%"+t_un.trim()+"%";
		}else {
			name="%%";
		}

		int no = 1;
		if(pageno!=null) {
			no=Integer.parseInt(pageno);
		}
		Map types = typeService.getTypes(name,no);
		ModelAndView mv = new ModelAndView("admin/type_list");
		mv.addObject("t_un", t_un);
		mv.addObject("pageno", pageno);
		mv.addObject("types", types);
		return mv;
	}

	@RequestMapping("/admin/foodtypeaddform")
	public String typeAddForm() {
		return "admin/type_add_form";
	}


	@RequestMapping("/admin/type_add_form")
	public String foodAdd(HttpServletRequest request) {
		String tn=request.getParameter("tn");
		Map<String,String> type = new HashMap();
		type.put("tn", tn);
		boolean result = typeService.addType(type);
		if(result) {
			request.setAttribute("msg", "添加成功");
		}else {
			request.setAttribute("msg", "添加失败");
		}
		request.setAttribute("href", request.getContextPath()+"/admin/type_list.do");
		return "result";
	}

	@RequestMapping("/admin/del_type")
	public ModelAndView deleteType(HttpServletRequest request, int id) {
		ModelAndView mv = new ModelAndView("result");
		boolean result = typeService.deleteTypeById(id);
		if(result) {
			mv.addObject("msg", "类型删除成功");
		}else {
			mv.addObject("msg", "类型删除成功");
		}
		mv.addObject("href", request.getContextPath()+"/admin/type_list.do");
		return mv;
	}


	@RequestMapping("/admin/modify_type")
	public ModelAndView typeModifyForm(int id) {
		ModelAndView mv = new ModelAndView("admin/type_modify_form");
		Map type = typeService.findTypeById(id);
		mv.addObject("type", type);
		return mv;
	}


	@RequestMapping("/admin/update_type")
	public ModelAndView updateType(HttpServletRequest request) {
		String id = request.getParameter("id");
		String tn = request.getParameter("tn");
		ModelAndView mv = new ModelAndView("result");
		Map<String,String> type = new HashMap();
		type.put("id",id);
		type.put("tn",tn);
		boolean result = typeService.updateType(type);
		if(result) {
			mv.addObject("msg", "类型修改成功");
		}else {
			mv.addObject("msg", "类型修改成功");
		}
		mv.addObject("href", request.getContextPath()+"/admin/type_list.do");
		return mv;
	}
}
