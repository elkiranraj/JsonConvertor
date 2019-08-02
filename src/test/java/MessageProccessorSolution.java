package test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MessageProccessorSolution {

	private static final String SRC_DIR = "C:/KiranData/Message Transformer test - senior/messages";
	private static final String DEST_DIR = "C:/KiranData/Message Transformer test - senior/output/";
	private static final String EMPTY = "";
	private static final String SPACE = " ";

	private static final String REGEX_ALPHA_CHAR = "([\\u0020-\\u007F]*)?"; // Regex for printable characters
	private static final String REGEX_NUM_TEN = "([0-9]{10})"; // Regex for searching 10 consecutive numbers

	// Search Content
	private static final String TO = "To:";
	private static final String SUBJECT = "Subject:";
	private static final String BODY = "Body:";

	// Rules
	private static final String RULE_TO = "@domain.com";
	private static final String RULE_SUBJECT = "SECURE:";

	private static HashMap<String, String> replaceMap;
	static {
		replaceMap = new HashMap<>();
		replaceMap.put("$", "e");
		replaceMap.put("^", "y");
		replaceMap.put("&", "u");
	}

	public static void main(String[] args) {
		MessageProccessorSolution sol = new MessageProccessorSolution();
		System.out.println("Starting process..");
		try {
			sol.processFilesInSrcDir();
			System.out.println("Process completed..!");
			System.out.println("Files are in :: " + DEST_DIR);
		} catch (Exception e) {
			System.out.println("Error processing file.");
			e.printStackTrace();
		}
	}

	private void processFilesInSrcDir() throws IOException {
		Path inputDir = Paths.get(SRC_DIR.trim());
		DirectoryStream<Path> inputFilesStream = Files.newDirectoryStream(inputDir);
		Files.createDirectories(Paths.get(DEST_DIR));
		for (Path filePath : inputFilesStream) {
			try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) { // Reading Header Content
				List<String> lines = br.lines().collect(Collectors.toList());
				processContent(lines, filePath.getFileName().toString());
			}
		}
	}

	private void processContent(List<String> lines, String fileName) throws IOException {
		boolean applyReversal = false;
		boolean applyReplacement = false;
		int bodyLineIndex = 0;
		String l;
		for (int i = 0; i < lines.size(); i++) {
			l = lines.get(i);
			if (l.startsWith(TO) && l.contains(RULE_TO)) {
				applyReplacement = true;
			}
			if (l.trim().startsWith(SUBJECT)) {
				applyReversal = checkSubject(l);
			}
			if (l.startsWith(BODY)) {
				bodyLineIndex = i + 1;
				if (applyReplacement && applyReversal) { // To prevent unnecessary rule processing for the body when
															// the rules are true
					break;
				}
				String body;
				for (int j = bodyLineIndex; j < lines.size(); j++) {
					body = lines.get(j);
					if (body.matches(REGEX_ALPHA_CHAR + REGEX_NUM_TEN + REGEX_ALPHA_CHAR)) {
						applyReplacement = true;
						applyReversal = true;
					}
				}
			}
		}
		processOutput(lines, bodyLineIndex, applyReplacement, applyReversal, fileName);
	}

	private void processOutput(List<String> lines, int bodyLineIndex, boolean applyReplacement, boolean applyReversal,
			String fileName) throws IOException {
		StringBuilder output = new StringBuilder();
		String l;
		for (int i = bodyLineIndex; i < lines.size(); i++) {
			l = lines.get(i);
			if (applyReplacement) {
				l = replaceChars(l);
			}
			if (applyReversal) {
				l = reverseLine(l);
			}
			output.append(l).append(System.lineSeparator());
		}
		Files.write(Paths.get(DEST_DIR + fileName), output.toString().getBytes());
	}

	private boolean checkSubject(String l) {
		String temp = l.replace(SUBJECT, EMPTY);
		return temp.trim().startsWith(RULE_SUBJECT);
	}

	private String reverseLine(String l) {
		StringBuilder str = new StringBuilder();
		String[] words = l.split(SPACE);
		for (int i = 0; i < words.length; i++) {
			str.append(revWord(words[i])).append(SPACE);
		}
		return str.toString().trim();
	}

	private String revWord(String string) {
		return new StringBuilder(string).reverse().toString();
	}

	private String replaceChars(String l) {
		for (String s : replaceMap.keySet()) {
			l = l.replace(s, replaceMap.get(s));
		}
		return l;
	}

}
