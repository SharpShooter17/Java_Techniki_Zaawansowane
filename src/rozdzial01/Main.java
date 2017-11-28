package rozdzial01;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

class generuj <T> implements Supplier<T>{

	@Override
	public T get() {
		return (T) new String("Echo");
	}

}

public class Main {

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.f3();
		m.f2();
		m.f1();
		m.f5();
		m.f6();
	}

	void f6() {
		List<Integer> ints = new ArrayList<>();
		ints.add(1);
		ints.add(10);
		ints.add(7);

		Optional<Integer> max = ints.stream().max(Integer::compareTo);
		System.out.print("Maximum: " + max.orElse(-1));
	}

	void f5(){
		List<String> song = new ArrayList<>();
		song.add("A");
		song.add("A");
		song.add("C");
		song.add("AbecaDlo");
		song.add("");

		//distinct - zwraca "set"
		Stream<String> stream = song.stream().distinct();
		stream = stream.sorted(Comparator.comparing(String::length).reversed());
		Iterator<String> it = stream.iterator();
		for (String i = it.next(); it.hasNext(); i = it.next()) {
			System.out.println(i);
		}
	}

	void f3(){
		List<String> song = new ArrayList<>();
		song.add("A");
		song.add("B");
		song.add("C");
		song.add("AbecaDlo");
		Stream<String> lowerSong = song.stream().map(String::toLowerCase);
		Iterator<String> it = lowerSong.iterator();
		for (String i = it.next(); it.hasNext(); i = it.next()) {
			System.out.println(i);
		}
	}

	void f2() {
		//strumien z argumentow lub tablicy
		Stream<String> song = Stream.of("A", "B", "C", "Abecadlo");
		//generowanie strumienia
		Stream<String> echos = Stream.generate(new generuj()).limit(1);
		//lub
		Stream<String> echos1 = Stream.generate(() -> "echo");
		//inty
		Stream<BigInteger> ints = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(10);
		BigInteger i = BigInteger.ZERO;
		for (Iterator<BigInteger> it = ints.iterator(); it.hasNext(); i = it.next()) {
			System.out.println(i);
		}

	}

	void f1() throws IOException{
		String contents = new String(Files.readAllBytes(Paths.get("D:/Programowanie/Java_Techniki_Zaawansowane/xyz/gutenberg/alice30.txt")),
				StandardCharsets.UTF_8);
		List<String> words = Arrays.asList(contents.split("\\PL"));

		//dluzej
		long count = 0;
		for (String string : words) {
			if (string.length() > 12){
				count++;
			}
		}
		//krocej
		long count1 = words.stream().filter(w -> w.length() > 12).count();
		System.out.println( count + "  " + count1 );
		//szbciej zliczanie i filtrowanie w tym samym czasie
		//https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
		//https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html
		//https://docs.oracle.com/javase/8/docs/api/java/util/List.html
		count = words.parallelStream().filter(w -> w.length() > 12).count();
	}

}
