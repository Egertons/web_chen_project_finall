package com.example.mvc.meal.actions;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.model.ModelAndView;
import com.example.mvc.meal.services.FoodService;
import com.example.mvc.meal.services.TypeService;

@Controller
public class FoodController {
	FoodService foodService = new FoodService();
	TypeService typeService = new TypeService();

	@RequestMapping("/admin/food_list")
	public ModelAndView userList(String c_un,String type,String pageno) {
		String name = "";
		if(c_un!=null) {
			name="%"+c_un.trim()+"%";
		}else {
			name="%%";
		}

		String f_type = "";
		if(type!=null){
			f_type="%"+type.trim()+"%";
		}else{
			f_type="%%";
		}

		int no = 1;
		if(pageno!=null) {
			no=Integer.parseInt(pageno);
		}
		Map foods = foodService.getFoods(name,f_type,no);
		ModelAndView mv = new ModelAndView("admin/food_list");
		mv.addObject("c_un", c_un);
		mv.addObject("pageno", pageno);
		mv.addObject("foods", foods);
		return mv;
	}


	@RequestMapping("/admin/modify_food")
	public ModelAndView foodModifyForm(int id) {
		ModelAndView mv = new ModelAndView("admin/food_modify_form");
		Map food = foodService.findFoodById(id);
		mv.addObject("food", food);
		List types=typeService.getAllType();
		mv.addObject("types", types);
		return mv;
	}

	@RequestMapping("/admin/food_update")
	public ModelAndView foodUpdate(HttpServletRequest request) throws Exception {
		System.out.println("基础food_update映射方法已访问到");
		ModelAndView mv = new ModelAndView("result");
			Map food = this.processUploadForm(request);
			System.out.println("此时已获取到food："+food.get("f_un"));
			boolean result=foodService.updateFood(food);
			System.out.println("此时result为："+result);
			if(result) {
				mv.addObject("msg", "菜品修改成功");
			}else {
				mv.addObject("msg", "菜品修改失败");
			}
		mv.addObject("href", request.getContextPath()+"/admin/food_list.do");
		return mv;
	}

	@RequestMapping("/admin/foodaddform")
	public ModelAndView AddForm() {
		ModelAndView mv=new ModelAndView("admin/food_add_form");
		List types=typeService.getAllType();
		mv.addObject("types", types);
		return mv;
	}


	//针对文件上传的工具类
	private Map processUploadForm(HttpServletRequest request) throws Exception{
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = request.getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> iter = items.iterator();
		Map data=new HashMap();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) {
				data.put(item.getFieldName(), item.getString("UTF-8"));
			} else {
				String savePath=URLDecoder.decode(request.getServletContext().getRealPath("/uploads"));
				File saveFolder=new File(savePath);
				if(!saveFolder.exists()) saveFolder.mkdir();
				String fileName=item.getName().substring(item.getName().lastIndexOf(File.separator)>0?item.getName().lastIndexOf(File.separator):0);
				File saveFile=new File(savePath+File.separator+fileName);
				if( saveFile.exists()) saveFile.delete();
				item.write(saveFile);
				data.put("img", "uploads"+"/"+fileName);
			}
		}
		return data;
	}


	@RequestMapping("/admin/food_add")
	public ModelAndView addFood(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("result");
		try {
			Map food = this.processUploadForm(request);
			boolean result=foodService.addFood(food);
			if(result) {
				mv.addObject("msg", "菜品添加成功");
			}else {
				mv.addObject("msg", "菜品添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("href", request.getContextPath()+"/admin/food_list.do");
		return mv;
	}

	//管理员界面的菜品删除
	@RequestMapping("/admin/del_food")
	public ModelAndView deleteFood(HttpServletRequest request, int id) {
		ModelAndView mv = new ModelAndView("result");
		boolean result = foodService.deleteFoodById(id);
		if(result) {
			mv.addObject("msg", "菜品删除成功");
		}else {
			mv.addObject("msg", "菜品删除成功");
		}
		mv.addObject("href", request.getContextPath()+"/admin/food_list.do");
		return mv;
	}
}
