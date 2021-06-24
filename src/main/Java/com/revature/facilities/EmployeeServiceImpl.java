package com.revature.facilities;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Story;
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

	@Override
	public void addEmployeeToStory(Story st) {
		Integer mattsfavoriteId = st.getStoryId();
		Employee helen = getEmployee(3);
		Employee taylor = getEmployee(4);
		Employee lydia = getEmployee(7);			
		
		helen.setStory3(st);
		taylor.setStory3(st);
		lydia.setStory3(st);
		
		updateEmployee(helen);
		updateEmployee(taylor);
		updateEmployee(lydia);
	}

}
