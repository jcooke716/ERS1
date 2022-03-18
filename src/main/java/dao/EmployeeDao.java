package dao;

import java.util.List;

import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.PendingExpensesPojo;

public interface EmployeeDao {
	
	EmployeePojo fetchEmployee(int empId);
	
	List<ExpensePojo> viewPending(int empId);
	
	List<ExpensePojo> viewResolved(int empId);
	
	PendingExpensesPojo submitRequest(PendingExpensesPojo pendPojo);
	
	EmployeePojo updateInfo(EmployeePojo empPojo);
	

}
