package com.philips.bootcamp.analyzer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckstyleReportGenerator {
	
	private String cmdString;
	private String filepath;
	private String checkstyleJarpath;
	private String rulesetCheckstyle;
	private String outputFile;
	

	public CheckstyleReportGenerator(String filepath) {		
		this.filepath = filepath;
		this.cmdString = "cmd /c java -jar ";
		this.checkstyleJarpath = "C:/Users/320053825/Downloads/checkstyle-8.22-all.jar";
		this.rulesetCheckstyle = "/google_checks.xml ";
		this.outputFile = "reportCheckStyle.txt";
	}	
	
	public void generateReport() {
		try {		
			String executeCheckstyleString = this.checkstyleJarpath + " -c "+ this.rulesetCheckstyle + this.filepath;
            Process checkstyleProcess = Runtime.getRuntime().exec(this.cmdString + executeCheckstyleString + " > " + this.outputFile);
            
            checkstyleProcess.waitFor();
            
            System.out.println("Checkstyle report generated.");
		}
		catch(Exception e){			
			System.out.println("error occured"); 
	        e.printStackTrace(); 		
		}
	}	
}