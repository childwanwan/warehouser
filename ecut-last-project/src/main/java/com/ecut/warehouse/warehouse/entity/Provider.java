package com.ecut.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * Copyright (C), 2019-2019, 思特奇
 * FileName: Provider
 * Author:   Childwanwan
 * Date:     2019/3/23 16:01
 * Description:
 * History:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
@Table(name="tbl_provider")
public class Provider {
	private String id;
	//供应商类型
	private String providerType;
	//供应商名称
	private String providerName;
	//联系人
	private String linkman;
	//机号码
	private String telephone;
	//地址
	private String addr;
	//状态0默认，9999已删除
	private Integer status;
}
