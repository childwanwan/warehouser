package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.InstoreItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InstoreItemsDao
 * Author:   Childwanwan
 * Date:     2019/4/6 20:09
 * Description: 入库单中间表Dao层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Mapper
public interface InstoreItemsDao {
	public List<InstoreItems> getInstoresGoodsByInstoresId(InstoreItems instoreItems);
}
