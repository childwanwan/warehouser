package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.InstoreItems;

import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InstoreItemsService
 * Author:   Childwanwan
 * Date:     2019/4/6 20:07
 * Description: 入库单中间表server层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public interface InstoreItemsService {
	public List<InstoreItems> getInstoresGoodsByInstoresId(InstoreItems instoreItems);
}
