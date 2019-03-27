package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.Provider;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ProviderDao
 * Author:   Childwanwan
 * Date:     2019/3/23 16:15
 * Description: 供应商dao
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Mapper
public interface ProviderDao {
	public List<Provider> queryProvider();
	public List<Provider> getProviderByCondition(Provider provider);
	public int addProvider(Provider provider);
	public int updateProvider(Provider provider);
}
