package com.example.mvc.meal.actions;
/**
 * 该类为购物车相关页面的“前端控制器”
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
import com.example.mvc.meal.services.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
	CartService cartService = new CartService();

	//用于管理员界面管理“用户点餐情况”
	@RequestMapping("/admin/cart_show")
	public ModelAndView cart_show(HttpSession session){
		ModelAndView mv = new ModelAndView("admin/cart_show");
		List<Map> carts = cartService.getAllCart();
		mv.addObject("carts",carts);
		return mv;
	}

	//用于普通用户界面上的添加购物车
	@RequestMapping("/user/add_cart")
	public ModelAndView add_cart(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("result");
		String[] ids = request.getParameterValues("ids");
		Map user = (Map)request.getSession().getAttribute("user");
		int user_id = (Integer) user.get("id");
		int count = cartService.addCart(user_id,ids);
		mv.addObject("msg","您成功添加了"+count+"件商品到购物车！");
		mv.addObject("href",request.getContextPath()+"/user/show_cart.do");
		return mv;
	}

	//用于普通用户删除购物车商品
	@RequestMapping("/user/del_cart")
	public ModelAndView removeCart(HttpServletRequest request) {
		String[] ids=request.getParameterValues("ids");
		int count=cartService.removeCartsByIds(ids);
		ModelAndView mv=new ModelAndView("result");
		mv.addObject("msg", "您成功移除了"+count+"件商品");
		mv.addObject("href", request.getContextPath()+"/user/show_cart.do");
		return mv;
	}

	//用于普通用户界面的显示购物车
	@RequestMapping("/user/show_cart")
	public ModelAndView show_cart(HttpSession session) {
		ModelAndView mv = new ModelAndView("user/show_cart");
		Map user = (Map)session.getAttribute("user");
		int user_id = (Integer) user.get("id");
		List carts = cartService.getCartByUserid(user_id);
		mv.addObject("carts",carts);
		return mv;
	}
}