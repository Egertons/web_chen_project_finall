package com.example.mvc.framework;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.mvc.framework.annotations.Controller;
import com.example.mvc.framework.annotations.RequestHeader;
import com.example.mvc.framework.annotations.RequestMapping;
import com.example.mvc.framework.annotations.RequestParam;
import com.example.mvc.framework.annotations.ResponseBody;
import com.example.mvc.framework.enums.RequestMethod;
import com.example.mvc.framework.model.Handler;
import com.example.mvc.framework.model.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class FrontController
 */

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] packages = null;
	private String viewLocation = "";
	private String suffix = ".jsp";
	private Map<String, Handler> mapping = new HashMap<String, Handler>();
	private DateFormat dateFormat = null;
	private String encoding="utf-8";
	@Override
	public void init() throws ServletException {
		this.packages = this.getInitParameter("packages").split(";");
		this.viewLocation = this.getInitParameter("view_location");
		this.suffix = this.getInitParameter("suffix");
		ResourceBundle bundle = ResourceBundle.getBundle("setting");
		String format = bundle.getString("dateFormat");
		encoding=bundle.getString("encoding");
		if (format != null) {
			dateFormat = new SimpleDateFormat(format);
		} else {
			dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		}
		this.scanComponent();
	}

	@SuppressWarnings("deprecation")
	private void scanComponent() {
		 String classPath = this.getClass().getResource("/").getPath();
		    classPath=URLDecoder.decode(classPath);
		for (int i = 0; i < this.packages.length; i++) {
			String packagePath = this.packages[i].replace(".", "\\");
			File folder = new File(classPath + packagePath);
			if (folder.exists()) {
				String[] files = folder.list();
				for (int j = 0; j < files.length; j++) {
					String file = files[j].substring(0, files[j].lastIndexOf("."));
					try {
						Class<?> clazz = Class.forName(this.packages[i] + "." + file);
						Annotation ann = clazz.getAnnotation(Controller.class);
						if (ann != null) {
							this.processMapping(clazz.newInstance(), clazz);
						}
					} catch (ClassNotFoundException | IllegalAccessException 
							| InstantiationException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void processMapping(Object target, Class<?> clazz) {
		RequestMapping clazzAnnotation = clazz.getAnnotation(RequestMapping.class);
		String[] parentMapping = null;
		if (clazzAnnotation != null) {
			parentMapping = clazzAnnotation.value();
		}
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
			if (methodAnnotation != null) {
				String[] childMapping = methodAnnotation.value();
				for (int j = 0; j < childMapping.length; j++) {
					if (parentMapping != null) {
						for (int k = 0; k < parentMapping.length; k++) {
							if (!parentMapping[k].startsWith("/") || !childMapping[j].startsWith("/")) {
								throw new RuntimeException(clazz.getName() + "路径配置错误");
							} else {
								String path = parentMapping[k] + childMapping[j];
								this.mappingItem(path, target, method);
							}
						}
					} else {
						if (!childMapping[j].startsWith("/")) {
							throw new RuntimeException(clazz.getName() + "路径配置错误");
						} else {
							this.mappingItem(childMapping[j], target, method);
						}
					}
				}
			}
		}
	}

	private void mappingItem(String path, Object target, Method method) {
		RequestMethod[] requestMethods = method.getAnnotation(RequestMapping.class).requestMethods();
		Handler handler = new Handler(target, method);
		if (requestMethods.length == 0) {
			String key = path;
			if (this.mapping.get(key) != null) {
				throw new RuntimeException(method.getDeclaringClass().getName() + " " + path + "路径已经映射");
			} else {
				this.mapping.put(key, handler);
			}
		} else {
			for (int l = 0; l < requestMethods.length; l++) {
				String key = "";
				key = requestMethods[l].getMethod() + path;
				if (this.mapping.get(path) != null) {
					throw new RuntimeException(method.getDeclaringClass().getName() + " " + path + "路径已经映射");
				} else {
					this.mapping.put(key, handler);
				}
			}
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(this.encoding);
		response.setCharacterEncoding(this.encoding);
		String URI = request.getRequestURI();
		URI=URI.substring(URI.indexOf(this.getServletContext().getContextPath())
				           +this.getServletContext().getContextPath().length(),URI.lastIndexOf("."));
		String method = request.getMethod().toLowerCase();
		String key = method + URI;
		Handler handler = this.mapping.get(key);
		if (handler == null) {
			handler = this.mapping.get(URI);
		}
		if (handler == null) {
				throw new RuntimeException("请求地址错误，无法正常处理");
		} else {
			//将请求数据提取，并按照方法参数列表顺序组织成数组
			Object[] parameterObjects = this.populateData(request, response, handler.getMethod());
			try {
				//执行业务逻辑方法
				Object result = handler.getMethod().invoke(handler.getTarget(),parameterObjects);
				//如果处理方法标注@ResponseBody,将返回值输出为JSON格式
				if (handler.getMethod().getAnnotation(ResponseBody.class) != null) {
					ObjectMapper mapper = new ObjectMapper();
					response.setContentType("application/json;charset=utf-8");
					mapper.writeValue(response.getWriter(), result);
				}else if(result instanceof ModelAndView){
					//如果返回值为类型为ModelAndView，则将数据放置到reqeust属性中，并转发到相应页面
					ModelAndView mv=(ModelAndView)result;
					String view=mv.getView();
					Map<String,Object> model=(Map<String,Object>)mv.getModel();
					model.forEach((k,v)->{
						request.setAttribute(k, v);
					});
					RequestDispatcher rd = request
							.getRequestDispatcher(this.viewLocation +"/"+ view + this.suffix);
					rd.forward(request, response);
				} else {
					//返回值为字符串，用于表示要显示的视图页面，如果以redirect:开头，则以重定向的方式跳转，否则转发
						String page = (String) result;
						int index = page.indexOf(":");
						if (index > 0) {
							String way = page.substring(0, index);
							page = page.substring(index + 1);
							if ("redirect".equals(way)) {
								response.sendRedirect(page);
							} else {
								RequestDispatcher rd = request
										.getRequestDispatcher(this.viewLocation +"/"+ page + this.suffix);
								rd.forward(request, response);
							}

						} else {
							RequestDispatcher rd = request.getRequestDispatcher(this.viewLocation+"/" + page + this.suffix);
							rd.forward(request, response);
						}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				throw new RuntimeException("框架调用错误，无法正常处理");
			}
		}
	}

	private Object[] populateData(HttpServletRequest request, HttpServletResponse response, Method method) {
		List<Object> parameterObject = new ArrayList<Object>();
		Parameter[] parameters = method.getParameters();
		for (Parameter parameter : parameters) {
			RequestParam paramAnotation=parameter.getAnnotation(RequestParam.class);
			RequestHeader headerAnotation=parameter.getAnnotation(RequestHeader.class);
			String paramName=parameter.getName();
			//处理RequestParam注解出现的情况
			if(paramAnotation!=null){
				paramName=paramAnotation.value();
			}
			String value=null;
			//处理RequestHeader出现的情况
			if(headerAnotation!=null){
				value=request.getHeader(headerAnotation.value());
			}else{
			   value = request.getParameter(paramName);
			}
			if (parameter.getType().isAssignableFrom(HttpServletRequest.class)) {
				parameterObject.add(request);
			} else if (parameter.getType().isAssignableFrom(HttpServletResponse.class)) {
				parameterObject.add(response);
			} else if (parameter.getType().isAssignableFrom(HttpSession.class)) {
				parameterObject.add(request.getSession());
			} else if (parameter.getType().isAssignableFrom(ServletContext.class)) {
				parameterObject.add(this.getServletContext());
			} else if (parameter.getType().isAssignableFrom(byte.class)
					|| parameter.getType().isAssignableFrom(Byte.class)) {
				parameterObject.add(Byte.valueOf(value));
			}else if (parameter.getType().isAssignableFrom(short.class)
					|| parameter.getType().isAssignableFrom(Short.class)) {		
				parameterObject.add(Short.valueOf(value));
			}  else if (parameter.getType().isAssignableFrom(int.class)
					|| parameter.getType().isAssignableFrom(Integer.class)) {
				parameterObject.add(Integer.valueOf(value));
			} else if (parameter.getType().isAssignableFrom(long.class)
					|| parameter.getType().isAssignableFrom(Long.class)) {				
				parameterObject.add(Long.valueOf(value));
			} else if (parameter.getType().isAssignableFrom(float.class)
					|| parameter.getType().isAssignableFrom(Float.class)) {
				parameterObject.add(Float.valueOf(value));
			} else if (parameter.getType().isAssignableFrom(double.class)
					|| parameter.getType().isAssignableFrom(Double.class)) {
				parameterObject.add(Double.valueOf(value));
			} else if (parameter.getType().isAssignableFrom(String.class)) {	
				parameterObject.add(value);
			} else if (parameter.getType().isAssignableFrom(String[].class)) {
				String[] values=null;
				if(headerAnotation!=null){
					Enumeration<String> headers=request.getHeaders(paramName);
					List<String> headerValues=new ArrayList<String>();
					while(headers.hasMoreElements()){
						headerValues.add(headers.nextElement());
					}
				 values=headerValues.toArray(new String[0]);			
				}else{
				   values= request.getParameterValues(paramName);
				}
				parameterObject.add(values);
			} else if (parameter.getType().isAssignableFrom(Map.class)) {
				parameterObject.add(request.getParameterMap());
			} else if (parameter.getType().isAssignableFrom(Date.class)) {
				try {
					Date date = dateFormat.parse(value);
					parameterObject.add(date);
				} catch (Exception e) {
					parameterObject.add(null);
				}
			} else if (!parameter.getType().isInterface()) {
				String contentType = request.getContentType();
				if (contentType!=null&&contentType.contains("application/json")) {
					parameterObject.add(populateJSONData(request, parameter.getType()));
				} else {
					parameterObject.add(populateFormData(request, parameter.getType()));
				}
			} else {
				parameterObject.add(null);
			}
		}
		return parameterObject.toArray();
	}

	private <T> T populateJSONData(HttpServletRequest request, Class<T> type) {
		ObjectMapper mapper = new ObjectMapper();
		T obj = null;
		try {
			obj = mapper.readValue(request.getInputStream(), type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			obj = null;
		}
		return obj;
	}

	private <T> T populateFormData(HttpServletRequest request, Class<T> clazz) {
		T bean = null;
		try {
			bean = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				field.setAccessible(true);
				String value = request.getParameter(field.getName());
				if (value != null) {
					if (field.getType().isAssignableFrom(int.class)
							|| field.getType().isAssignableFrom(Integer.class)) {
						field.setInt(bean, Integer.valueOf(value));
					} else if (field.getType().isAssignableFrom(byte.class)
							|| field.getType().isAssignableFrom(Byte.class)) {
						field.setByte(bean, Byte.valueOf(value));
					} else if (field.getType().isAssignableFrom(short.class)
							|| field.getType().isAssignableFrom(Short.class)) {
						field.setShort(bean, Short.valueOf(value));
					} else if (field.getType().isAssignableFrom(long.class)
							|| field.getType().isAssignableFrom(Long.class)) {
						field.setLong(bean, Long.valueOf(value));
					} else if (field.getType().isAssignableFrom(float.class)
							|| field.getType().isAssignableFrom(Float.class)) {
						field.setFloat(bean, Float.valueOf(value));
					} else if (field.getType().isAssignableFrom(double.class)
							|| field.getType().isAssignableFrom(Double.class)) {
						field.setDouble(bean, Double.valueOf(value));
					} else if (field.getType().isAssignableFrom(String[].class)) {
						String[] values = request.getParameterValues(field.getName());
						field.set(bean, values);
					} else if (field.getType().isAssignableFrom(String.class)) {
						field.set(bean, value);
					}else if (field.getType().isAssignableFrom(Date.class)) {
						try {
							Date date = dateFormat.parse(value);
							field.set(bean, date);
						} catch (Exception e) {
							field.set(bean, null);
						}
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			bean = null;
		}
		return bean;
	}
}
