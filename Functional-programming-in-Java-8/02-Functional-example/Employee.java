public class Employee {
	private String employeeId;
	private boolean isFrontendSpecialist;
	private boolean isBackendSpecialist;
	
	public Employee(String employeeId, boolean isFrontendSpecialist, boolean isBackendSpecialist) {
		this.employeeId = employeeId;
		this.isFrontendSpecialist = isFrontendSpecialist;
		this.isBackendSpecialist = isBackendSpecialist;
	}

	public boolean isFrontendSpecialist() {
		return isFrontendSpecialist;
	}
		
	public boolean isBackendSpecialist() {
		return isBackendSpecialist;
	}
	
	public String toString() {
		return employeeId;
	}
}