import java.util.*;

public class Main {
	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("EMP-01-B", false, true));
		employees.add(new Employee("EMP-02-F", true, false));
		employees.add(new Employee("EMP-03-FULL", true, true));
		employees.add(new Employee("EMP-04-B", false, true));
	
		print(employees, "Frontend specialists: ", (Employee e) -> { return e.isFrontendSpecialist(); });
		print(employees, "Backend specialists: ", (Employee e) -> { return e.isBackendSpecialist(); });
	}
	private static void print(List<Employee> employees, String message, SkillsChecker checker) {
		System.out.print(message);
		for (Employee employee : employees) {
			if (checker.check(employee))
				System.out.print(employee + " ");
		}
		System.out.println();
	}
}