package com.ecut.warehouse.warehouse.domain;

import com.ecut.warehouse.warehouse.entity.Employee;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: EmployeeToEmployeeForm
 * Author:   Childwanwan
 * Date:     2019/3/17 23:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class DoChangFunction {
	public static EmployeeForm employeeChangeToEmployeeForm(Employee employee){
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setId(employee.getId())
				.setAddr(employee.getAddr())
				.setEmployeeName(employee.getEmployeeName())
				.setTelephone(employee.getTelephone())
				.setStatus(employee.getStatus());
		return employeeForm;
	}
}
