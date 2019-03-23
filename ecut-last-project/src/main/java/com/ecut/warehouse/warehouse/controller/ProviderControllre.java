package com.ecut.warehouse.warehouse.controller;

import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.entity.Provider;
import com.ecut.warehouse.warehouse.service.ProviderService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: ProviderControllre
 * Author:   Childwanwan
 * Date:     2019/3/23 16:04
 * Description:
 * History:
 */
@RestController
public class ProviderControllre {
	@Autowired
	private ProviderService providerService;

	/*
	 * @Author:Childwanwan
	 * @Description:查询所有的供应商
	 * @Para:* @param
	 * @data:2019/3/17  22:50
	 */
	@RequestMapping(value = "/provider/getProvider", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> getProvider() {
		//定义返回的json
		JSONObject returnJson = new JSONObject();
		List<Provider> list = new ArrayList<>();
		try{
			list = providerService.queryProvider();
		}catch (Exception e){
			returnJson= ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
		}
		if (null != list && list.size()>0){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
			returnJson.put("data",list);
		}
		//获取参数，及将参数封装成对象
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}


	/*
	 * @Author:Childwanwan
	 * @Description:根据条件查询供应商
	 * @Para:* @param
	 * @data:2019/3/17  22:50
	 */
	@RequestMapping(value = "/provider/getProviderByCondition", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> getProviderByCondition(
			@RequestParam(required = false) String id,
			@RequestParam(required = false) String providerType,
			@RequestParam(required = false) String providerName,
			@RequestParam(required = false) String linkman,
			@RequestParam(required = false) String telephone,
			@RequestParam(required = false) String addr,
			@RequestParam(required = false) Integer status) {

		Provider provider = new Provider();
		if (null!=id&&!"".equals(id)){
			provider.setId(id);
		}
		if (null!=providerType&&!"".equals(providerType)){
			provider.setProviderType(providerType);
		}
		if (null!=providerName&&!"".equals(providerName)){
			provider.setProviderName(providerName);
		}
		if (null!=linkman&&!"".equals(linkman)){
			provider.setLinkman(linkman);
		}
		if (null!=telephone&&!"".equals(telephone)){
			provider.setTelephone(telephone);
		}
		if (null!=addr&&!"".equals(addr)){
			provider.setAddr(addr);
		}
		if (null != status && !"".equals(status)){
			provider.setStatus(status);
		}

		//定义返回的json
		JSONObject returnJson = new JSONObject();
		List<Provider> list = new ArrayList<>();
		try{
			list = providerService.getProviderByCondition(provider);
		}catch (Exception e){
			returnJson= ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
		}
		if (null != list && list.size()>0){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
			returnJson.put("data",list);
		}
		//获取参数，及将参数封装成对象
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}
}
