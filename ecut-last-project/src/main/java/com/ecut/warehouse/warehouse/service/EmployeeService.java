package com.ecut.warehouse.warehouse.service;

import com.ecut.warehouse.warehouse.entity.Employee;
import com.ecut.warehouse.warehouse.entity.Goods;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: EmployeeService
 * Author:   Childwanwan
 * Date:     2019/3/17 22:43
 * Description: 职工服务层接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public interface EmployeeService {
	public Employee selectEmployeeByUsernamePasswordType(Employee employee);
	public int updateEmployee(Employee employee);
	public Employee getEmployeeById(Employee employee);
	public int addEmployee(Employee employee);
}
