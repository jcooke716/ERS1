package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;
import pojo.AdminPojo;
import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.FinalExpensesPojo;
import pojo.PendingExpensesPojo;

public class AdminDaoImpl implements AdminDao {
	
	public static final Logger LOG = LogManager.getLogger(AdminDaoImpl.class);

	@Override
	public EmployeePojo fetchEmployee(int empId) throws SystemException {
		LOG.info("Entered fetchEmployee() in AdminDao");
		Connection conn = DBUtil.getConnection();
		EmployeePojo empPojo = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee_details WHERE emp_id=" + empId;
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				empPojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}

		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchEmployee() in AdminDao");
		return empPojo;

	}

	@Override
	public AdminPojo fetchAdmin(int adminId) throws SystemException {
		LOG.info("Entered fetchAdmin() in AdminDao");
		Connection conn = DBUtil.getConnection();
		AdminPojo adminPojo = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM admin_details WHERE admin_id=" + adminId;
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				adminPojo = new AdminPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchAdmin() in AdminDao");
		return adminPojo;
	}

	@Override
	public List<EmployeePojo> fetchAllEmployees() throws SystemException {
		LOG.info("Entered fetchAllEmployees() in AdminDao");
		Connection conn = DBUtil.getConnection();
		List<EmployeePojo> allEmployees = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee_details ORDER BY emp_id";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				EmployeePojo empPojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8));
				allEmployees.add(empPojo);
			}

		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchAllEmployees() in AdminDao");
		return allEmployees;
	}

	@Override
	public List<ExpensePojo> fetchAllExpenses(int empId) throws SystemException {
		LOG.info("Entered fetchAllExpenses() in AdminDao");
		Connection conn = DBUtil.getConnection();
		List<ExpensePojo> EmployeeExpenses = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String query1 = "SELECT reimbursements_pending.pend_id AS expense_id, employee_details.emp_first_name AS employee_name, reimbursements_pending.pend_amount AS amount, reimbursements_pending.pend_reason AS reason, reimbursements_pending.pend_request_time AS created_at, reimbursements_pending.pend_resolve_time AS resolved_at, admin_details.admin_first_name AS manager, reimbursements_pending.pend_status AS status FROM reimbursements_pending LEFT JOIN employee_details ON reimbursements_pending.pend_request=employee_details.emp_id LEFT JOIN admin_details ON reimbursements_pending.pend_response=admin_details.admin_id WHERE pend_request="
					+ empId;
			ResultSet rs = stmt.executeQuery(query1);
			while (rs.next()) {
				ExpensePojo pendPojo = new ExpensePojo(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getTimestamp(5).toString(), rs.getString(6), rs.getString(7), rs.getString(8));
				EmployeeExpenses.add(pendPojo);
			}
			String query2 = "SELECT reimbursements_final.final_id AS expense_id, employee_details.emp_first_name AS employee_name, reimbursements_final.final_amount AS amount, reimbursements_final.final_reason AS reason, reimbursements_final.final_request_time AS created_at, reimbursements_final.final_resolve_time AS resolved_at, admin_details.admin_first_name AS manager, reimbursements_final.final_status AS status FROM reimbursements_final LEFT JOIN employee_details ON reimbursements_final.final_request=employee_details.emp_id LEFT JOIN admin_details ON reimbursements_final.final_response=admin_details.admin_id WHERE final_request="
					+ empId;
			ResultSet rs2 = stmt.executeQuery(query2);
			while (rs2.next()) {
				ExpensePojo finalPojo = new ExpensePojo(rs2.getInt(1), rs2.getString(2), rs2.getDouble(3),
						rs2.getString(4), rs2.getString(5), rs2.getTimestamp(6).toString(), rs2.getString(7), rs2.getString(8));
				EmployeeExpenses.add(finalPojo);
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchAllExpenses() in AdminDao");
		return EmployeeExpenses;
	}

	@Override
	public List<ExpensePojo> fetchAllPendingExpenses() throws SystemException {
		LOG.info("Entered fetchAllPendingExpenses() in AdminDao");
		Connection conn = DBUtil.getConnection();
		List<ExpensePojo> allEmployeeExpenses = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT reimbursements_pending.pend_id AS expense_id, employee_details.emp_first_name AS employee_name, reimbursements_pending.pend_amount AS amount, reimbursements_pending.pend_reason AS reason, reimbursements_pending.pend_request_time AS created_at, reimbursements_pending.pend_resolve_time AS resolved_at, admin_details.admin_first_name AS manager, reimbursements_pending.pend_status AS status FROM reimbursements_pending LEFT JOIN employee_details ON reimbursements_pending.pend_request=employee_details.emp_id LEFT JOIN admin_details ON reimbursements_pending.pend_response=admin_details.admin_id";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ExpensePojo pendPojo = new ExpensePojo(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getTimestamp(5).toString(), rs.getString(6), rs.getString(7), rs.getString(8));
				allEmployeeExpenses.add(pendPojo);
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchAllPendingExpenses() in AdminDao");
		return allEmployeeExpenses;
	}

	@Override
	public List<ExpensePojo> fetchAllFinalExpenses() throws SystemException {
		LOG.info("Entered fetchAllFinalExpenses() in AdminDao");
		Connection conn = DBUtil.getConnection();
		List<ExpensePojo> allResolvedExpenses = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT reimbursements_final.final_id AS expense_id, employee_details.emp_first_name AS employee_name, reimbursements_final.final_amount AS amount, reimbursements_final.final_reason AS reason, reimbursements_final.final_request_time AS created_at, reimbursements_final.final_resolve_time AS resolved_at, admin_details.admin_first_name AS manager, reimbursements_final.final_status AS status FROM reimbursements_final LEFT JOIN employee_details ON reimbursements_final.final_request=employee_details.emp_id LEFT JOIN admin_details ON reimbursements_final.final_response=admin_details.admin_id;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ExpensePojo finalPojo = new ExpensePojo(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getString(5), rs.getTimestamp(6).toString(), rs.getString(7), rs.getString(8));
				allResolvedExpenses.add(finalPojo);
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchAllFinalExpenses() in AdminDao");
		return allResolvedExpenses;
	}

	@Override
	public AdminPojo loginAdmin(AdminPojo pojoIn) throws SystemException {
		LOG.info("Entered loginAdmin() in AdminDao");
		Connection conn = DBUtil.getConnection();
		AdminPojo loginPojo = new AdminPojo(0, "", "", "", 0, "", "", "");
		AdminPojo fetchedPojo = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM admin_details WHERE admin_email='"+pojoIn.getAdminEmail()+"'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				fetchedPojo = new AdminPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
			if (fetchedPojo.getAdminPassword().equals(pojoIn.getAdminPassword())) {
				loginPojo = fetchedPojo;
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited loginAdmin() in AdminDao");
		return loginPojo;

	}

	@Override
	public PendingExpensesPojo fetchPendingExpense(int expenseId) throws SystemException {
		LOG.info("Entered fetchPendingExpense() in AdminDao");
		Connection conn = DBUtil.getConnection();
		Statement stmt;
		PendingExpensesPojo fetchedExpense = null;
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM reimbursements_pending WHERE pend_id=" + expenseId;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				fetchedExpense = new PendingExpensesPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4),
						rs.getTimestamp(5).toString(), rs.getString(6), rs.getInt(7), rs.getString(8));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited fetchPendingExpense() in AdminDao");
		return fetchedExpense;
	}

	@Override
	public ExpensePojo deletePendingExpense(int expenseId) throws SystemException {
		LOG.info("Entered deletePendingExpense() in AdminDao");
		Connection conn = DBUtil.getConnection();
		ExpensePojo deletedExpense = new ExpensePojo();
		try {
			Statement stmt = conn.createStatement();
			String query1 = "DELETE FROM reimbursements_pending WHERE pend_id=" + expenseId;
			int rows = stmt.executeUpdate(query1);
			deletedExpense.setExpenseId(expenseId);
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited deletePendingExpense() in AdminDao");
		return deletedExpense;

	}

	@Override
	public FinalExpensesPojo approveExpense(PendingExpensesPojo pendPojo) throws SystemException {
		LOG.info("Entered approveExpense() in AdminDao");
		Connection conn = DBUtil.getConnection();
		FinalExpensesPojo approvedRequest = null;
		try {
			Statement stmt = conn.createStatement();
			String query1 = "INSERT INTO reimbursements_final VALUES("+pendPojo.getPendingId()+", " + pendPojo.getPendingRequest() + ", "
					+ pendPojo.getPendingAmount() + ", '" + pendPojo.getPendingReason() + "', '"+pendPojo.getPendingCreated()+"', DEFAULT, "
					+ pendPojo.getPendingResponse() + ", 'APPROVED');";
			int rows = stmt.executeUpdate(query1);
			int finalId = 0;
			String query2 = "SELECT MAX(final_id) FROM reimbursements_final;";
			ResultSet rs1 = stmt.executeQuery(query2);
			if(rs1.next()) {
				finalId = rs1.getInt(1);
			}
			String query3 = "SELECT * FROM reimbursements_final WHERE final_id=" + finalId;
			ResultSet rs2 = stmt.executeQuery(query3);
			while (rs2.next()) {
				approvedRequest = new FinalExpensesPojo(rs2.getInt(1), rs2.getInt(2), rs2.getDouble(3),
						rs2.getString(4), rs2.getString(5), rs2.getTimestamp(6), rs2.getInt(7), rs2.getString(8));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited approveExpense() in AdminDao");
		return approvedRequest;

	}

	@Override
	public FinalExpensesPojo denyExpense(PendingExpensesPojo pendPojo) throws SystemException {
		LOG.info("Entered denyExpense() in AdminDao");
		Connection conn = DBUtil.getConnection();
		FinalExpensesPojo deniedRequest = null;
		try {
			Statement stmt = conn.createStatement();
			String query1 = "INSERT INTO reimbursements_final VALUES("+pendPojo.getPendingId()+", " + pendPojo.getPendingRequest() + ", "
					+ pendPojo.getPendingAmount() + ", '" + pendPojo.getPendingReason() + "', '"+pendPojo.getPendingCreated()+"', DEFAULT, "
					+ pendPojo.getPendingResponse() + ", 'DENIED');";
			int rows = stmt.executeUpdate(query1);
			int finalId = 0;
			String query2 = "SELECT MAX(final_id) FROM reimbursements_final;";
			ResultSet rs1 = stmt.executeQuery(query2);
			if(rs1.next()) {
				finalId = rs1.getInt(1);
			}
			String query3 = "SELECT * FROM reimbursements_final WHERE final_id=" + finalId;
			ResultSet rs2 = stmt.executeQuery(query3);
			while (rs2.next()) {
				deniedRequest = new FinalExpensesPojo(rs2.getInt(1), rs2.getInt(2), rs2.getDouble(3), rs2.getString(4),
						rs2.getString(5), rs2.getTimestamp(6), rs2.getInt(7), rs2.getString(8));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited denyExpense() in AdminDao");
		return deniedRequest;
	}

	@Override
	public ExpensePojo fetchNewestExpense() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeePojo loginEmployee(EmployeePojo pojoIn) throws SystemException {
		LOG.info("Entered loginEmployee() in AdminDao");
		Connection conn = DBUtil.getConnection();
		EmployeePojo loginPojo = new EmployeePojo(0,"","","",0,"","","");
		EmployeePojo fetchedPojo = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee_details WHERE emp_email='"+pojoIn.getEmpEmail()+"'";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				fetchedPojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
			if (fetchedPojo.getEmpPassword().equals(pojoIn.getEmpPassword())) {
				loginPojo = fetchedPojo;
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited loginEmployee() in AdminDao");
		return loginPojo;
	}

	@Override
	public int pendingAmount() throws SystemException {
		LOG.info("Entered pendingAmount() in AdminDao");
		Connection conn = DBUtil.getConnection();
		int num = 0;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT COUNT(pend_id) FROM reimbursements_pending";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited pendingAmount() in AdminDao");
		return num;
	}

}
