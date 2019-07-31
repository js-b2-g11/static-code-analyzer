package com.philips.bootcamp.analyzer;

import com.philips.bootcamp.utils.Values;

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
		this.cmdString = Values.CHECKSTYLE_CMD;
		this.checkstyleJarpath = Values.CHECKSTYLE_PATH;
		this.rulesetCheckstyle = Values.CHECKSTYLE_RULESET;
		this.outputFile = Values.CHECKSTYLE_OUTPUT_FILE;
	}	
	
	public void generateReport() {
		try {		
			String executeCheckstyleString = checkstyleJarpath + " -c "+ rulesetCheckstyle + filepath;
            Process checkstyleProcess = Runtime.getRuntime().exec(cmdString + executeCheckstyleString + " > " + outputFile);
            checkstyleProcess.waitFor();   
            System.out.print("Checkstyle report generated.\n");
		}
		catch(Exception e){			
			System.out.print("error occured\n"); 
	        e.printStackTrace(); 		
		}
	}	
}