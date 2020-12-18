package com.example.mvc.meal.actions;
/**
 * 该类为菜品类型的“前端控制器”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.TypeService;

@Controller
public class TypeController {
	TypeService typeService = new TypeService();

	//管理员页面”菜品类型“首页
	@RequestMapping("/admin/type_list")
	public ModelAndView typeList(String t_un,String pageno) {
		String name = "";
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

	//管理员页面”菜品类型添加“跳转
	@RequestMapping("/admin/foodtypeaddform")
	public String typeAddForm() {
		return "admin/type_add_form";
	}

	//管理员页面”菜品类型添加“逻辑
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

	//管理员页面”菜品类型修改“跳转
	@RequestMapping("/admin/modify_type")
	public ModelAndView typeModifyForm(int id) {
		ModelAndView mv = new ModelAndView("admin/type_modify_form");
		Map type = typeService.findTypeById(id);
		mv.addObject("type", type);
		return mv;
	}

	//管理员页面”菜品类型修改“逻辑
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

	//管理员页面”菜品类型删除“逻辑
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
}
