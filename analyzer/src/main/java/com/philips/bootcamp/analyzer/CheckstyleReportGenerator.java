package com.philips.bootcamp.analyzer;

import java.io.IOException;
import java.sql.Timestamp;

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
	private Timestamp reportStartTime;
	private Timestamp reportEndTime;

	public CheckstyleReportGenerator(String filepath) {		
		this.filepath = filepath;		
		this.cmdString = Values.CHECKSTYLE_CMD;
		this.checkstyleJarpath = Values.CHECKSTYLE_PATH;
		this.rulesetCheckstyle = Values.CHECKSTYLE_RULESET;
		this.outputFile = Values.CHECKSTYLE_OUTPUT_FILE;
	}	
	
	public void generateReport() throws IOException, InterruptedException {
		
		if(filepath == null || filepath.trim().length() == 0) {
			try {
	            throw new Exception("Filepath is not specified enter a valid filepath");
	        } catch (Exception e) {
//	            e.printStackTrace();
	            System.out.print("Filepath not specified\n");
	        } 
		}
		else {		
			String executeCheckstyleString = checkstyleJarpath + " -c "+ rulesetCheckstyle + filepath;
			Process checkstyleProcess = Runtime.getRuntime().exec(cmdString + executeCheckstyleString + " > " + outputFile);
            checkstyleProcess.waitFor();               
            System.out.print("Checkstyle report generated.\n");
		}
	}
}