package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.PendingExpensesPojo;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao empDao = new EmployeeDaoImpl();

	@Override
	public EmployeePojo fetchEmployee(int empId) {
		return empDao.fetchEmployee(empId);
	}

	@Override
	public List<ExpensePojo> viewPending(int empId) {
		return empDao.viewPending(empId);
	}

	@Override
	public List<ExpensePojo> viewResolved(int empId) {
		return empDao.viewResolved(empId);
	}

	@Override
	public PendingExpensesPojo submitRequest(PendingExpensesPojo pendPojo) {
		return empDao.submitRequest(pendPojo);
	}

	@Override
	public EmployeePojo updateInfo(EmployeePojo empPojo) {
		return empDao.updateInfo(empPojo);
	}

}
