package com.ecut.warehouse.warehouse.service.impl;

import com.ecut.warehouse.warehouse.dao.EmployeeDao;
import com.ecut.warehouse.warehouse.entity.Employee;
import com.ecut.warehouse.warehouse.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: EmployeeServiceImpl
 * Author:   Childwanwan
 * Date:     2019/3/17 22:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public Employee selectEmployeeByUsernamePasswordType(Employee employee) {
		Employee employeeResult = employeeDao.selectEmployeeByUsernamePasswordType(employee);
		if (null == employeeResult && "".equals(employeeResult)){
			return null;
		}else {
			//登入信息放进redis
			return employeeResult;
		}

	}

	@Override
	public int updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(Employee employee) {
		return employeeDao.getEmployeeById(employee);
	}

	@Override
	@Transactional
	public int addEmployee(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeDao.getEmployees();
	}

	@Override
	public List<Employee> getEmployeeByEmployeeName(Employee employee) {
		return employeeDao.getEmployeeByEmployeeName(employee);
	}

	@Override
	public List<Employee> getEmployeesByStatus(Employee employee) {
		return employeeDao.getEmployeesByStatus(employee);
	}
}
