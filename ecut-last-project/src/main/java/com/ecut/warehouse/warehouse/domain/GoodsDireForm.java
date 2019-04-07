package com.ecut.warehouse.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.sf.json.JSONArray;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: GoodsDireForm
 * Author:   wanpp
 * Date:     2019/4/7 13:54
 * Description:
 * History:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
public class GoodsDireForm {
	private String id;
	//商品编码
	private String goodsCode;
	//商品名称
	private String goodsName;
	//规格集
	private JSONArray specificationItems;
	//自定义规格
	private String customAttributeItems;
	//
	private Integer status;
	//备注
	private String comment;
	//商品类型
	private String goodsType;
	//商品数量
	private Integer goodsNum;
}
