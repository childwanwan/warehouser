package com.ecut.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: Goods
 * Author:   Childwanwan
 * Date:     2019/3/23 13:11
 * Description:
 * History:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
@Table(name="tbl_goods")
public class Goods {
	private String id;
	//商品编码
	private String goodsCode;
	//商品名称
	private String goodsName;
	//规格集
	private String specificationItems;
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
