package com.ecut.warehouse.warehouse.dao;

import com.ecut.warehouse.warehouse.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: EmployeeDao
 * Author:   Childwanwan
 * Date:     2019/3/17 20:50
 * Description: 职工的dao层接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Mapper
public interface EmployeeDao {
	/*
	* 登入操作
	* */
	public Employee selectEmployeeByUsernamePasswordType(Employee employee);
}
