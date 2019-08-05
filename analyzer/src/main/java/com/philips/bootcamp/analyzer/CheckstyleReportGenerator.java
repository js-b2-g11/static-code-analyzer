package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Properties;

import com.philips.bootcamp.utils.FileValidator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckstyleReportGenerator extends Tool{
	
	private String checkstylePath;
	private String checkstyleRuleset;
	private String checkstyleOutputFile;
	private Timestamp reportStartTime;
	private Timestamp reportEndTime;	
	
	public CheckstyleReportGenerator(String filepath, String checkstylePath, String checkstyleRuleset,
			String checkstyleOutputFile) {
		super(filepath.trim());
		this.checkstylePath = checkstylePath;
		this.checkstyleRuleset = checkstyleRuleset;
		this.checkstyleOutputFile = checkstyleOutputFile;		
	}		
	
	public static CheckstyleReportGenerator getCheckstyleReportObject (String configFile) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(configFile));		
		String filepath = p.getProperty("path");
		String checkstylePath = p.getProperty("checkstylePath");
		String checkstyleRuleset = p.getProperty("checkstyleRuleset");
		String checkstyleOutputFile = p.getProperty("checkstyleOutputFile");
		CheckstyleReportGenerator report = new CheckstyleReportGenerator(filepath, checkstylePath, 
				checkstyleRuleset, checkstyleOutputFile);						
		return report;
	}
	
	public void generateCompleteReport() {				
		if (isValidReport()) {
			String command[] = new String[] {"cmd", "/c", "java", "-jar", checkstylePath, "-c", 
					checkstyleRuleset, this.getFilepath(), ">", checkstyleOutputFile};
			Runtime rt = Runtime.getRuntime();
			try {
				Process p = rt.exec(command);
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        while (true) {
		            line = r.readLine();
		            if (line == null) { break; }
		            System.out.println(line);
		        }
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Checkstyle Report(Complete) generated");
		} else {
			System.out.println("Invalid/Empty file specified!");
		}
	}

	@Override
	public boolean isValidReport() {
		if (this.getFilepath().equals(null) || this.getFilepath()=="")
			return false;
		return (FileValidator.isValidPath(this.getFilepath()));
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException {
		String propertiesFile = "./../sca.properties";				
		CheckstyleReportGenerator reportCheckstyle = CheckstyleReportGenerator.getCheckstyleReportObject(propertiesFile);
		reportCheckstyle.generateReport();
	}

	@Override
	public void generateReport() {
		// TODO Auto-generated method stub
		
	}
	
}

	
