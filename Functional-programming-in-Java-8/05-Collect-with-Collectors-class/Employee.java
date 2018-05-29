import java.time.LocalDate;

public class Employee {
	private String name;
	private String email;
	private boolean isFrontendSpecialist;
	private boolean isBackendSpecialist;
	private Gender gender;
	private LocalDate worksSince;
	private int level;
	
	public Employee(String name, String email, boolean isFrontendSpecialist, boolean isBackendSpecialist, Gender gender, LocalDate worksSince, int level) {
		this.name = name;
		this.email = email;
		this.isFrontendSpecialist = isFrontendSpecialist;
		this.isBackendSpecialist = isBackendSpecialist;
		this.gender = gender;
		this.worksSince = worksSince;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public boolean isFrontendSpecialist() {
		return isFrontendSpecialist;
	}
		
	public boolean isBackendSpecialist() {
		return isBackendSpecialist;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public LocalDate getWorksSince() {
		return worksSince;
	}
	
	public int getLevel() {
		return level;
	}
	
	public String toString() {
		return name + ", " + email;
	}
}

enum Gender {
	MALE, FEMALE
}