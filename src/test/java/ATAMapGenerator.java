package test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ATAMapGenerator {

	public static void main(String[] args) {
		ATAMapGenerator sol = new ATAMapGenerator();
		// System.out.println("Starting process..");
		try {
			sol.printLines();
			// System.out.println("Process completed..!");
		} catch (Exception e) {
			System.out.println("Error processing file.");
			e.printStackTrace();
		}
	}

	private void printLines() throws Exception {
		Path path = Paths.get("c:\\kiran\\ata_map.csv");
		List<String> ataStream;
		try (BufferedReader br = new BufferedReader(
				new FileReader(path.toFile()))) {
			ataStream = br.lines().collect(Collectors.toList());
		}
		List<ATA> ataList = new ArrayList<>();
		for (int i = 1; i < ataStream.size(); i++) {
			String[] a = ataStream.get(i).trim()
					// .replace("\"", "")
					.split(",", -1);
			// System.out.println(ataStream.get(i) + " :: " + a.length);
			ATA ata = new ATA();
			switch (a[0]) {
				case "H1" :
					if (a.length > 2) {
						for (int j = 2; j < a.length; j++) {
							if (!a[j].isEmpty())
								a[1] += "," + a[j];
						}
					}
					ata.setType("H1");
					ata.setDesc(a[1]);
					ataList.add(ata);
					// System.out.println("H1: " + a[0]);
					break;
				case "H2" :
					if (a.length > 2) {
						for (int j = 2; j < a.length; j++) {
							if (!a[j].isEmpty())
								a[1] += "," + a[j];
						}
					}
					ata.setType("H2");
					ata.setDesc(a[1]);
					ataList.add(ata);
					// System.out.println("H1: " + a[0]);
					break;
				case "" :
					if (a.length > 3) {
						for (int j = 3; j < a.length; j++) {
							a[2] += "," + a[j];
						}
					}
					if (ataList.get(ataList.size() - 1).getDesc2() == null)
						ataList.get(ataList.size() - 1).setDesc2(a[2]);
					else {
						String desTmp = ataList.get(ataList.size() - 1)
								.getDesc2();
						ataList.get(ataList.size() - 1)
								.setDesc2(desTmp + ", " + a[2]);
					}
					// System.out.println("\"\": " + a[0]);
					break;
				default :
					if (a.length > 3) {
						for (int j = 3; j < a.length; j++) {
							a[2] += "," + a[j];
						}
					}
					ata.setType("A");
					ata.setAta(a[1].trim().length() < 4 ? "0" + a[1] : a[1]);
					ata.setDesc(a[2]);
					ataList.add(ata);
					// System.out.println("Num: " + a[0]);
					break;
			}
		}
		System.out.println("<table class='ataTable'>");
		for (ATA ata : ataList) {
			// System.out.println(ata);
			System.out.println("<tr>");

			if (ata.getType().equalsIgnoreCase("H1")) {
				System.out.println("<td colspan=2 class='tdAtaTwoDigit'>");
				System.out.println(ata.getDesc().replace("00", ""));
				System.out.println("</td>");
			} else if (ata.getType().equalsIgnoreCase("H2")) {
				System.out.println("<td colspan=2 class='tdAtaThreeDigit'>");
				System.out.println(ata.getDesc());
				System.out.println("</td>");
			} else {
				System.out.println("<td class='tdAtaCode'>");
				System.out.println("<chr:ataDisplayCode predictedAtaCode=\""
						+ ata.getAta() + "\"/>");
				System.out.println("</td>");

				System.out.println("<td class='tdAtaDesc'>");
				if (ata.getDesc2() != null) {
					System.out.print(ata.getDesc() + "<br>" + ata.getDesc2());
				} else {
					System.out.println(ata.getDesc());
				}
				System.out.println("</td>");
			}

			System.out.println("</tr>");
		}
		System.out.println("</table>");
	}

	class ATA {
		private String type;
		private String ata;
		private String desc;
		private String desc2;

		public ATA() {
		}

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getAta() {
			return ata;
		}
		public void setAta(String ata) {
			this.ata = ata;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getDesc2() {
			return desc2;
		}

		public void setDesc2(String desc2) {
			this.desc2 = desc2;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("ATA [type=");
			builder.append(type);
			builder.append(", ata=");
			builder.append(ata);
			builder.append(", desc=");
			builder.append(desc);
			if (desc2 != null) {
				builder.append(" ");
				builder.append(desc2);
			}
			builder.append("]");
			return builder.toString();
		}

	}
}
