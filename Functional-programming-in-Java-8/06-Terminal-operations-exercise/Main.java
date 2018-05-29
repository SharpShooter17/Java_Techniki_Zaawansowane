import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.time.*;
import java.time.temporal.*;
import java.time.temporal.ChronoUnit;

public class Main {
	public static void main(String[] args) {
		List<Employee> employees = Company.getAllEmployees();
		System.out.println("Czy pracuje jakas kobieta? " + employees.stream().anyMatch(e -> e.getGender() == Gender.FEMALE));
		System.out.println("Czy ka¿dy pracownik pracuje wiecej niz dwa lata? " + employees.stream().allMatch(e -> { return ChronoUnit.YEARS.between(e.getWorksSince(), LocalDate.now()) > 2;}));
		System.out.println("Czy kazdy ma inny level niz 11? " + employees.stream().noneMatch(e -> e.getLevel() == 11));
		System.out.println("Ilosc pracownikow: " + employees.stream().count());
		System.out.println("Kto pracuje najdluzej? " + employees.stream().min( (e1, e2) -> { return e1.getWorksSince().isBefore(e2.getWorksSince()) ? -1 : 1 ; } ));
		System.out.println("Kto pracuje na najwyzszym levelu? " + employees.stream().max( (e1, e2) -> { return e1.getLevel() - e2.getLevel(); } ).get() );
	}
}
