package com.ecut.warehouse.warehouse.controller;

import com.ecut.warehouse.warehouse.domain.ReturnJsonData;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.service.GoodsService;
import com.ecut.warehouse.warehouse.service.impl.GoodsServiceImpl;
import com.ecut.warehouse.warehouse.utils.CommonUtils;
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
	 * @Description:查询所有的goods，包括报损状态中的
	 * @Para:* @param 登入信息
	 * @data:2019/3/17  22:50
	 */
	@RequestMapping(value = "/goods/getGoods", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> getGoods() {
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

	/*
	 * @Author:Childwanwan
	 * @Description:查询所有的goods，包括报损状态中的
	 * @Para:* @param 登入信息
	 * @data:2019/3/17  22:50
	 */
	@RequestMapping(value = "/goods/getGoodsExceptError", method = RequestMethod.GET)
	public ResponseEntity<JSONObject> getGoodsExceptError() {
		//定义返回的json
		JSONObject returnJson = new JSONObject();
		List<Goods> list = new ArrayList<>();
		try{
			list = goodsService.getGoodsExceptError();
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
	 * @Description:添加供应商
	 * @Para:* @param
	 * @data:2019/3/17  22:50
	 */
	@RequestMapping(value = "/goods/addGoods", method = RequestMethod.POST)
	public ResponseEntity<JSONObject> addGoods(@RequestBody Goods goods) {
		//定义返回的json
		JSONObject returnJson = new JSONObject();
		if (null==goods.getId()||"".equals(goods.getId())){
			goods.setId(CommonUtils.getUUID());
		}
		if (null==goods.getGoodsNum()||"".equals(goods.getGoodsNum())){
			goods.setGoodsNum(1);
		}
		if (null==goods.getStatus()){
			goods.setStatus(1);
		}
		System.out.println(goods);
		int i = 0;
		try{
			i = goodsService.addGoods(goods);
		}catch (Exception e){
			returnJson= ReturnJsonData.returnJsonFunction(ReturnJsonData.SYS_ERROR);
		}
		if (i>0){
			returnJson = ReturnJsonData.returnJsonFunction(ReturnJsonData.OK);
		}
		//获取参数，及将参数封装成对象
		return new ResponseEntity<>(returnJson, HttpStatus.ACCEPTED);
	}
}
