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
	public void generateReport() throws IOException{		
		if(isValidReport()) {
			String command[] = new String[] {"cmd", "/c", "java", "-jar", checkstylePath, "-c", 
					checkstyleRuleset, this.getFilepath()};
			Runtime rt = Runtime.getRuntime();
			Process checkstyleProcess = rt.exec(command);      
            
            JavaFileGetter jfg = new JavaFileGetter();
            List<String> result = jfg.getFile(this.getFilepath());         
            
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(checkstyleProcess.getInputStream()));
            String s = null;
			while ((s = stdInput.readLine()) != null) {

				for(int i=0;i<result.size();i++) {
					File file = new File(result.get(i));					
					String fileNameWithOutExt = file.getName().replaceFirst("[.][^.]+$", "");				
					Pattern p = Pattern.compile("^.*\\b("+result.get(i).replace("\\", "\\\\")+")\\b.*$");
				    Matcher m = p.matcher(s);
				    if(m.find()) {				    	
						BufferedWriter writer = new BufferedWriter(
	                          new FileWriter("./reports/"+fileNameWithOutExt+".txt", true)  //Set true for append mode
	                      ); 
				        writer.newLine();   //Add new line
				        writer.write(m.group());
				        writer.close();
				    }
				}
			}
			System.out.println("Checkstyle report generated.");
		}
		else {		
			System.out.println("Invalid/Empty file specified!");
		}
	}
}	

	
