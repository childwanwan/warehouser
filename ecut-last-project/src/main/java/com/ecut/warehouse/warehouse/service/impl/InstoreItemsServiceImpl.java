package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.InstoreDao;
import com.ecut.warehouse.warehouse.dao.InstoreItemsDao;
import com.ecut.warehouse.warehouse.entity.InstoreItems;
import com.ecut.warehouse.warehouse.service.InstoreItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: InstoreItemsServiceImpl
 * Author:   wanpp
 * Date:     2019/4/6 20:08
 * Description:
 * History:
 */
@Service
public class InstoreItemsServiceImpl implements InstoreItemsService {
	@Autowired
	private InstoreItemsDao instoreItemsDao;
	@Override
	public List<InstoreItems> getInstoresGoodsByInstoresId(InstoreItems instoreItems) {
		return instoreItemsDao.getInstoresGoodsByInstoresId(instoreItems);
	}
}
