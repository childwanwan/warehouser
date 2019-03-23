package com.ecut.warehouse.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: GlobalCorsConfig
 * Author:   Childwanwan
 * Date:     2019/2/27 19:50
 * Description:	解决跨域问题的配置类,最好能去掉，把这个放前台
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

@Configuration
public class GlobalCorsConfig {
	@Bean
	public CorsFilter corsFilter() {
		//1.添加CORS配置信息
		CorsConfiguration config = new CorsConfiguration();
		//1) 允许的域,不要写*，否则cookie就无法使用了http://localhost:8080
		config.addAllowedOrigin("*");
		//2) 是否发送Cookie信息
		config.setAllowCredentials(true);

		//3) 允许的请求方式
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		// 4）允许的头信息
		config.addAllowedHeader("*");

		//2.添加映射路径，我们拦截一切请求
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		configSource.registerCorsConfiguration("/**", config);

		//3.返回新的CorsFilter.
		return new CorsFilter(configSource);
	}
}

