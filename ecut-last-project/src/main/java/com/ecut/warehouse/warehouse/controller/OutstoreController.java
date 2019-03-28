package com.ecut.warehouse.warehouse.controller;

import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.entity.Outstore;
import com.ecut.warehouse.warehouse.entity.OutstoreItems;
import com.ecut.warehouse.warehouse.service.GoodsService;
import com.ecut.warehouse.warehouse.service.OutstoreItemsService;
import com.ecut.warehouse.warehouse.service.OutstoreService;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
}
