package com.example.mvc.meal.filters;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filter1LoginCheck
 */
@WebFilter("/*")
public class Filter1LoginCheck implements Filter {

    /**
     * Default constructor.
     */
    public Filter1LoginCheck() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hr = (HttpServletRequest) request;
//		hr.setCharacterEncoding("utf-8");
        HttpSession session = hr.getSession();
        String path = hr.getServletPath();
        int temp = path.lastIndexOf('.');
        if (temp != -1) {
            String suffix = path.substring(temp + 1);
            String addr = path.substring(path.lastIndexOf('/') + 1, temp);
            if (suffix.equals("jsp") && !addr.equals("index")) {
                // 直接访问JSP文件
                hr.setAttribute("msg", "非法访问！");
                hr.setAttribute("href", hr.getContextPath() + "/index.do");
                hr.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
                return;
            } else if (suffix.equals("do")) {
                if (!(addr.equals("index") || addr.equals("show_detail") || addr.equals("login")
                        || addr.equals("register") || addr.equals("check_username"))) {
                    Map user = (Map)session.getAttribute("user");
                    if (user == null) {
                        hr.setAttribute("msg", "请先登录！");
                        hr.setAttribute("href", hr.getContextPath() + "/index.do");
                        hr.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
                        return;
                    }else if("0".equals(user.get("ident"))&&path.contains("/admin/")) {
                        hr.setAttribute("msg", "普通用户无法访问管理员权限");
                        hr.setAttribute("href", hr.getContextPath() + "/index.do");
                        hr.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
                        return;
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
