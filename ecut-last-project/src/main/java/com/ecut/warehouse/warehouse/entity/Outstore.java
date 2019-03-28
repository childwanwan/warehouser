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
@Table(name="tbl_outstore")
public class Outstore {
	private String id;
	//创建时间，意义暂时不大
	private Date createTime;
	//出库日期
	private Date outstoreTime;
	//数量合计
	private Integer totalNum;
	//转态
	private Integer status;
	//提供出库的人
	private String providerId;
	//接收出库的人
	private String reserveId;

}
