package com.example.mvc.meal.actions;
/**
 * 该类为用户诸多的功能的“前端控制器”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.FoodService;
import com.example.mvc.meal.services.TypeService;
import com.example.mvc.meal.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	UserService userService = new UserService();
	FoodService foodService = new FoodService();
	TypeService typeService = new TypeService();

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

	//普通用户首页
	@RequestMapping("/user/user_index")
	public ModelAndView UserIndex(HttpServletRequest request) throws ServletException, IOException {
		String s_name = request.getParameter("s_fn");
		String name = "";
		if(s_name!=null) {
			name="%"+s_name.trim()+"%";
		}else {
			name="%%";
		}
		String s_type = request.getParameter("s_type");
		String f_type = "";
		if(s_type!=null){
			f_type="%"+s_type.trim()+"%";
		}else{
			f_type="%%";
		}
		String pageno = request.getParameter("pageno");
		int page_no;
		if(pageno!=null){
			page_no=Integer.parseInt(pageno);
		}else{
			page_no=1;
		}
		ModelAndView mv=new ModelAndView("user/user_index");
		Map foods=foodService.getFoods(name,f_type,page_no);
		List types=typeService.getAllType();
		mv.addObject("foods", foods);
		mv.addObject("types", types);
		mv.addObject("s_name", s_name);
		mv.addObject("s_type", s_type);
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

	//普通用户页面的用户更新
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
