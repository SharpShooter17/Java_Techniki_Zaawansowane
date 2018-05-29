import java.util.*;
import java.util.function.*;
import java.time.*;

public class Company {
	public static List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("Anna Kowalska", "anna.kowalska@company.com", true, false, Gender.FEMALE, LocalDate.of(2010, Month.JANUARY, 10), 10));
		employees.add(new Employee("Grzegorz Kowalski", "grzegorz.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2011, Month.JANUARY, 10), 12));
		employees.add(new Employee("Marcin Kowalski", "marcin.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2011, Month.JANUARY, 10), 13));
		employees.add(new Employee("Jan Kowalski", "jan.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2010, Month.JANUARY, 10), 10));
		employees.add(new Employee("Marcel Kowalski", "marcel.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2018, Month.JANUARY, 10), 10));
		employees.add(new Employee("Marek Kowalski", "marek.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2014, Month.JANUARY, 10), 10));
		employees.add(new Employee("Jarek Kowalski", "jarek.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2015, Month.JANUARY, 10), 13));
		employees.add(new Employee("Tomasz Kowalski", "tomasz.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2009, Month.JANUARY, 10), 13));
		employees.add(new Employee("Rafal Kowalski", "rafal.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2010, Month.JANUARY, 10), 12));
		employees.add(new Employee("Pawel Kowalski", "pawel.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2012, Month.JANUARY, 10), 12));
		employees.add(new Employee("Zbigniew Kowalski", "zbigniew.kowalski@company.com", true, false, Gender.MALE, LocalDate.of(2013, Month.JANUARY, 10), 12));
		return employees;
	}
}