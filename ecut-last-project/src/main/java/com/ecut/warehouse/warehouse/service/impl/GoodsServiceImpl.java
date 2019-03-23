package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.GoodsDao;
import com.ecut.warehouse.warehouse.entity.Goods;
import com.ecut.warehouse.warehouse.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: GoodsServiceImpl
 * Author:   Childwanwan
 * Date:     2019/3/23 13:08
 * Description:
 * History:
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	@Override
	public List<Goods> queryGoods() {
		return goodsDao.queryGoods();
	}

	@Override
	public List<Goods> getGoodsExceptError() {
		return goodsDao.getGoodsExceptError();
	}

	@Override
	@Transactional
	public int addGoods(Goods goods) {
		return goodsDao.addGoods(goods);
	}
}
