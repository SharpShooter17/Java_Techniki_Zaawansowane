import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.time.*;
import java.time.temporal.*;

public class Main {
	public static void main(String[] args) {
		List<Employee> employees = Company.getAllEmployees();
		
		System.out.println("\nEmployees names:");
		System.out.println(employees.stream().map(e -> e.getName()).collect(Collectors.joining(", ")));
		
		System.out.println("\nAverage level:");
		System.out.println(employees.stream().collect(Collectors.averagingInt(e -> e.getLevel())));

		Map<Integer, List<Employee>> employeesByLevel = employees.stream().collect(Collectors.groupingBy(e -> e.getLevel()));
		System.out.println("\nEmployyes grouped by level:");
		System.out.println(employeesByLevel);
		
		Map<Integer, Long> numberOfEmployeesByLevel = employees.stream().collect(Collectors.groupingBy(e -> e.getLevel(), Collectors.counting()));
		System.out.println("\nNumber of employyes on each level:");
		System.out.println(numberOfEmployeesByLevel);
		
		Map<Boolean, List<Employee>> employeesDevidedByLevel = employees.stream().collect(Collectors.partitioningBy(e -> e.getLevel() > 12));
		System.out.println("\nEmployyes divided to those with level bigger than 12 and the rest:");
		System.out.println(employeesDevidedByLevel);
		
		Map<Boolean, Set<Employee>> empDevidedByLevelSet = employees.stream().collect(Collectors.partitioningBy(e -> e.getLevel() > 12, Collectors.toSet()));
		System.out.println("\nEmployyes divided to those with level bigger than 12 and the rest:");
		System.out.println(empDevidedByLevelSet);
		
		Map<String, Employee> employeesMapEmailKey = employees.stream().collect(Collectors.toMap(e -> e.getEmail(), e -> e));
		System.out.println("\nEmployye map containing email as a key:");
		System.out.println(employeesMapEmailKey);
		
		Map<Integer, String> employeesMapLevelKey = employees.stream().collect(Collectors.toMap(e -> e.getLevel(), e -> e.getName(), (d1, d2) -> d1 + ", " + d2));
		System.out.println("\nEmployye map containing level as a key:");
		System.out.println(employeesMapLevelKey);
	}
}