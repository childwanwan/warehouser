package com.ecut.warehouse.warehouse.controller;

import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.service.GoodsService;
import com.ecut.warehouse.warehouse.service.impl.GoodsServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: WarehouseController
 * Author:   Childwanwan
 * Date:     2019/3/23 10:46
 * Description:
 * History:
 */
@RestController
public class GoodsController {
	@Autowired
	private GoodsService goodsService;


	/*
	 * @Author:Childwanwan
	 * @Description:登入controller
	 * @Para:* @param 登入信息
	 * @data:2019/3/17  22:50
	 */
	@RequestMapping(value = "/warehouse/getGoods", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> getWarehouse() {
		//定义返回的json
		JSONObject returnJson = new JSONObject();
		List<Goods> list = new ArrayList<>();

		try{
			list = goodsService.queryGoods();
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
