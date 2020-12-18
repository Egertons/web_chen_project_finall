package com.example.mvc.meal.actions;
/**
 * 该类为主页面以及网站中主要业务逻辑的“前端控制器”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.CartService;
import com.example.mvc.meal.services.FoodService;
import com.example.mvc.meal.services.TypeService;
import com.example.mvc.meal.services.UserService;

@Controller
public class HomeController {
	FoodService foodService = new FoodService();
	TypeService typeService = new TypeService();
	UserService userService = new UserService();

	//网站首页
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

	//用户登出
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:index.do";
	}

	//网站首页中各个菜品的"详情介绍"
	@RequestMapping("/show_detail")
	public ModelAndView show_detail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/show_detail");
		String id = request.getParameter("id");
		Map food = foodService.findfoodById(id);
		Map f_type_id = foodService.findfoodtype_id(id);
		int type_id = (int)f_type_id.get("type");
		Map foodtype = typeService.findTypeById(type_id);
		mv.addObject("food", food);
		mv.addObject("foodtype",foodtype);
		return mv;
	}
}