package test.java;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Test1 {

	public static void main(String[] args) throws Exception {
		/*BufferedReader reader = new BufferedReader(new FileReader("Orig.txt"));
		File output = new File("output.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();

		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			// Default - Takes Request and Response
			String pattern = "(<\\?xml version)(.*?)(</RetrieveInsuranceScoreRequestMessage>|</RetrieveInsuranceScoreResponseMessage>)";
			if (args.length > 0) {
				if (args[0] == "0") // 0 - for Request
					pattern = "(<\\?xml version)(.*?)(</RetrieveInsuranceScoreRequestMessage>)";
				else if (args[0] == "1") // 1 - for Response
					pattern = "(<\\?xml version)(.*?)(</RetrieveInsuranceScoreResponseMessage>)";
			}
			Pattern p = Pattern.compile(pattern, Pattern.DOTALL);
			Matcher m = p.matcher(stringBuilder);
			while (m.find()) {
				// System.out.println(stringBuilder.substring(m.start(), m.end()));
				System.out.println("********************** X ********************** X **********************");

				Document document = builder.parse(new InputSource(new StringReader(stringBuilder.substring(m.start(), m.end()))));

				String xPathStr = "/RetrieveInsuranceScoreResponseMessage/RetrieveInsuranceScoreResponseTransaction";
				String xmlData = (xpath.evaluate(xPathStr, document.getDocumentElement())).trim();
				System.out.println(xmlData);
				if (null != xmlData && xmlData.contains("WINDSOR")) {
					writer.write(stringBuilder.substring(m.start(), m.end()));
					writer.write(ls);
					writer.write("********************** X ********************** X **********************");
					writer.write(ls);
					writer.flush();
				}
			}

		} finally {
			reader.close();
			writer.close();
		}*/
		
		Test1 t = new Test1();
		t.ConvetLogtoXML("Orig1.txt", "FLEXCOMM", "AX1H25", "");
	}

	public boolean ConvetLogtoXML(String FileName, String ServiceName, String PolicyNumber, String RequestType)
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

		BufferedReader reader = new BufferedReader(new FileReader(FileName));
		System.out.println(reader.toString());
		String outFileName;// = PCThreadCache.getInstance().getProperty(PCConstants.FormsFolderPath) + "\\" + PolicyNumber + "_" + ServiceName + "_Output.xml";
		outFileName = "output.txt";
		File output = new File(outFileName);
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();

		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		String pattern = null;
		String strPolicyXpath = null;
		String strTemp = null;
		String xpath_timeStamp = null;
		String xpath_WholeData = null;
		String strXmlNameSpace = null;
		Element userElement = null;
		String strNameSpace = null;
		Element userElement_match = null;
		String strNameSpace_Match = null;

		Boolean status;
		/*XlsxReader sXL = XlsxReader.getInstance();
		HashMap<String,Object> updateColumnNameValues = new HashMap<String,Object>();
		HashMap<String,Object> whereConstraint = new HashMap<String,Object>();
		updateColumnNameValues.clear();
		whereConstraint.clear();
		updateColumnNameValues.put("OutPutXMLFile", outFileName);
		whereConstraint.put(PCConstants.ID,PCThreadCache.getInstance().getProperty("TCID") );// 
		status = sXL.executeUpdateQuery("XMLValidation",  updateColumnNameValues,  whereConstraint );*/

		List<String> arrTimeStamp = new ArrayList<>();

		boolean blnPolicyinLogFound = false;
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line.trim());
				stringBuilder.append(ls);
			}
			// Default - Takes Request and Response
			switch (ServiceName.toUpperCase()) {
			case "FLEXCOMM":
				//pattern = "(<\\?xml version)(.*?<tns:policyNumber>AX1RVB</tns:policyNumber>.*?)(</tns:GetCommissionRates>)";
				pattern = "(<tns:GetCommissionRates)(.*?<tns:policyNumber>AX1RVB</tns:policyNumber>.*?)(</tns:GetCommissionRates>)";
				// xpath_timeStamp = "/GetCommissionRates//policyNumber[text()='"+PolicyNumber+"']/parent::GetCommissionRates//requestTime";
				//xpath_timeStamp = "/GetCommissionRates//policyNumber/text()";
				xpath_timeStamp = "/GetCommissionRates//requestTime/text()";
				xpath_WholeData = "/GetCommissionRates";
				if (RequestType.equalsIgnoreCase("RESPONSE")) {
					// pattern = "(<\\?xml version)(.*?)(</tns:GetCommissionRates>)";
					//pattern = "(<\\?xml version)(.*?<tns:policyNumber>AX1RVB</tns:policyNumber>.*?)(</tns:GetCommissionRates>)";
					pattern = "(<tns:GetCommissionRates)(.*?<tns:policyNumber>AX1RVB</tns:policyNumber>.*?)(</tns:GetCommissionRates>)";
					strXmlNameSpace = "flexcommrates.thehartford.com/response";
				} else {
					//pattern = "(<\\?xml version)(.*?)(</tns:GetCommissionRates>)";
					pattern = "(<tns:GetCommissionRates)(.*?)(</tns:GetCommissionRates>)";
					strXmlNameSpace = "flexcommrates.thehartford.com/request";
				}
				break;
			case "OTHERS":
				break;

			}
			Pattern p = Pattern.compile(pattern, Pattern.DOTALL);

			Matcher m = p.matcher(stringBuilder);
			int intloop = 0;
			while (m.find()) {
				// System.out.println(stringBuilder.substring(m.start(), m.end()));
				try {
					//System.out.println("********************** X ********************** X **********************");
					Document document = builder.parse(new InputSource(new StringReader(stringBuilder.substring(m.start(), m.end()))));
					String xmlData = (xpath.evaluate(xpath_WholeData, document.getDocumentElement())).trim();
					String xmlData_timeStamp = (xpath.evaluate(xpath_timeStamp, document.getDocumentElement())).trim();
					/* userElement = (Element) xpath.evaluate(xpath_WholeData, document,XPathConstants.NODE);
					 strNameSpace=userElement.getAttribute("xmlns:tns");
					  System.out.println(userElement.getAttribute("xmlns:tns"));*/
					if (null != xmlData && xmlData.contains(PolicyNumber) /*&& strNameSpace.contains(strXmlNameSpace)*/) {
						System.out.println(xmlData_timeStamp);
						System.out.println(xpath.evaluate("/GetCommissionRates//requestTime/text()", document.getDocumentElement()));

						arrTimeStamp.add(xmlData_timeStamp);
						intloop++;

					}
				} catch (SAXException e) {
					//System.out.println("exception -*" + e.getMessage());
					e.printStackTrace();
				}
			}
			if (!arrTimeStamp.isEmpty()) {
				strTemp = arrTimeStamp.get(0).toString();
				for (int i = 1; i < arrTimeStamp.size(); i++) {
					strTemp = ComparetiemStamp(strTemp, arrTimeStamp.get(i).toString());
				}
				try {
					Matcher m1 = p.matcher(stringBuilder);
					while (m1.find()) {
						Document document1 = builder.parse(new InputSource(new StringReader(stringBuilder.substring(m1.start(), m1.end()))));
						String xmlData1 = (xpath.evaluate(xpath_WholeData, document1.getDocumentElement())).trim();
						System.out.println(xmlData1);
						userElement_match = (Element) xpath.evaluate(xpath_WholeData, document1, XPathConstants.NODE);
						strNameSpace_Match = userElement_match.getAttribute("xmlns:tns");
						if (null != xmlData1 && xmlData1.contains(PolicyNumber) && xmlData1.contains(strTemp) && strNameSpace_Match.contains(strXmlNameSpace)) {
							blnPolicyinLogFound = true;
							writer.write("<?xml version=\"1.0\"?>");
							writer.write(stringBuilder.substring(m1.start(), m1.end()));
							writer.write(ls);
							writer.write(ls);
							writer.flush();
						}

					}
				} catch (SAXException e) {
					System.out.println("2nd while exception -*" + e.getMessage());
				}

			}

		} finally {
			reader.close();
			writer.close();
		}
		return blnPolicyinLogFound;

	}

	public String ComparetiemStamp(String time1, String time2) {
		// String text = "2018-01-09 02:27:24.000334";
		Timestamp ts = Timestamp.valueOf(time1);
		// String text1 = "2018-01-09 02:27:06.000055";
		Timestamp ts1 = Timestamp.valueOf(time2);
		System.out.println(ts1);
		String latesttime;
		System.out.println("Comparing timestamp" + time1 + "___" + time2);
		if (ts.after(ts1)) {
			System.out.println("ts" + ts + " is latest");
			latesttime = time1;
		} else {
			System.out.println("ts1" + ts1 + " is latest");
			latesttime = time2;
		}
		return latesttime;
	}
}
