package test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class JsonToPojoConvertor {

	public static void main(String[] args) {
		String packageName = "com.casebank.rondo.solr.bean.result";
		File inputJson = new File("." + File.separator + "defectJson.json");
		File outputPojoDirectory = new File(
				"." + File.separator + "convertedPojo");
		outputPojoDirectory.mkdirs();
		try {
			new JsonToPojoConvertor().convert2JSON(inputJson.toURI().toURL(),
					outputPojoDirectory, packageName,
					inputJson.getName().replace(".json", ""));
		} catch (IOException e) {
			System.out.println("Encountered issue while converting to pojo: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public void convert2JSON(URL inputJson, File outputPojoDirectory,
			String packageName, String className) throws IOException {
		className = "Defect";
		JCodeModel codeModel = new JCodeModel();
		URL source = inputJson;
		GenerationConfig config = new DefaultGenerationConfig() {
			@Override
			public boolean isGenerateBuilders() { // set config option by
													// overriding method
				return false;
			}

			@Override
			public SourceType getSourceType() {
				return SourceType.JSON;
			}

			@Override
			public String getClassNameSuffix() {
				return "SolrBean";
			}

		};
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config,
				new Jackson2Annotator(config), new SchemaStore()),
				new SchemaGenerator());
		mapper.generate(codeModel, className, packageName, source);
		codeModel.build(outputPojoDirectory);
	}
}
