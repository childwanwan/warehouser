package com.ecut.warehouse.warehouse.domain;

import net.sf.json.JSONObject;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: ReturnJsonData
 * Author:   Childwanwan
 * Date:     2019/3/18 8:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class ReturnJsonData {

	public static String OK = "OK";
	public static String PARA_ERROR = "PARA_ERROR";
	public static String DATA_ERROR = "DATA_ERROR";
	public static String SYS_ERROR = "SYS_ERROR";

	public static JSONObject returnJsonFunction(String returnJsonCode) {
		JSONObject returnJson = new JSONObject();
		if (null != returnJsonCode && !"".equals(returnJsonCode)) {
			switch (returnJsonCode) {
				//正常情况
				case "OK": {
					returnJson.put("retCode", 1);
					returnJson.put("retMsg", "数据获取成功");
					break;
				}

				//参数异常
				case "PARA_ERROR": {
					returnJson.put("retCode", -1);
					returnJson.put("retMsg", "数据参数异常");
					break;
				}

				//数据获取为空的情况
				case "DATA_ERROR": {
					returnJson.put("retCode", -2);
					returnJson.put("retMsg", "数据获取失败");
					break;
				}

				//系统出异常的情况:
				case "SYS_ERROR": {
					returnJson.put("retCode", -3);
					returnJson.put("retMsg", "后台系统异常!");
					break;
				}
			}
			if (null != returnJson && !"".equals(returnJson)) {
				return returnJson;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
