package com.example.mvc.meal.actions;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
	UserService userService = new UserService();

	//登录后的详情页（首页）(管理员界面)
	@RequestMapping("/admin/user_list")
	public ModelAndView userList(String s_un,String pageno) {
		String name="";
		if(s_un!=null) {
			name="%"+s_un.trim()+"%";
		}else {
			name="%%";
		}
		int no=1;
		if(pageno!=null) {
			no=Integer.parseInt(pageno);
		}
		Map users = userService.getUsers(name,no);
		ModelAndView mv=new ModelAndView("admin/user_list");
		mv.addObject("s_un", s_un);
		mv.addObject("pageno", pageno);
		mv.addObject("users", users);
		return mv;
	}


	//管理员界面的用户详情信息修改
	@RequestMapping("/admin/modify_user")
	public ModelAndView AdminModifyForm(int id) {
		ModelAndView mv = new ModelAndView("admin/user_modify_form");
		Map user = userService.findUserById(id);
		mv.addObject("user", user);
		return mv;
	}
	//普通用户界面的用户详情信息修改
	@RequestMapping("/user/user_modify")
	public ModelAndView userModifyForm(int id) {
		ModelAndView mv = new ModelAndView("user/user_modify_form");
		Map user = userService.findUserById(id);
		mv.addObject("user", user);
		return mv;
	}

	//供页面跳转到“添加用户”界面
	@RequestMapping("/admin/useraddform")
	public String userAddForm() {
		return "admin/user_add_form";
	}

	@RequestMapping("/user/show_cart")
	public String show_cart() {
		return "user/show_cart";
	}

	//供管理员页面中“添加用户”的特定jsp的数据处理
	@RequestMapping("/admin/user_add")
	public String userAdd(HttpServletRequest request) {
		String un=request.getParameter("un");
		String pw=request.getParameter("pw");
		String tel=request.getParameter("tel");
		String addr=request.getParameter("addr");
		String ident=request.getParameter("ident");
		Map<String,String> user=new HashMap();
		user.put("un", un);
		user.put("pw", pw);
		user.put("ident", ident);
		user.put("tel", tel);
		user.put("addr", addr);
		boolean result=userService.addUser(user);
		if(result) {
			request.setAttribute("msg", "添加成功");
		}else {
			request.setAttribute("msg", "添加失败");
		}
		request.setAttribute("href", request.getContextPath()+"/admin/user_list.do");
		return "result";
	}

	//管理员页面的用户更新
	@RequestMapping("/admin/user_update")
	public String userUpdate(HttpServletRequest request) {
		String id=request.getParameter("id");
		String un=request.getParameter("un");
		String pw=request.getParameter("pw");
		String tel=request.getParameter("tel");
		String addr=request.getParameter("addr");
		String ident=request.getParameter("ident");
		Map<String,String> user=new HashMap();
		user.put("id", id);
		user.put("un", un);
		user.put("pw", pw);
		user.put("ident", ident);
		user.put("tel", tel);
		user.put("addr", addr);
		boolean result=userService.updateUser(user);
		if(result) {
			request.setAttribute("msg", "更新成功");
		}else {
			request.setAttribute("msg", "更新失败");
		}
		request.setAttribute("href", request.getContextPath()+"/admin/user_list.do");
		return "result";
	}

	@RequestMapping("/user/user_update")
	public String PuTonguserUpdate(HttpServletRequest request) {
		String id=request.getParameter("id");
		String un=request.getParameter("un");
		String pw=request.getParameter("pw");
		String tel=request.getParameter("tel");
		String addr=request.getParameter("addr");
		Map<String,String> user=new HashMap();
		user.put("id", id);
		user.put("un", un);
		user.put("pw", pw);
		user.put("tel", tel);
		user.put("addr", addr);
		boolean result=userService.PuTongupdateUser(user);
		if(result) {
			request.setAttribute("msg", "更新成功");
		}else {
			request.setAttribute("msg", "更新失败");
		}
		request.setAttribute("href", request.getContextPath()+"/user/user_index.do");
		return "result";
	}
	//管理员界面的用户删除
	@RequestMapping("/admin/del_user")
	public ModelAndView deleteUser(HttpServletRequest request, int id) {
		ModelAndView mv=new ModelAndView("result");
		boolean result=userService.deleteUserById(id);
		if(result) {
			mv.addObject("msg", "用户删除成功");
		}else {
			mv.addObject("msg", "用户删除成功");
		}
		mv.addObject("href", request.getContextPath()+"/admin/user_list.do");
		return mv;
	}
}
