package com.revature.facilities;

import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeService {
	Employee getEmployee(String user, String pass);
	Employee getEmployee(Integer id);
	
	Employee updateEmployee(Employee em);
	List<Employee> getAllEmployees();
}
