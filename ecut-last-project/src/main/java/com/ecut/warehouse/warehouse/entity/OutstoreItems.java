package com.ecut.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: outstoreItems
 * Author:   wanpp
 * Date:     2019/3/28 18:37
 * Description:
 * History:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
@Table(name="tbl_outstore_items")
public class OutstoreItems {
	private String id;
	//出库id
	private String outstoreId;
	//商品id
	private String goodsId;
	//状态，默认0，删除9999
	private Integer status;
	//出库数量
	private Integer sellNum;
	//备注
	private String comment;

}
