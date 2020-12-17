package com.example.mvc.meal.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.daos.UserDao;
import com.example.mvc.meal.services.CartService;
import com.example.mvc.meal.services.FoodService;
import com.example.mvc.meal.services.TypeService;
import com.example.mvc.meal.services.UserService;

//前端控制器
@Controller
public class HomeController {
	FoodService foodService = new FoodService();
	TypeService typeService = new TypeService();
	CartService cartService = new CartService();
	UserService userService = new UserService();

	//网站的首页
	@RequestMapping("/index")
	public ModelAndView homepage(){
		List<Map> hots = foodService.getHotFoods();//获取热点菜品列表
		List<Map> sales = foodService.getSaleFoods();//获取特价菜品列表
		List<Map> recommeds = foodService.getRecommedFoods();//获取厨师推荐菜品列表
		ModelAndView mv = new ModelAndView("homepage");
		mv.addObject("hots", hots);
		mv.addObject("sales", sales);
		mv.addObject("recommeds", recommeds);
		return mv;
	}

	//网站首页中各个菜品的详情介绍
	@RequestMapping("/show_detail")
	public ModelAndView show_detail(HttpServletRequest request){
		String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView("/show_detail");
		Map food = foodService.findfoodById(id);
		Map f_type_id = foodService.findfoodtype_id(id);
		String type_id = f_type_id.get("type").toString();
		Map foodtype = typeService.findtypeById(type_id);
		mv.addObject("food", food);
		mv.addObject("foodtype",foodtype);
		return mv;
	}



	//首页注册
	@RequestMapping("/register")
	public String reg(HttpServletRequest request) throws ServletException, IOException {
		String un = request.getParameter("un");
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		Map<String,String> user = new HashMap();
		user.put("un", un);
		user.put("pw", pw);
		user.put("ident", "0");
		user.put("tel", tel);
		user.put("addr", addr);
		boolean result = userService.addUser(user);
		if(result) {
			request.setAttribute("msg", "注册成功");
		}else {
			request.setAttribute("msg", "注册失败");
		}
		request.setAttribute("href", request.getContextPath()+"/index.do");
		return "result";
	}

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
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:index.do";
	}
	//首页登录
	@RequestMapping("/login")
	public String login(String un, String pw,HttpSession session, HttpServletRequest request){
		if(un==null||pw==null||un.trim().equals("")||pw.trim().equals("")) {
			request.setAttribute("msg", "用户名密码不能为空");
			request.setAttribute("href", request.getContextPath()+"/index.do");
			return "result";
		}else {
			Map user=userService.findUser(un,pw);
			if(user==null) {
				request.setAttribute("msg", "用户名密码错误");
				request.setAttribute("href", request.getContextPath()+"/index.do");
				return "result";
			}else {
				session.setAttribute("user", user);
				String ident=(String)user.get("ident");
				if("1".equals(ident)) {
					return "redirect:admin/user_list.do";
				}else {
					return "redirect:user/user_index.do";
				}
			}
		}
	}
}