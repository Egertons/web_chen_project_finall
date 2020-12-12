package com.example.mvc.meal.actions;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.meal.services.CartService;

@Controller
public class CartController {
	CartService cartService = new CartService();
}
