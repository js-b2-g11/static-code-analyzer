package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.philips.bootcamp.utils.FileValidator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PmdReportGenerator extends Tool {

	private String pmdRuleset;

	public PmdReportGenerator(String filepath, String pmdRuleset) {
		super(filepath);
		this.pmdRuleset = pmdRuleset;
	}

	public static PmdReportGenerator getPmdReportObject(String filepath) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader("./../sca.properties"));
//		String filepath = p.getProperty("path");		
		String pmdRuleset = p.getProperty("pmdRuleset");
		PmdReportGenerator report = new PmdReportGenerator(filepath, pmdRuleset);
		return report;
	}

	@Override
	public void generateReport() throws IOException {
		if (isValidReport()) {
			String command[] = new String[] { "pmd.bat", "-d", this.getFilepath(), "-f", "text", "-R", pmdRuleset};
			Runtime rt = Runtime.getRuntime();

			Process pmdProcess;
			pmdProcess = rt.exec(command);

			JavaFileGetter jfg = new JavaFileGetter();
			List<String> result = jfg.getFile(this.getFilepath());

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(pmdProcess.getInputStream()));

			String s = null;
			while ((s = stdInput.readLine()) != null) {

				for (int i = 0; i < result.size(); i++) {
					File file = new File(result.get(i));
					String fileNameWithOutExt = file.getName().replaceFirst("[.][^.]+$", "");
					Pattern p = Pattern.compile("^.*\\b(" + result.get(i).replace("\\", "\\\\") + ")\\b.*$");
					Matcher m = p.matcher(s);
					if (m.find()) {
						BufferedWriter writer = new BufferedWriter(
								new FileWriter("./reports/" + fileNameWithOutExt + ".txt", true) // Set true for append
																									// mode
						);
						writer.newLine(); // Add new line
						writer.write(m.group());
						writer.close();
					}
				}
			}
			System.out.print("PMD report generated\n");
		}
		else {
			System.out.println("Invalid/Empty file specified!");
		}
	}

	@Override
	public boolean isValidReport() {
		if (this.getFilepath().equals(null) || this.getFilepath() == "")
			return false;
		return (FileValidator.isValidPath(this.getFilepath()));
	}
}