import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Person{
	private String name;
	private int age;
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}

	public String getName(){
		return this.name;
	}

	public int getAge(){
		return this.age;
	}

	@Override
	public String toString(){
		return this.name;
	}
}

public class Example {
	public static void supplier(){
		Supplier<StringBuilder> supplier = () -> new StringBuilder().append("test");
		StringBuilder builder = supplier.get();
		System.out.println(builder);
	}

	public static void consumer() {
		Consumer<String> consumer = (String s) -> { System.out.println("Param: " + s.toUpperCase()); };
		consumer.accept("Consumer accept!");
	}

	public static void biconsumer(){
		BiConsumer<StringBuilder, String> bi = (sb, s) -> { sb.append(" " + s.toUpperCase()); };
		StringBuilder sb3 = new StringBuilder("sb");
		String str = "string";
		bi.accept(sb3, str);
		System.out.println(sb3);
		System.out.println(str);
	}

	public static void function() {
		BiFunction<Integer, Double, String> biFunction = (Integer i, Double d) -> { 
			Double inZloty = new Double(i*d);
			return i + " RSD equels " + inZloty + " PLN";
		};

		System.out.println(biFunction.apply(20000, 0.04));
	}

	public static void booleanSupplier(){
		int x = 0, y = 1;
		BooleanSupplier bs = () -> x < y;
		System.out.println("Boolean supplier: " + bs);
	}

	// STREAMS
	public static void operartions(){
		Stream<Integer> si = Stream.of(1,2,3,4,5,6,7,8,9,0);
		boolean matchAll = si.allMatch((i) -> i < 6);
		System.out.println(matchAll);

		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Anna", 28));
		persons.add(new Person("Kamil", 26));
		persons.add(new Person("Rysiek", 27));
		persons.add(new Person("Bartek", 24));
		persons.add(new Person("Krzysztof", 64));
		Optional<Person> op = persons.stream().findFirst();
		System.out.println(op.get());

		op = persons.stream().min((p1, p2) -> { return p1.getAge() - p2.getAge(); });
		System.out.println(op.get());
		persons.stream().forEach(p -> System.out.println("Hi, " + p));
	}

	public static void main(String[] args){
		supplier();
		consumer();
		biconsumer();
		function();
		operartions();
	}
}	
