package servicelayertests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import exceptions.SystemException;
import pojo.AdminPojo;
import service.AdminServiceImpl;

public class AdminServiceTest {
	
//	public static final Logger LOG = LogManager.getLogger(AdminServiceTest.class);
//	public static AdminServiceImpl adminService = Mockito.mock(AdminServiceImpl.class);
//	
//	@Test
//	public void testAdminLoginCorrect() {
//		AdminPojo adminIn = new AdminPojo(0, "password","","",0,"bossbaby@gmail","","admin");
//		try {
//			AdminPojo actualOutput = adminService.loginAdmin(adminIn);
//			AdminPojo expectedOutput = new AdminPojo(1, "password", "Boss","Baby",1234567890, "bossbaby@gmail.com", "555 Business Street, Hollywood CA 32567", "admin");
//			assertEquals(expectedOutput,actualOutput);
//		} catch (SystemException e) {
//			LOG.error(e);
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	@Test
//	public void testAdminLoginIncorrect() {
//		AdminPojo adminIn = new AdminPojo(0, "password","","",0,"bobsmith@gmail","","admin");
//		try {
//			AdminPojo actualOutput = adminService.loginAdmin(adminIn);
//			AdminPojo expectedOutput = new AdminPojo(0, "", "","",0, "", "", "");
//			assertEquals(expectedOutput,actualOutput);
//		} catch (SystemException e) {
//			LOG.error(e);
//			System.out.println(e.getMessage());
//		}
//	}
	

}
