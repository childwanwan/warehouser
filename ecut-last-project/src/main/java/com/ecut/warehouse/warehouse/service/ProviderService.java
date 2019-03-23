package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.Provider;

import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ProviderService
 * Author:   Childwanwan
 * Date:     2019/3/23 16:05
 * Description: 供应商service接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public interface ProviderService {
	public List<Provider> queryProvider();
	public List<Provider> getProviderByCondition(Provider provider);
}
