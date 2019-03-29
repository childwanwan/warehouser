package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.OutstoreDao;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: InstoreServiceImpl
 * Author:   wanpp
 * Date:     2019/3/28 14:39
 * Description:
 * History:
 */
@Service
public class OutstoreServiceImpl implements OutstoreService {
	@Autowired
	private OutstoreDao outstoreDao;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OutstoreItemsService outstoreItemsService;

	@Override
	public int addOutstore(Outstore outstore) {
		return outstoreDao.addOutstore(outstore);
	}

	@Override
	@Transactional
	public boolean outstoreLogic(JSONObject jsonObject) {
		//定义返回值
		Boolean returnBoolean = true;
		//更新goods表的数量
		int updateGoodsNum = 0;

		//新增出库单返回的影响行数
		int outstoreNum = 0;

		//新增出库中间表影响行数
		int outstoreItemNum = 0;

		//定义goodsIds
		List<String> goodsIds = new ArrayList<>();

		//获取json对象
		//1获取出库物品的信息，包含商品码和数量还有描述信息
		JSONArray goodsList = JSONArray.fromObject(jsonObject.get("goodsList"));
		//System.out.println(goodsList.size());
		//2、更新仓库
		if (null != goodsList && !"".equals(goodsList)) {
			//定义商品实体
			Goods updateGoods = new Goods();
			Goods oldGoods = new Goods();
			Goods paramGoods = new Goods();
			try {
				for (int i = 0; i < goodsList.size(); i++) {
					//应该是有一个goodsCode，还有一个是goodsNum
					//updateGoods = (Goods) goodsList.get(i);
					updateGoods.setGoodsCode(JSONObject.fromObject(goodsList.get(i)).get("goodsCode").toString());
					updateGoods.setGoodsNum(Integer.parseInt(JSONObject.fromObject(goodsList.get(i)).get("goodsNum").toString()));
					//updateGoods.setComment(JSONObject.fromObject(goodsList.get(i)).get("comment").toString());
					//定义的goods是用来当做参数查询原表里的goods信息的
					paramGoods.setGoodsCode(updateGoods.getGoodsCode());
					//查询出相关的商品信息
					oldGoods = goodsService.getGoodsByGoodsCode(paramGoods);

					if (null != oldGoods && !"".equals(oldGoods)) {
						goodsIds.add(oldGoods.getId());
						//重新设置值
						updateGoods.setGoodsNum(oldGoods.getGoodsNum() - updateGoods.getGoodsNum());
						updateGoods.setId(oldGoods.getId());
					} else {
						returnBoolean = false;
					}
					updateGoodsNum = updateGoodsNum + goodsService.updateGoods(updateGoods);
				}
			} catch (Exception e) {
				throw new RuntimeException("系统内部异常！");
			}
		}

		//3、把数据存入入库表
		Outstore outstore = new Outstore();
		outstore.setTotalNum(Integer.parseInt(jsonObject.get("totalNum").toString()));
		outstore.setProviderId(jsonObject.get("providerId").toString());
		outstore.setReserveId(jsonObject.get("reserveId").toString());
		//这个uuid后面还需要用到
		String uuid = CommonUtils.getUUID();
		outstore.setId(uuid);
		outstore.setOutstoreTime(new Date());
		outstore.setCreateTime(new Date());
		outstore.setStatus(0);
		if (updateGoodsNum == goodsList.size()) {
			try {
				outstoreNum = this.addOutstore(outstore);
			} catch (Exception e) {
				throw new RuntimeException("系统内部异常！");
			}
		}
		if (outstoreNum <= 0) {
			returnBoolean = false;
		}
		//把数据存入出库item表中
		//定义出库中间表对象
		OutstoreItems outstoreItems = new OutstoreItems();

		outstoreItems.setOutstoreId(uuid);
		outstoreItems.setStatus(0);
		Goods goods = new Goods();
		try {
			for (int i = 0; i < goodsList.size(); i++) {
				//Goods goods = (Goods) goodsList.get(i);
				goods.setGoodsNum(Integer.parseInt(JSONObject.fromObject(goodsList.get(i)).get("goodsNum").toString()));
				goods.setComment(JSONObject.fromObject(goodsList.get(i)).get("comment").toString());
				outstoreItems.setId(CommonUtils.getUUID());
				outstoreItems.setGoodsId(goodsIds.get(i));
				outstoreItems.setSellNum(goods.getGoodsNum());
				outstoreItems.setComment(goods.getComment());
				//存
				outstoreItemNum = outstoreItemNum + outstoreItemsService.addOutstoreItem(outstoreItems);
			}
		} catch (Exception e) {
			throw new RuntimeException("系统内部异常！");
		}
		if (updateGoodsNum == goodsList.size() && outstoreItemNum == goodsList.size() && outstoreNum > 0) {
			if (returnBoolean){
				returnBoolean = true;
			}else {
				returnBoolean = false;
			}
		} else {
			returnBoolean = false;
		}
		return returnBoolean;
	}

	@Override
	public List<Outstore> getOutstores() {
		return outstoreDao.getOutstores();
	}

	@Override
	public Outstore getOutstoresById(Outstore outstore) {
		return outstoreDao.getOutstoresById(outstore);
	}

	@Override
	public int deleteOutstoresById(Outstore outstore) {
		return outstoreDao.deleteOutstoresById(outstore);
	}
}
