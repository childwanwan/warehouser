package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.ProviderDao;
import com.ecut.warehouse.warehouse.entity.Provider;
import com.ecut.warehouse.warehouse.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: ProviderServiceImpl
 * Author:   Childwanwan
 * Date:     2019/3/23 16:06
 * Description:
 * History:
 */
@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderDao providerDao;
	@Override
	public List<Provider> queryProvider() {
		return providerDao.queryProvider();
	}

	@Override
	public List<Provider> getProviderByCondition(Provider provider) {
		return providerDao.getProviderByCondition(provider);
	}
}
