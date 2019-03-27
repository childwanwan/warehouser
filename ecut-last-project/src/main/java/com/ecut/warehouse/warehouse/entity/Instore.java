package com.ecut.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.util.Date;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: Instore
 * Author:   wanpp
 * Date:     2019/3/27 18:26
 * Description:
 * History:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
@Table(name="tbl_instore")
public class Instore {
	private String id;
	//入库人id
	private String provideId;
	//入库日期
	private Date instoreTime;
	//数量合计
	private Integer totalNum;
	//状态
	private Integer status;
	//接收人id
	private String reserverId;
}
