import java.util.function.*;

public class Main {
	static String staticVariable = "static";
	String instanceVariable = "instance";
	public static void main(String[] args) {
		new Main().test("effectively final method param");
	}
	
	void test(String effectivelyFinalMethodParam) {
		String effectivelyFinalLocalVariable = "effectively final local";
		//effectivelyFinalMethodParam = "compilation error";
		//effectivelyFinalLocalVariable = "compilation error";
		
		print(() -> instanceVariable);
		print(() -> staticVariable);
		print(() -> effectivelyFinalMethodParam);
		print(() -> effectivelyFinalLocalVariable);
		
		instanceVariable = "instance changed";
		print(() -> instanceVariable);
	}
	
	void print(Supplier<String> f) {
		System.out.println(f.get());
	}
}