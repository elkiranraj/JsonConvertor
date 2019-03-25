package test.java;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ReadCountryCode {

	public static void main(String[] args) throws FileNotFoundException {
		File f = new File("orderby.txt");
		Scanner in = new Scanner(f);

		Map<String, String> map = new TreeMap<>();
		while (in.hasNextLine()) {
//			System.out.println(in.nextLine());
			map.put(in.nextLine(), "");
		}
		map.entrySet().stream().forEach(System.out::println);
	}

}
