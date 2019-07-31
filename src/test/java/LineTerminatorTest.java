package test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
//		Pattern pat = Pattern.compile("\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029]");//.matcher(this).replaceAll(replacement)
		for (String l : lines) {
//			l="\"[{\"PK\":\"402867146bf6d8a3016bf6d8d9e80029\",\"ClusterName\":\"640-0506\",\"SerialNum\":\"640\",\"eportedDate\":new Date(1563148800000),\"esolvedDate\":new Date(1564012800000),\"EquipmentCode\":\"2911\",\"Description\":\"ON T/O WITH GEA SELECTED UP C HYD EICAS AS WELL AS GEA DISAGEE C HYD PSI DOPPED TO1610 PSI, IN FLIGHT C HYD PSI OSE TO 2980 PSI, SAME HAPPENED ON APP. ( ADP CONTOLLE - AUTO FUNCTION )\",\"CorrectiveAction\":\"S35 CENTE HYD PESS SWITCH EPLACED AS PE AMM 29-11-09-424-005...CHKD SEVICEABLE [WP: AC216153]...EPLACED ON SPEC AS PE FLEET EQUEST.... [MEL C: 29-11-04-A0] [MDD: 464119]\",\"DocumentId\":\"L5725250-1\",\"ClusterPK\":\"402867146b248f76016b249082300117\",\"isJunk\":false,\"metadata_ESOLVED_STATION\":\"YYZ\",\"metadata_EPOTED_STATION\":\"YYC\",\"metadata_TS_STEPS\":\"[2019-07-22][ONGOING] TOUBLESHOOTING C/O AS PE FIM 29-11 -813 WIING CHECK ON CONNECTO D466 ON ADP FILTE MODULE EQD TO CONFIM CONTOL PX SWITCH FAILUE. PATS PUT ON SPEC <br> [2019-07-23][ONGOING] FIM POCEDUE 29-11-813 CAIED OUT, NO FAULT FOUND.  PESSUE SWITCH S29 FOUND PEVIOUSLY EPLACED.  EQUIES ADDITIONAL TOUBLESHOOTING. <br> [2019-07-24][ONGOING] SEQ 3:\\\\r\\\\r\\\\nTECH SUPPOT BACKGOUND INFOMATION:\\r\\r\\n. SINCE MACH 2019 THEE HAVE BEEN 16 DEFECTS ELATE TO THE ADP AUTO FUNCTION\\r\\r\\n. THE FOLLOWING PATS HAVE BEEN EPLACED:\\r\\r\\n17-MA-2019: EPLACED ADP\\r\\r\\n11-AP-2019 : EPLACED ADP ON DMD LY K684\\r\\r\\n23-MAY-2019 EPLACED CENTE 2 ACMP PE UNIT EXCHANGE TASK CAD DUE TIME X\\r\\r\\nADP CONTOL PESS SWITCH S29 EPLACED.\\r\\r\\n04-JUN-2019: EPLACE C2 HYD PUMP PESSUE SWITCH S34 ON ACMP2 FILTE MODULE\\r\\r\\n19-JUN-2019: ADP MODULATING VALVE EPLACED \\r\\r\\n25-JUN-2019 EPLACED ADP OVESPEED /ELECTONIC CONTOL (M1057) CAD\\r\\r\\n14-JUL-2019 CHANGED K153 AND M305\\r\\r\\n. WHEN THE MEL IS APPLIED AND THE APD IS OPEATED IN IAW THE MEL AND THE CONTOL IS ON FO TAKE-OFF AND LANDING, THEE HAVE NOT BEEN ANY ADDITIONAL EPOTS OF GEA DISAGEE, SUGGESTING THAT THE ADP IS HYDAULICALLY SOUND.\\r\\r\\n\\r\\r\\nTECH SUPPOT ECOMMENDATIONS:\\r\\r\\n*** THEE SHOULD BE NO NEED TO PLACE THE AICAFT ON JACKS TO FULLY TEST THE ADP FO DISPATCH. TEST THE ADP PE THE AMM TASK AT THE COMPLETION OF THE WIING CHECKS ***\\r\\r\\nCONTINUE TO INVESTIGATE THE ADP AUTO ON AND INDICATION CICUITS FO DISCEPANCIES.\\r\\r\\nEFE TO SSM29-00-05 PAGE 104 SH 1 AND 2   AND WDM 29-11-32 PAGE 6 SH 1, 2, AND 3\\r\\r\\n. EPLACE HYDAULIC PESSUE SWITCH S35 IN THE IGHT WHEEL WELL ON SPEC\\r\\r\\n. EMOVE THE HYDAULIC SYSTEM CONTOL PANEL AND CHECK THE CICUITS THOUGH THE PANEL SWITCH S6 FO THE AUTO POSITION\\r\\r\\n     D1406 PIN 5 TO D1406 PIN 17\\r\\r\\n     D1406 PI\",\"metadata_DOCUMENT_STATUS\":\"CLOSED\",\"metadata_MEL_NUMBE\":\"29-11-04-A0\",\"metadata_DEFE\":\"PLAN\",\"metadata_FLIGHT\":\"AC137\",\"metadata_EPOT_PN\":\"211C223-297, 211C223-301, NAS1612-6\",\"metadata_PICKLIST_PN\":\"211C223-534\",\"metadata_FLEET_FULL\":\"B767-375E\",\"metadata_FIN_NO\":\"CF6-8002\",\"metadata_MDD\":\"464119\",\"metadata_MEL\":\"C\"},{\"PK\":\"402867146c072749016c072771c900a2\",\"SerialNum\":\"656\",\"eportedDate\":new Date(1563408000000),\"esolvedDate\":new Date(1563494400000),\"EquipmentCode\":\"1120\",\"Description\":\"AS SNAGGED BY THE SAFA INSPECTOS IN MS, THE ADP PLACAD ON LH SIDE OF FUSELAGE IS NOT LEDGIBLE.\",\"CorrectiveAction\":\"ADP PLACAD FOUND TO BE INTACT ON THIS AICAFT. (656) [MDD: 464724]\",\"DocumentId\":\"M2255023-1\",\"isJunk\":true,\"metadata_ESOLVED_STATION\":\"YYZ\",\"metadata_EPOTED_STATION\":\"YYZ\",\"metadata_FLEET_FULL\":\"B767-333EGEV\",\"metadata_FIN_NO\":\"GE\",\"metadata_MDD\":\"464724\",\"metadata_DOCUMENT_STATUS\":\"CLOSED\",\"metadata_DEFE\":\"PLAN\"},{\"PK\":\"402867146c245642016c245656d00008\",\"SerialNum\":\"636\",\"eportedDate\":new Date(1563926400000),\"esolvedDate\":new Date(1563926400000),\"EquipmentCode\":\"2911\",\"Description\":\"ADP CASE DAIN FOUND LEAKING.\",\"CorrectiveAction\":\"PESSUE INLET AND OUTLET UNION PACKING OF ADP CASE DAIN EPLACED AS PE AMM 29-11-19.\\r\\r\\nLEAK CHECK C/O. CHK SEVICEABLE.\\r\\r\\nWP:211927\",\"DocumentId\":\"M2255725-1\",\"isJunk\":false,\"metadata_ESOLVED_STATION\":\"YYZ\",\"metadata_EPOTED_STATION\":\"YYZ\",\"metadata_FLEET_FULL\":\"B767-333EPWV\",\"metadata_FIN_NO\":\"PW\",\"metadata_DOCUMENT_STATUS\":\"CLOSED\"},{\"PK\":\"402867146c365c3c016c365c486d002e\",\"ClusterName\":\"640-0506\",\"SerialNum\":\"640\",\"eportedDate\":new Date(1564185600000),\"EquipmentCode\":\"2911\",\"Description\":\"AFTE T/O ON SELECTION OF GEA UP 'C HYD PESS' EICAS MESSAGE APPEAED FOLLOWED BY 'GEA DISSAGE' MESSAGE.  EXTINGUISHED WHEN GEA WAS FULLY UP.   HYD PESSUE WENT TO 1650PSI.   ADP SELECTED ON.  ... ( ADP CONTOLLE - AUTO FUNCTION )\",\"CorrectiveAction\":\". [MEL C: 29-11-04-A0] [MDD: 466388]\",\"DocumentId\":\"L5715873-1\",\"ClusterPK\":\"402867146b248f76016b249082300117\",\"isJunk\":false,\"metadata_EPOTED_STATION\":\"YOW\",\"metadata_FLEET_FULL\":\"B767-375E\",\"metadata_TS_STEPS\":\"[2019-07-28][ONGOING] CAIED OUT PESSUE CHECK OF BOTH CENTE PUMPS, OPEATE NOMAL. SELECTED ADP ALONE TO ON POSITION AND ADP PESSUIZES NOMALLY. CAIED OUT HIGH DEMAND CHECK BY SELECTING BOTH CENTE SWITCHES TO ON AND SELECTING ADP TO AUTO, THEN SELECTED FLAPS DOWN. ADP CONFIMED TO KICK IN. AFTE SELECTING FLAP UP AND FULLY STOWED, ADP WOULD NOT SHUT OFF. HAD TO SELECT TO OFF POSITION.\",\"metadata_FIN_NO\":\"CF6-8002\",\"metadata_MDD\":\"466388\",\"metadata_DOCUMENT_STATUS\":\"OPEN\",\"metadata_MEL_NUMBE\":\"29-11-04-A0\",\"metadata_MEL\":\"C\",\"metadata_DEFE\":\"PLAN\",\"metadata_FLIGHT\":\"AC889\"}]4,0\"";
			System.out.println(l);
//			Matcher m = pat.matcher(l);
//			System.out.println(m.groupCount());
			output = l.replaceAll("\\\\R", " * ");
			System.out.println(output);
		}
		System.out.println(lines.get(0).replaceAll("(\\u000D\\u000A|[\\u000A\\u000B\\u000C\\u000D\\u0085\\u2028\\u2029])", " * "));
		Files.write(Paths.get(fileName + "_output.txt"), output.getBytes());
	}
}
