package com.example.mvc.meal.actions;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.CartService;

import java.util.List;

@Controller
public class CartController {
	CartService cartService = new CartService();
	@RequestMapping("/admin/cart_show")
	public ModelAndView cart_show() {
		ModelAndView mv = new ModelAndView("admin/cart_show");
		return mv;
	}
}
