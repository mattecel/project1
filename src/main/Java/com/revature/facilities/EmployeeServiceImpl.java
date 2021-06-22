package com.revature.facilities;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.repos.EmployeeDAO;
import com.revature.repos.EmployeeRepo;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRepo empDao = new EmployeeDAO();

	@Override
	public Employee getEmployee(String user, String pass) {
		return empDao.getEmployee(user, pass);
	}

	@Override
	public Employee getEmployee(Integer id) {
		return empDao.getEmployee(id);
	}

	@Override
	public Employee updateEmployee(Employee em) {
		empDao.updateEmployee(em);
		return empDao.getEmployee(em.getEmployeeId());
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empDao.getAllEmployees();
	}

}
