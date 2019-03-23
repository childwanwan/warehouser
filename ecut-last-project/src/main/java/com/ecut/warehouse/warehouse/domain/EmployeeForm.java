package com.ecut.warehouse.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: EmployeeForm
 * Author:   Childwanwan
 * Date:     2019/3/17 23:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
public class EmployeeForm {

	/**
	 * id
	 */
	private String id;

	/**
	 * 职员名称
	 */
	private String employeeName;


	/**
	 * 手机号码
	 */
	private String telephone;

	/**
	 * 地址
	 */
	private String addr;

	/**
	 * 状态
	 */
	private Integer status;

}
