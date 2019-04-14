package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.Employee;
import com.ecut.warehouse.warehouse.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: GoodsDao
 * Author:   Childwanwan
 * Date:     2019/3/23 13:23
 * Description: 物品dao
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Mapper
public interface GoodsDao {
	public List<Goods> queryGoods();
	public List<Goods> getGoodsExceptError();
	public int addGoods(Goods goods);
	public int updateGoods(Goods goods);
	public Goods getGoodsById(Goods goods);
	public List<Goods> getGoodsByGoodsCode(Goods goods);
	public List<Goods> getGoodsBygoodsName(Goods goods);

	public List<Goods> getGoodsByCondition(Goods goods);
}
