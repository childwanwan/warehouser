package com.ecut.warehouse.filter;

import com.ecut.warehouse.warehouse.entity.Employee;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: LoginFilter
 * Author:   Childwanwan
 * Date:     2019/3/17 17:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Configuration
public class LoginFilter implements Filter {

	Logger log = LoggerFactory.getLogger(LoginFilter.class);

	//允许未登入就能访问的url片段
	@Value("${filter.exclusion.url}")
	private String exclusionUrl;// = "/valid/|/do|/logManager/|/screen/|/overThresholdInfo|/exceptionalCallInfo|/delay|/apiCount";


	/**/@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;



		String uri = request.getRequestURI();

		//是否需要过滤
		boolean needFilter = shouldFilter(uri);

		if(!needFilter){
			filterChain.doFilter(request, response);
		}else {
			/*for (int i=0,j=request.getCookies().length;i<j;i++){
				request.getCookies()[i].getName();
			}*/
			Cookie cookies= request.getCookies()[0];
			//System.out.println(cookies.getName());
			if (cookies != null){
				if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
					return;
				}
				//null == redisConn.getDataFromRedis(cookies.getName()) || "".equals(redisConn.getDataFromRedis(cookies.getName()))
				if (null == request.getSession().getAttribute("user") || "".equals(request.getSession().getAttribute("user"))) {
					log.debug("仓库管理门户请求链接" + request.getServletPath() + "被拦截，session没有用户信息");
					response.setStatus(200);
					response.setContentType("application/json;charset=utf-8");
					response.getWriter().write("{\"retCode\":\"000000\",\"retMsg\":\"请登录后操作\"}");
					return;
				}
				filterChain.doFilter(request, response);
			}else{
				log.debug("仓库管理门户请求链接" + request.getServletPath() + "被拦截，没有session");
				response.setStatus(200);
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write("{\"retCode\":\"000000\",\"retMsg\":\"请登录后操作\"}");
				return;
			}
		}
	}

	@Override
	public void destroy() {

	}

	public boolean shouldFilter(String requestURI) {
		//String str = requestURI.split("\\/")[1];
		String[] urls = exclusionUrl.split("\\|");
		if (urls.length > 0) {
			List<String> list = Arrays.asList(urls);
			for (String filter : list){
				if (requestURI.contains(filter)){
					return false;
				}
			}
		}
		return true;
	}
}
