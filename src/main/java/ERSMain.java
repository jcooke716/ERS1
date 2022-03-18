

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.AdminDaoImpl;
import exceptions.SystemException;
import io.javalin.Javalin;
import pojo.AdminPojo;
import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.FinalExpensesPojo;
import pojo.PendingExpensesPojo;
import service.AdminService;
import service.AdminServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import exceptions.SystemException;

public class ERSMain {
	
	public static final Logger LOG = LogManager.getLogger(ERSMain.class);

	public static void main(String[] args) {
		AdminService adminService = new AdminServiceImpl();
		EmployeeService employeeService = new EmployeeServiceImpl();
		Javalin myServer = Javalin.create((config)->config.enableCorsForAllOrigins()).start(4040);
		System.out.println();
		
		// Fetch employee endpoint
		myServer.get("/api/v1/employees/{eid}", (ctx) -> {
			LOG.info("Fetch employee endpoint consumed.");
			String empId = ctx.pathParam("eid");
			EmployeePojo fetchedEmployee = adminService.fetchEmployee(Integer.parseInt(empId));
			ctx.json(fetchedEmployee);
		});
		
		// fetch admin endpoint
		myServer.get("/api/v1/admins/{aid}", (ctx) -> {
			LOG.info("Fetch admin endpoint consumed.");
			String adminId = ctx.pathParam("aid");
			AdminPojo fetchedAdmin = adminService.fetchAdmin(Integer.parseInt(adminId));
			ctx.json(fetchedAdmin);
		});
		
		// fetch all employees endpoint
		myServer.get("/api/v1/employees", (ctx) -> {
			LOG.info("Fetch all employees endpoint consumed.");
			List<EmployeePojo> allEmployees = adminService.fetchAllEmployees();
			ctx.json(allEmployees);
		});
		
		// fetch all employee expenses endpoint
		myServer.get("/api/v1/expenses/{eid}", (ctx) -> {
			LOG.info("Fetch all employee expenses endpoint consumed.");
			String empId = ctx.pathParam("eid");
			List<ExpensePojo> allEmpExpenses = adminService.fetchAllExpenses(Integer.parseInt(empId));
			ctx.json(allEmpExpenses);
		});
		
		// fetch all pending expenses endpoint
		myServer.get("/api/v1/requests", (ctx) -> {
			LOG.info("Fetch all pending expenses endpoint consumed.");
			List<ExpensePojo> allPendingExpenses = adminService.fetchAllPendingExpenses();
			ctx.json(allPendingExpenses);
		});
		
		// fetch all final expenses endpoint
		myServer.get("/api/v1/resolutions", (ctx) -> {
			LOG.info("Fetch all final expenses endpoint consumed.");
			List<ExpensePojo> allFinalExpenses = adminService.fetchAllFinalExpenses();
			ctx.json(allFinalExpenses);
		});
		
		// admin login endpoint
		myServer.post("/api/v1/login/admins", (ctx) -> {
			LOG.info("Admin login endpoint consumed.");
			AdminPojo adminPojo = ctx.bodyAsClass(AdminPojo.class);
			AdminPojo loginPojo = adminService.loginAdmin(adminPojo);
			ctx.json(loginPojo);
		});
		
		// employee login endpoint
		myServer.post("/api/v1/login/employees", (ctx) -> {
			LOG.info("Employee login endpoint consumed.");
			EmployeePojo employeePojo = ctx.bodyAsClass(EmployeePojo.class);
			EmployeePojo loginPojo = adminService.loginEmployee(employeePojo);
			ctx.json(loginPojo);
		});
		
		// fetch pending expense endpoint
		myServer.get("/api/v1/requests/{pid}", (ctx) -> {
			LOG.info("Fetch pending expense endpoint consumed.");
			String pendId = ctx.pathParam("pid");
			PendingExpensesPojo pendPojo = adminService.fetchPendingExpense(Integer.parseInt(pendId));
			ctx.json(pendPojo);
		});
		
		// delete pending pojo endpoint
		myServer.delete("/api/v1/requests/{pid}", (ctx) -> {
			LOG.info("Delete pending pojo endpoint consumed.");
			String pendId = ctx.pathParam("pid");
			ExpensePojo deletedPojo = adminService.deletePendingExpense(Integer.parseInt(pendId));
			ctx.result("Resolution successful!");
		});
		
		// approving request endpoint
		myServer.post("api/v1/requests/approve", (ctx) -> {
			LOG.info("Approve request endpoint consumed.");
			PendingExpensesPojo pendPojo = ctx.bodyAsClass(PendingExpensesPojo.class);
			FinalExpensesPojo approvedExpense = adminService.approveExpense(pendPojo);
			ctx.json(approvedExpense);
		});
		
		// denying request endpoint
		myServer.post("/api/v1/requests/deny", (ctx) -> {
			LOG.info("Deny expense endpoint consumed.");
			PendingExpensesPojo pendPojo = ctx.bodyAsClass(PendingExpensesPojo.class);
			FinalExpensesPojo deniedExpense = adminService.denyExpense(pendPojo);
			ctx.json(deniedExpense);
		});
		
		// amount of pending requests endpoint
		myServer.get("api/v1/amounts", (ctx) ->{
			LOG.info("Pending expenses amount endpoint consumed.");
			int pendingAmount = adminService.pendingAmount();
			ctx.json(pendingAmount);
		});
		
		/// EMPLOYEE ENDPOINTS ///
		
		// fetch all pending request for employee endpoint
        myServer.get("/api/v1/pending/{eid}", (ctx) -> {
        	LOG.info("Fetch all pending requests for employee endpoint consumed.");
            String employeeId = ctx.pathParam("eid");
            List<ExpensePojo> allPendingExpenses = employeeService.viewPending(Integer.parseInt(employeeId));
            ctx.json(allPendingExpenses);
         });
        
        // fetch all resolved request for employee endpoint
        myServer.get("/api/v1/resolved/{eid}", (ctx) -> {
        	LOG.info("Fetch all resolved request for employee endpoint consumed.");
            String employeeId = ctx.pathParam("eid");
            List<ExpensePojo> allFinalExpenses = employeeService.viewResolved(Integer.parseInt(employeeId));
            ctx.json(allFinalExpenses);
                });
        
        // submit reimbursement request endpoint
        myServer.post("api/v1/requests/submit", (ctx) ->{
        	LOG.info("Submit reimbursement reuqest endpoint consumed.");
        	PendingExpensesPojo pendPojo = ctx.bodyAsClass(PendingExpensesPojo.class);
        	PendingExpensesPojo submittedPojo = employeeService.submitRequest(pendPojo);
        	ctx.json(submittedPojo);
        });
        
        // update employee info endpoint
        myServer.put("api/v1/employees", (ctx) ->{
        	LOG.info("Update employee endpoint consumed.");
        	EmployeePojo pojoIn = ctx.bodyAsClass(EmployeePojo.class);
        	EmployeePojo updatedPojo = employeeService.updateInfo(pojoIn);
        	ctx.json(updatedPojo);
        });
        
        // EXCEPTION HANDLING
        
        // exception handling for SystemException
        myServer.exception(SystemException.class, (se, ctx) -> {
        	LOG.error(se);
			Map<String, String> error = new HashMap<>();
			error.put("message", se.getMessage());
			error.put("date", LocalDateTime.now().toString());
			ctx.json(error);
		});

	}

}
