package com.ecut.warehouse.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2019-03-17
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor                       //无参构造
@AllArgsConstructor
@Table(name="tbl_employee")
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;


	/**
	 * 职员名称
	 */
	private String employeeName;

	/**
	 * 密码
	 */
	private String password;

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


	protected Serializable pkVal() {
		return null;
	}

}



