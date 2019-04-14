package com.ecut.warehouse.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	// 多个跨域域名设置
	public static final String[] ALLOW_DOMAIN = {"http://localhost:8080",
			"http://120.78.132.185:8881", "http://localhost:8081"};

	Logger log = LoggerFactory.getLogger(LoginFilter.class);

	//允许未登入就能访问的url片段
	@Value("${filter.exclusion.url}")
	private String exclusionUrl;// = "/valid/|/do|/logManager/|/screen/|/overThresholdInfo|/exceptionalCallInfo|/delay|/apiCount";


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//System.out.println("进来啦");

		String originHeader = request.getHeader("Origin");
		if (Arrays.asList(ALLOW_DOMAIN).contains(originHeader)) {
			//通过在响应 header 中设置 ‘*’ 来允许来自所有域的跨域请求访问。
			response.setHeader("Access-Control-Allow-Origin", originHeader);
			//通过对 Credentials 参数的设置，就可以保持跨域 Ajax 时的 Cookie
			//设置了Allow-Credentials，Allow-Origin就不能为*,需要指明具体的url域
			response.setHeader("Access-Control-Allow-Credentials", "true");
			//请求方式
			response.setHeader("Access-Control-Allow-Methods", "*");
			//（预检请求）的返回结果（即 Access-Control-Allow-Methods 和Access-Control-Allow-Headers 提供的信息） 可以被缓存多久
			response.setHeader("Access-Control-Max-Age", "86400");
			//首部字段用于预检请求的响应。其指明了实际请求中允许携带的首部字段
			//res.setHeader("Access-Control-Allow-Headers", "*");
			response.setHeader("Access-Control-Allow-Headers", "Timestamp,Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token,Access-Control-Allow-Headers");
		}

		String uri = request.getRequestURI();

		//是否需要过滤
		boolean needFilter = shouldFilter(uri);
		if(!needFilter){
			filterChain.doFilter(request, response);
		}else {
			//Cookie cookies= request.getCookies()[0];
			String tokenHeader = request.getHeader("token");
			//System.out.println("+++++++++++++++++++++"+tokenHeader+"+++++++++++++++++++++++++++");
			if (tokenHeader != null){
				if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
					return;
			}
				//null == redisConn.getDataFromRedis(cookies.getName()) || "".equals(redisConn.getDataFromRedis(cookies.getName()))
				if (null == request.getSession().getAttribute(tokenHeader) || "".equals(request.getSession().getAttribute(tokenHeader))) {
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
