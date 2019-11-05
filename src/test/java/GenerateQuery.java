package test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GenerateQuery {

	public static void main(String[] args) {
		GenerateQuery sol = new GenerateQuery();
		System.out.println("Starting process..");
		try {
			sol.printLines();
			System.out.println("Process completed..!");
		} catch (Exception e) {
			System.out.println("Error processing file.");
			e.printStackTrace();
		}
	}

	private void printLines1() throws Exception {
		Path path = Paths.get("c:\\kiran\\squadron1.txt");
		List<String> tailStream;
		List<String> tagSquadronStream;
		List<String> tagTycomStream;
		try (BufferedReader br = new BufferedReader(
				new FileReader(path.toFile()))) {
			tagSquadronStream = br.lines().distinct().filter(s -> !s.isEmpty())
					.collect(Collectors.toList());
			Collections.sort(tagSquadronStream, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareToIgnoreCase(o2);
				}
			});
		}
		path = Paths.get("c:\\kiran\\tycom1.txt");
		try (BufferedReader br = new BufferedReader(
				new FileReader(path.toFile()))) {
			tagTycomStream = br.lines().distinct().filter(s -> !s.isEmpty())
					.collect(Collectors.toList());
		}
		path = Paths.get("c:\\kiran\\fleet.txt");
		try (BufferedReader br = new BufferedReader(
				new FileReader(path.toFile()))) {
			tailStream = br.lines().filter(s -> !s.isEmpty())
					.collect(Collectors.toList());
		}
		int sqRandom;

		int tailRandom1;
		int tailRandom2;
		int tailRandom3;
		String INSERT_STMT = "INSERT INTO tail_tag_serial_no(tag_name, serial_no) VALUES ('";

		System.out.println("SQ:: " + tagSquadronStream.size());
		System.out.println("TAIL:: " + tailStream.size());
		for (int i = 0; i < tagTycomStream.size(); i++) {
			System.out.println("-- TY :: " + tagTycomStream.get(i));
			sqRandom = ThreadLocalRandom.current().nextInt(0,
					tagSquadronStream.size());
			tailRandom1 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println("-- TY :: " + tagTycomStream.get(i) + "- SQ ::"
					+ tagSquadronStream.get(sqRandom));
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom1) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom)
					+ "', '" + tailStream.remove(tailRandom1) + "');");

			tailRandom2 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom2) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom)
					+ "', '" + tailStream.remove(tailRandom2) + "');");

			tailRandom3 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom3) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.remove(sqRandom)
					+ "', '" + tailStream.remove(tailRandom3) + "');");

			sqRandom = ThreadLocalRandom.current().nextInt(0,
					tagSquadronStream.size());
			tailRandom1 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println("-- TY :: " + tagTycomStream.get(i) + "- SQ ::"
					+ tagSquadronStream.get(sqRandom));
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom1) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom)
					+ "', '" + tailStream.remove(tailRandom1) + "');");

			tailRandom2 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom2) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom)
					+ "', '" + tailStream.remove(tailRandom2) + "');");

			tailRandom3 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom3) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.remove(sqRandom)
					+ "', '" + tailStream.remove(tailRandom3) + "');");

			sqRandom = ThreadLocalRandom.current().nextInt(0,
					tagSquadronStream.size());
			tailRandom1 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println("-- TY :: " + tagTycomStream.get(i) + "- SQ ::"
					+ tagSquadronStream.get(sqRandom));
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom1) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom)
					+ "', '" + tailStream.remove(tailRandom1) + "');");

			tailRandom2 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom2) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom)
					+ "', '" + tailStream.remove(tailRandom2) + "');");

			tailRandom3 = ThreadLocalRandom.current().nextInt(0,
					tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '"
					+ tailStream.get(tailRandom3) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.remove(sqRandom)
					+ "', '" + tailStream.remove(tailRandom3) + "');");

			System.out.println();
		}
		System.out.println("SQ:: " + tagSquadronStream.size());
		System.out.println("TAIL:: " + tailStream.size());
	}

	private void printLines() throws Exception {
		Path path = Paths.get("c:\\kiran\\squadron.txt");
		List<String> tailStream;
		List<String> tagSquadronStream;
		List<String> tagTycomStream;
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			tagSquadronStream = br.lines().distinct().filter(s -> !s.isEmpty()).collect(Collectors.toList());
			Collections.sort(tagSquadronStream, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareToIgnoreCase(o2);
				}
			});
		}
		path = Paths.get("c:\\kiran\\tycom.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			tagTycomStream = br.lines().distinct().filter(s -> !s.isEmpty()).collect(Collectors.toList());
		}
		path = Paths.get("c:\\kiran\\fleet.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			tailStream = br.lines().filter(s -> !s.isEmpty()).collect(Collectors.toList());
		}
		int sqRandom;

		int tailRandom1;
		int tailRandom2;
		int tailRandom3;
		String INSERT_STMT = "INSERT INTO tail_tag_serial_no(tag_name, serial_no) VALUES ('";

		System.out.println("SQ:: " + tagSquadronStream.size());
		System.out.println("TAIL:: " + tailStream.size());
		for (int i = 0; i < tagTycomStream.size(); i++) {
			System.out.println("-- TY :: " + tagTycomStream.get(i));
			sqRandom = ThreadLocalRandom.current().nextInt(0, tagSquadronStream.size());
			tailRandom1 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println("-- TY :: " + tagTycomStream.get(i) + "- SQ ::" + tagSquadronStream.get(sqRandom));
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom1) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom) + "', '" + tailStream.remove(tailRandom1) + "');");

			tailRandom2 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom2) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom) + "', '" + tailStream.remove(tailRandom2) + "');");

			tailRandom3 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom3) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.remove(sqRandom) + "', '" + tailStream.remove(tailRandom3) + "');");

			sqRandom = ThreadLocalRandom.current().nextInt(0, tagSquadronStream.size());
			tailRandom1 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println("-- TY :: " + tagTycomStream.get(i) + "- SQ ::" + tagSquadronStream.get(sqRandom));
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom1) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom) + "', '" + tailStream.remove(tailRandom1) + "');");

			tailRandom2 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom2) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom) + "', '" + tailStream.remove(tailRandom2) + "');");

			tailRandom3 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom3) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.remove(sqRandom) + "', '" + tailStream.remove(tailRandom3) + "');");

			sqRandom = ThreadLocalRandom.current().nextInt(0, tagSquadronStream.size());
			tailRandom1 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println("-- TY :: " + tagTycomStream.get(i) + "- SQ ::" + tagSquadronStream.get(sqRandom));
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom1) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom) + "', '" + tailStream.remove(tailRandom1) + "');");

			tailRandom2 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom2) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.get(sqRandom) + "', '" + tailStream.remove(tailRandom2) + "');");

			tailRandom3 = ThreadLocalRandom.current().nextInt(0, tailStream.size());
			System.out.println(INSERT_STMT + tagTycomStream.get(i) + "', '" + tailStream.get(tailRandom3) + "');");
			System.out.println(INSERT_STMT + tagSquadronStream.remove(sqRandom) + "', '" + tailStream.remove(tailRandom3) + "');");

			System.out.println();
		}
		System.out.println("SQ:: " + tagSquadronStream.size());
		System.out.println("TAIL:: " + tailStream.size());
	}
}
