package test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class LineTerminatorTest {

	public static void main(String[] args) {
		LineTerminatorTest sol = new LineTerminatorTest();
		System.out.println("Starting process..");
		try {
			sol.printLines();
//			sol.processFilesInSrcDir();
			System.out.println("Process completed..!");
		} catch (Exception e) {
			System.out.println("Error processing file.");
			e.printStackTrace();
		}
	}

	private void printLines() throws Exception {
		Path path = Paths.get("c:\\kiran\\test.txt");
		// System.out.println("tagArray.add(\"" + l + "\");");
		List<String> tagStream;
		List<String> tailStream;
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			tagStream = br.lines().collect(Collectors.toList());
		}
		path = Paths.get("c:\\kiran\\fleet.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			tailStream = br.lines().collect(Collectors.toList());
			Collections.sort(tailStream);
		}
		for (int i = 0; i < tailStream.size(); i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, tagStream.size());
			System.out.println("INSERT INTO tail_tag(tag_name, serial_no) VALUES ('" + tagStream.get(randomNum) + "', '"
					+ tailStream.get(i) + "');");
		}
	}

	private void processFilesInSrcDir() throws IOException {
		Path filePath = Paths.get("newLine.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) { // Reading Header Content
			List<String> lines = br.lines().collect(Collectors.toList());
			processContent(lines, filePath.getFileName().toString());
		}
	}

	private static final String NEWLINE_CHAR = "\\\\n";

	private void processContent(List<String> lines, String fileName) throws IOException {
		String output = "";
		for (String l : lines) {
			System.out.println(l);
			output = l.replaceAll("\\\\r|\\\\n", "").replaceAll("[ ]?<br>[ ]?", NEWLINE_CHAR);
			System.out.println(output);
		}
		Files.write(Paths.get(fileName + "_output.txt"), output.getBytes());
	}
}
