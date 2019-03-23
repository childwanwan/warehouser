package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.Goods;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: GoodsService
 * Author:   Childwanwan
 * Date:     2019/3/23 10:55
 * Description:
 * History:
 */

public interface GoodsService {
	public List<Goods> queryGoods();

	public List<Goods> getGoodsExceptError();
}
