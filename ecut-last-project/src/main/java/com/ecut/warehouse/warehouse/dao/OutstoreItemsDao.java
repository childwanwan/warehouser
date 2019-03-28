package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.OutstoreItems;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OutstoreItemsDao
 * Author:   Childwanwan
 * Date:     2019/3/28 18:42
 * Description: 出库中间表dao层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Mapper
public interface OutstoreItemsDao {
	public int addOutstoreItem(OutstoreItems outstoreItems);
}
