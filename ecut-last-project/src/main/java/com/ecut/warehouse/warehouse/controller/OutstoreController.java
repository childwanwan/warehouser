package com.ecut.warehouse.warehouse.controller;

import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Outstore;
import com.ecut.warehouse.warehouse.entity.OutstoreItems;
import com.ecut.warehouse.warehouse.service.GoodsService;
import com.ecut.warehouse.warehouse.service.OutstoreItemsService;
import com.ecut.warehouse.warehouse.service.OutstoreService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: InstoreController
 * Author:   wanpp
 * Date:     2019/3/28 14:40
 * Description:
 * History:
 */
@RestController
public class OutstoreController {

	@Autowired
	private OutstoreService outstoreService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private OutstoreItemsService outstoreItemsService;

	/*
	 * @Author: wanpp
	 * @Description:添加出库单
	 * @param: [goods]
	 * @Return: ResponseEntity<JSONObject>
	 * @date: 2019/3/28  15:38
	 */
	@Transactional
	@RequestMapping(value = "/outstore/addOutstore", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> addOutstore(@RequestBody JSONObject jsonObject) {
		//定义返回的json
		JSONObject returnJson = new JSONObject();

		if (outstoreService.outstoreLogic(jsonObject)){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
		}else {
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.PARA_ERROR);
		}

		//获取参数，及将参数封装成对象
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}

	/*
	 * @Author: wanpp
	 * @Description:获取出库单
	 * @param: []
	 * @Return: org.springframework.http.ResponseEntity<net.sf.json.JSONObject>
	 * @date: 2019/3/29  8:41
	 */
	@RequestMapping(value = "/outstore/getOutstores", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> getOutstores() {

		//定义返回的json
		JSONObject returnJson = new JSONObject();

		List<Outstore> listOutstore = new ArrayList<>();

		listOutstore = outstoreService.getOutstores();

		if (null!=listOutstore&&!"".equals(listOutstore)){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
			returnJson.put("data",listOutstore);
		}else {
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
		}

		//获取参数，及将参数封装成对象
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}


	/*
	 * @Author: wanpp
	 * @Description:获取出库单
	 * @param: []
	 * @Return: org.springframework.http.ResponseEntity<net.sf.json.JSONObject>
	 * @date: 2019/3/29  8:41
	 */
	@RequestMapping(value = "/outstore/getOutstoresById", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> getOutstoresById(@RequestBody JSONObject jsonObject) {
		String id = jsonObject.get("id").toString();

		//定义返回的json
		JSONObject returnJson = new JSONObject();

		Outstore paraOutstore = new Outstore();
		paraOutstore.setId(id);

		Outstore resultOutstore = new Outstore();

		try {
			resultOutstore = outstoreService.getOutstoresById(paraOutstore);
		}catch (Exception e){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
		}

		if (null!=resultOutstore&&!"".equals(resultOutstore)){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
			returnJson.put("data",resultOutstore);
		}else {
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
		}

		//获取参数，及将参数封装成对象
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}

	/*
	 * @Author: wanpp
	 * @Description:删除出库单
	 * @param: [id]
	 * @Return: org.springframework.http.ResponseEntity<net.sf.json.JSONObject>
	 * @date: 2019/3/29  10:17
	 */
	@RequestMapping(value = "/outstore/deleteOutstoresById", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> deleteOutstoresById(@RequestBody JSONObject jsonObject) {
		String id = jsonObject.get("outstoreId").toString();
		//定义返回的json
		JSONObject returnJson = new JSONObject();

		Outstore paraOutstore = new Outstore();
		paraOutstore.setId(id);
		paraOutstore.setStatus(9999);


		int resultInfect = 0;

		try {
			resultInfect = outstoreService.deleteOutstoresById(paraOutstore);
		}catch (Exception e){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
		}

		if (resultInfect > 0){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
		}else {
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
		}

		//获取参数，及将参数封装成对象
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}


	@RequestMapping(value = "/outstore/getOutstoresGoodsByOutstoresId", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> getOutstoresGoodsByOutstoresId(@RequestBody JSONObject jsonObject) {
		String id = jsonObject.get("id").toString();
		//定义返回的json
		JSONObject returnJson = new JSONObject();

		OutstoreItems paraOutstoreItems = new OutstoreItems();
		paraOutstoreItems.setOutstoreId(id);


		List<OutstoreItems> returnList = new ArrayList<>();

		try {
			returnList = outstoreItemsService.getOutstoresGoodsByOutstoresId(paraOutstoreItems);
		}catch (Exception e){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
		}

		if (null!=returnList&&!"".equals(returnList)){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
			returnJson.put("data",returnList);
		}else {
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.DATA_ERROR);
		}

		//获取参数，及将参数封装成对象
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}
}
