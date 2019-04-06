package com.ecut.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: InstoreItems
 * Author:   wanpp
 * Date:     2019/4/6 20:04
 * Description:
 * History:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
@Table(name="tbl_instore_items")
public class InstoreItems implements Serializable {
	private String id;
	private String instoreId;
	private String goodsId;
	private Integer buyNum;
	private String comment;
	private Integer status;
}
