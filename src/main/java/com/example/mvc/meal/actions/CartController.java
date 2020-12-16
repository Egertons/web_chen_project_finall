package com.example.mvc.meal.actions;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.CartService;
import com.example.mvc.meal.services.FoodService;
import com.example.mvc.meal.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
	CartService cartService = new CartService();
	UserService userService = new UserService();
	FoodService foodService = new FoodService();
	@RequestMapping("/admin/cart_show")
	public ModelAndView cart_show() {
		ModelAndView mv = new ModelAndView("admin/cart_show");
		return mv;
	}

	/*
	* public ModelAndView AdminModifyForm(int id) {
		ModelAndView mv = new ModelAndView("admin/user_modify_form");
		Map user = userService.findUserById(id);
		mv.addObject("user", user);
		return mv;
	}
	* */

	@RequestMapping("/user/add_cart")
	public String add_cart(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		Map session_user = (Map)request.getSession().getAttribute("user");
		String user_id = session_user.get("id").toString();
		Map<String,String> cart_thing = new HashMap();
		cart_thing.put("ids", ids);
		cart_thing.put("user_id", user_id);
		boolean result = cartService.addCart(cart_thing);
		if(result) {
			request.setAttribute("msg", "添加购物车成功");
		}else {
			request.setAttribute("msg", "添加购物车失败");
		}
		request.setAttribute("href", request.getContextPath()+"/user/show_cart.do");
		return "result";
	}

	@RequestMapping("/user/show_cart")
	public ModelAndView show_cart(String pageno) {
		int no = 1;
		if(pageno!=null) {
			no = Integer.parseInt(pageno);
		}
		Map foods = cartService.getAll(no);
		ModelAndView mv=new ModelAndView("user/show_cart");
		mv.addObject("foods", foods);
		mv.addObject("pageno", pageno);
		return mv;
	}
}