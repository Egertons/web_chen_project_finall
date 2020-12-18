package com.example.mvc.meal.actions;
/**
 * 该类为Food相关页面以及业务逻辑的“前端控制器”
 *
 * @author ZhangLin
 * @version $Revision: 12.18 2020/12/18
 *
 * 变更记录
 * NO　　　  日期             责任人             变更类型           具体内容
 * 01　　    2020/12/18      张  霖           代码格式规范　　　　
 */
import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
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

	//该类为管理员页面“菜品管理”的前端控制
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

	//该类为管理员页面“菜品修改”的前端“跳转”控制
	@RequestMapping("/admin/modify_food")
	public ModelAndView foodModifyForm(int id) {
		ModelAndView mv = new ModelAndView("admin/food_modify_form");
		Map food = foodService.findFoodById(id);
		mv.addObject("food", food);
		List types=typeService.getAllType();
		mv.addObject("types", types);
		return mv;
	}

	//该类为管理员页面“菜品更新”的前端控制
	@RequestMapping("/admin/food_update")
	public ModelAndView foodUpdate(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("result");
		Map food = this.processUploadForm(request);
		boolean result = foodService.updateFood(food);
		if(result) {
			mv.addObject("msg", "菜品修改成功");
		}else {
			mv.addObject("msg", "菜品修改失败");
		}
		mv.addObject("href", request.getContextPath()+"/admin/food_list.do");
		return mv;
	}

	//该类为管理员页面关于“菜品添加”的前端“跳转”控制
	@RequestMapping("/admin/foodaddform")
	public ModelAndView AddForm() {
		ModelAndView mv=new ModelAndView("admin/food_add_form");
		List types=typeService.getAllType();
		mv.addObject("types", types);
		return mv;
	}

	//该类为管理员页面关于“菜品添加”的前端控制
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

	//针对文件上传的工具类
	private Map processUploadForm(HttpServletRequest request) throws Exception{
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = request.getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);
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
}
