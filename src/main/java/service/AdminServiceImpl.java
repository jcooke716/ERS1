package service;

import java.util.List;

import dao.AdminDao;
import dao.AdminDaoImpl;
import exceptions.SystemException;
import pojo.AdminPojo;
import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.FinalExpensesPojo;
import pojo.PendingExpensesPojo;

public class AdminServiceImpl implements AdminService {
	
	AdminDao adminDao;
	
	public AdminServiceImpl() {
		adminDao = new AdminDaoImpl();
	}

	@Override
	public EmployeePojo fetchEmployee(int empId) throws SystemException {
		return adminDao.fetchEmployee(empId);
	}

	@Override
	public AdminPojo fetchAdmin(int adminId) throws SystemException {
		return adminDao.fetchAdmin(adminId);
	}

	@Override
	public List<EmployeePojo> fetchAllEmployees() throws SystemException {
		return adminDao.fetchAllEmployees();
	}

	@Override
	public List<ExpensePojo> fetchAllExpenses(int empId) throws SystemException {
		return adminDao.fetchAllExpenses(empId);
	}

	@Override
	public List<ExpensePojo> fetchAllPendingExpenses() throws SystemException {
		return adminDao.fetchAllPendingExpenses();
	}

	@Override
	public List<ExpensePojo> fetchAllFinalExpenses() throws SystemException {
		return adminDao.fetchAllFinalExpenses();
	}

	@Override
	public PendingExpensesPojo fetchPendingExpense(int expenseId) throws SystemException {
		return adminDao.fetchPendingExpense(expenseId);
	}

	@Override
	public FinalExpensesPojo approveExpense(PendingExpensesPojo pendPojo) throws SystemException {
		return adminDao.approveExpense(pendPojo);
	}

	@Override
	public FinalExpensesPojo denyExpense(PendingExpensesPojo pendPojo) throws SystemException {
		return adminDao.denyExpense(pendPojo);
	}

	@Override
	public ExpensePojo deletePendingExpense(int expenseId) throws SystemException {
		return adminDao.deletePendingExpense(expenseId);
	}

	@Override
	public ExpensePojo fetchNewestExpense() throws SystemException {
		return adminDao.fetchNewestExpense();
	}

	@Override
	public AdminPojo loginAdmin(AdminPojo pojoIn) throws SystemException {
		return adminDao.loginAdmin(pojoIn);
	}

	@Override
	public EmployeePojo loginEmployee(EmployeePojo pojoIn) throws SystemException {
		return adminDao.loginEmployee(pojoIn);
	}

	@Override
	public int pendingAmount() throws SystemException {
		return adminDao.pendingAmount();
	}

}
