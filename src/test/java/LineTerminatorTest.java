package test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LineTerminatorTest {

	public static void main(String[] args) {
		LineTerminatorTest sol = new LineTerminatorTest();
		System.out.println("Starting process..");
		try {
			sol.processFilesInSrcDir();
			System.out.println("Process completed..!");
		} catch (Exception e) {
			System.out.println("Error processing file.");
			e.printStackTrace();
		}
	}

	private void processFilesInSrcDir() throws IOException {
		Path filePath = Paths.get("newLine.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) { // Reading Header Content
			List<String> lines = br.lines().collect(Collectors.toList());
			processContent(lines, filePath.getFileName().toString());
		}
	}

	private void processContent(List<String> lines, String fileName) throws IOException {
		String output = "";
		for (String l : lines) {
			System.out.println(l);
			//l = l.replace("\\\\", "\\");
			output = l.replaceAll("\\\\r|\\\\n", "");
			System.out.println(output);
		}
		System.out.println(lines.get(0).replaceAll("(\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029])", " * "));
		Files.write(Paths.get(fileName + "_output.txt"), output.getBytes());
	}
}
