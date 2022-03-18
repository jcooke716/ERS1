package dao;

import java.util.List;

import exceptions.EmployeeNotFound;
import exceptions.SystemException;
import pojo.AdminPojo;
import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.FinalExpensesPojo;
import pojo.PendingExpensesPojo;

public interface AdminDao {
	
	EmployeePojo fetchEmployee(int empId) throws SystemException;
	
	AdminPojo fetchAdmin(int adminId) throws SystemException;
	
	List<EmployeePojo> fetchAllEmployees() throws SystemException;
	
	List<ExpensePojo> fetchAllExpenses(int empId) throws SystemException;
	
	List<ExpensePojo> fetchAllPendingExpenses() throws SystemException;
	
	List<ExpensePojo> fetchAllFinalExpenses() throws SystemException;
	
	PendingExpensesPojo fetchPendingExpense(int expenseId) throws SystemException;
	
	FinalExpensesPojo approveExpense(PendingExpensesPojo pendPojo) throws SystemException;
	
	FinalExpensesPojo denyExpense(PendingExpensesPojo pendPojo) throws SystemException;
	
	ExpensePojo deletePendingExpense(int expenseId) throws SystemException;
	
	ExpensePojo fetchNewestExpense() throws SystemException;
	
	AdminPojo loginAdmin(AdminPojo pojoIn) throws SystemException;
	
	EmployeePojo loginEmployee(EmployeePojo pojoIn) throws SystemException;
	
	int pendingAmount() throws SystemException;
	
	default void exitApplication() {
		DBUtil.closeConnection();
	}
	

}
