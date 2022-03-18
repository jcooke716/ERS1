package pojo;

public class EmployeePojo {
	
	private int empId;
	private String empPassword;
	private String empFirstName;
	private String empLastName;
	private long empContact;
	private String empEmail;
	private String empAddress;
	private String empRole;
	
	public EmployeePojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeePojo(int empId, String empPassword, String empFirstName, String empLastName, long empContact,
			String empEmail, String empAddress, String empRole) {
		super();
		this.empId = empId;
		this.empPassword = empPassword;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empContact = empContact;
		this.empEmail = empEmail;
		this.empAddress = empAddress;
		this.empRole = empRole;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public long getEmpContact() {
		return empContact;
	}

	public void setEmpContact(long empContact) {
		this.empContact = empContact;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	@Override
	public String toString() {
		return "EmployeePojo [empId=" + empId + ", empPassword=" + empPassword + ", empFirstName=" + empFirstName
				+ ", empLastName=" + empLastName + ", empContact=" + empContact + ", empEmail=" + empEmail
				+ ", empAddress=" + empAddress + ", empRole=" + empRole + "]";
	}
	
	
	
	
	
	
	
	

}
