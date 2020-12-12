package com.example.mvc.meal.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.TypeService;

@Controller
public class TypeController {
	TypeService service = new TypeService();
}
