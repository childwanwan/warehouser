package com.ecut.warehouse.warehouse.controller;

import com.ecut.warehouse.warehouse.domain.DoChangFunction;
import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Employee;
import com.ecut.warehouse.warehouse.service.EmployeeService;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: EmployeeController
 * Author:   Childwanwan
 * Date:     2019/3/17 19:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CommonUtils commonUtils;

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> test(HttpServletResponse response) {
		System.out.println("test1");
		JSONObject returnJson = new JSONObject();
		returnJson.put("hello", "world");
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}


	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> test2(HttpServletResponse response) {
		System.out.println("test2");
		JSONObject returnJson = new JSONObject();
		returnJson.put("hello", "world");
		String cookieName = "test2";
		String cookieValue = commonUtils.getUUID();
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(3600);
		cookie.setPath("/");
		response.addCookie(cookie);


		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}


	/*
	 * @Author:Childwanwan
	 * @Description:登入controller
	 * @Para:* @param 登入信息
	 * @data:2019/3/17  22:50
	 */
	@RequestMapping(value = "/employee/login", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> employeeLogin(HttpServletResponse response, HttpServletRequest request, @RequestBody JSONObject jsonObject) {
		//定义返回的json
		JSONObject returnJson = new JSONObject();

		//获取参数，及将参数封装成对象
		Employee employeePara = new Employee();
		if (null != jsonObject.get("employeeName") && !"".equals(jsonObject.get("employeeName").toString())
				&& null != jsonObject.get("password") && !"".equals(jsonObject.get("password").toString())
				&& null != jsonObject.get("status") && !"".equals(jsonObject.get("status").toString())) {
			employeePara.setEmployeeName(jsonObject.get("employeeName").toString())
					.setPassword(jsonObject.get("password").toString())
					.setStatus(Integer.parseInt(jsonObject.get("status").toString()));
		} else {
			//throw new RuntimeException("参数错误,请确认参数！");
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.PARA_ERROR);
		}
		try {
			Employee employeeResult = employeeService.selectEmployeeByUsernamePasswordType(employeePara);
			if (null != employeeResult && !"".equals(employeeResult)) {

				request.getSession().setAttribute("user", employeeResult);

				//返回的数据
				returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
				//调用domain里的函数，将employee转换
				//System.out.println(returnJson);
				returnJson.put("data", DoChangFunction.employeeChangeToEmployeeForm(employeeResult));
			} else {
				returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
			}
		} catch (Exception e) {
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
			throw new RuntimeException("登入出问题了,请联系后台处理人员!" + e);
		}
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}
}
