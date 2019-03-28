package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.Outstore;
import net.sf.json.JSONObject;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InstoreService
 * Author:   Childwanwan
 * Date:     2019/3/28 14:38
 * Description: 入库的Service接口层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public interface OutstoreService {
	public int addOutstore(Outstore outstore);
	public boolean outstoreLogic(JSONObject jsonObject);
}
