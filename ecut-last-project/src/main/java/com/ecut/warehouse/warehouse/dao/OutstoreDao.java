package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.Outstore;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InstoreDao
 * Author:   Childwanwan
 * Date:     2019/3/28 14:37
 * Description: 入库的dao层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Mapper
public interface OutstoreDao {
	public int addOutstore(Outstore outstore);
}
