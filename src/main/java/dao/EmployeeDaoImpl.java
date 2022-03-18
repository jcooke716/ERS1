package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.PendingExpensesPojo;

public class EmployeeDaoImpl implements EmployeeDao {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeDaoImpl.class);

	@Override
	public EmployeePojo fetchEmployee(int empId) {
		LOG.info("Entered fetchEmployee() in EmployeeDao()");
		Connection conn = DBUtil.getConnection();
		EmployeePojo empPojo = null;
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee_details WHERE emp_id="+empId;
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				empPojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("Exited fetchEmployee() in EmployeeDao()");
		return empPojo;
	}

	@Override
	public List<ExpensePojo> viewPending(int employeeId) {
		LOG.info("Entered viewPending() in EmployeeDao()");
		Connection conn = DBUtil.getConnection();
		List<ExpensePojo> allPending = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String query1 = "SELECT reimbursements_pending.pend_id AS expense_id, employee_details.emp_first_name AS employee_name, reimbursements_pending.pend_amount AS amount, reimbursements_pending.pend_reason AS reason, reimbursements_pending.pend_request_time AS created_at, reimbursements_pending.pend_resolve_time AS resolved_at, admin_details.admin_first_name AS manager, reimbursements_pending.pend_status AS status FROM reimbursements_pending LEFT JOIN employee_details ON reimbursements_pending.pend_request=employee_details.emp_id LEFT JOIN admin_details ON reimbursements_pending.pend_response=admin_details.admin_id WHERE pend_request="
					+ employeeId;
			ResultSet rs = stmt.executeQuery(query1);
			while (rs.next()) {
				ExpensePojo pendPojo = new ExpensePojo(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getTimestamp(5).toString(), rs.getString(6), rs.getString(7), rs.getString(8));
				allPending.add(pendPojo);
			}

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		LOG.info("Exited viewPending() in EmployeeDao()");
        return allPending;
    }

	@Override
	public List<ExpensePojo> viewResolved(int employeeId) {
		LOG.info("Entered viewResolved() in EmployeeDao()");
		Connection conn = DBUtil.getConnection();
		List<ExpensePojo> allResolved = new ArrayList<>();

        try {
        Statement stmt = conn.createStatement();

        String query2 = "SELECT reimbursements_final.final_id AS expense_id, employee_details.emp_first_name AS employee_name, reimbursements_final.final_amount AS amount, reimbursements_final.final_reason AS reason, reimbursements_final.final_request_time AS created_at, reimbursements_final.final_resolve_time AS resolved_at, admin_details.admin_first_name AS manager, reimbursements_final.final_status AS status FROM reimbursements_final LEFT JOIN employee_details ON reimbursements_final.final_request=employee_details.emp_id LEFT JOIN admin_details ON reimbursements_final.final_response=admin_details.admin_id WHERE final_request="
				+ employeeId;
		ResultSet rs2 = stmt.executeQuery(query2);
		while (rs2.next()) {
			ExpensePojo finalPojo = new ExpensePojo(rs2.getInt(1), rs2.getString(2), rs2.getDouble(3),
					rs2.getString(4), rs2.getString(5), rs2.getTimestamp(6).toString(), rs2.getString(7), rs2.getString(8));
			allResolved.add(finalPojo);
		}

    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        LOG.info("Exited viewResolved() in EmployeeDao()");
        return allResolved;
    }

	@Override
	public PendingExpensesPojo submitRequest(PendingExpensesPojo pendPojo) {
		LOG.info("Entered submitRequest() in EmployeeDao()");
		Connection conn = DBUtil.getConnection();
		PendingExpensesPojo submittedPojo = null;
		try {
			Statement stmt = conn.createStatement();
			String query1 = "INSERT INTO reimbursements_pending VALUES(DEFAULT, "+pendPojo.getPendingRequest()+", "+pendPojo.getPendingAmount()+", '"+pendPojo.getPendingReason()+"', DEFAULT, '', 1, 'PENDING')";
			int rows = stmt.executeUpdate(query1);
			int pendId = 0;
			String query2 = "SELECT MAX(pend_id) FROM reimbursements_pending";
			ResultSet rs1 = stmt.executeQuery(query2);
			if(rs1.next()) {
				pendId = rs1.getInt(1);
			}
			String query3 = "SELECT * FROM reimbursements_pending WHERE pend_id="+pendId;
			ResultSet rs2 = stmt.executeQuery(query3);
			while(rs2.next()) {
				submittedPojo = new PendingExpensesPojo(rs2.getInt(1), rs2.getInt(2), rs2.getDouble(3), rs2.getString(4), rs2.getTimestamp(5).toString(), rs2.getString(6), rs2.getInt(7), rs2.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("Exited submitRequest() in EmployeeDao()");
		return submittedPojo;
		
	}

	@Override
	public EmployeePojo updateInfo(EmployeePojo empPojo) {
		LOG.info("Entered updateInfo() in EmployeeDao()");
		Connection conn = DBUtil.getConnection();
		EmployeePojo updatedEmpPojo = null;
		try {
			Statement stmt = conn.createStatement();
			String query1 = "UPDATE employee_details SET emp_password='"+empPojo.getEmpPassword()+"', emp_first_name='"+empPojo.getEmpFirstName()+"', emp_last_name='"+empPojo.getEmpLastName()+"', emp_contact="+empPojo.getEmpContact()+", emp_email='"+empPojo.getEmpEmail()+"', emp_address='"+empPojo.getEmpAddress()+"' WHERE emp_id="+empPojo.getEmpId();
			int rows = stmt.executeUpdate(query1);
			updatedEmpPojo = fetchEmployee(empPojo.getEmpId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("Exited updateInfo() in EmployeeDao()");
		return updatedEmpPojo;
	}

}
