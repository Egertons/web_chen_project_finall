package com.example.mvc.meal.actions;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.CartService;
import com.example.mvc.meal.services.FoodService;
import com.example.mvc.meal.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
	CartService cartService = new CartService();
	UserService userService = new UserService();
	FoodService foodService = new FoodService();

	@RequestMapping("/user/add_cart")
	public ModelAndView add_cart(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		Map user = (Map)request.getSession().getAttribute("user");
		int user_id = (Integer) user.get("id");
		int count = cartService.addCart(user_id,ids);
		ModelAndView mv = new ModelAndView("result");
		mv.addObject("msg","您成功添加了"+count+"件商品到购物车！");
		mv.addObject("href",request.getContextPath()+"/user/show_cart.do");
		return mv;
	}

	@RequestMapping("/user/show_cart")
	public ModelAndView show_cart(HttpSession session) {
		ModelAndView mv = new ModelAndView("user/show_cart");
		Map user = (Map)session.getAttribute("user");
		int user_id = (Integer) user.get("id");
		List carts = cartService.getCartByUserid(user_id);
		mv.addObject("carts",carts);
		return mv;
	}
	@RequestMapping("/admin/cart_show")
	public ModelAndView cart_show(HttpSession session){
		ModelAndView mv = new ModelAndView("admin/cart_show");
		List<Map> carts = cartService.getAllCart();
		System.out.println(carts);
		mv.addObject("carts",carts);
		return mv;
	}

	@RequestMapping("/user/del_cart")
	public ModelAndView removeCart(HttpServletRequest request) {
		String[] ids=request.getParameterValues("ids");
		int count=cartService.removeCartsByIds(ids);

		ModelAndView mv=new ModelAndView("result");
		mv.addObject("msg", "您成功移除了"+count+"件商品");
		mv.addObject("href", request.getContextPath()+"/user/show_cart.do");
		return mv;
	}
}