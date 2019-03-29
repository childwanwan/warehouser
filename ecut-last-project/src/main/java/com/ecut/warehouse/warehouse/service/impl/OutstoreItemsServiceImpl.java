package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.OutstoreItemsDao;
import com.ecut.warehouse.warehouse.entity.OutstoreItems;
import com.ecut.warehouse.warehouse.service.OutstoreItemsService;
import com.ecut.warehouse.warehouse.service.OutstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: OutstoreItemsServiceImpl
 * Author:   wanpp
 * Date:     2019/3/28 18:44
 * Description:
 * History:
 */
@Service
public class OutstoreItemsServiceImpl implements OutstoreItemsService {
	@Autowired
	private OutstoreItemsDao outstoreItemsDao;


	@Override
	public int addOutstoreItem(OutstoreItems outstoreItems) {
		return outstoreItemsDao.addOutstoreItem(outstoreItems);
	}

	@Override
	public List<OutstoreItems> getOutstoresGoodsByOutstoresId(OutstoreItems outstoreItems) {
		return outstoreItemsDao.getOutstoresGoodsByOutstoresId(outstoreItems);
	}
}
