package com.revature.facilities;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Story;

public interface EmployeeService {
	Employee getEmployee(String user, String pass);
	Employee getEmployee(Integer id);
	
	void addEmployeeToStory(Story st);
	Employee updateEmployee(Employee em);
	List<Employee> getAllEmployees();
}
