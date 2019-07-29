package com.philips.bootcamp.analyzer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PmdReportGenerator {
	
	private String pmd;
	private String filepath;
    private String format;
    private String ruleset;
    private String outputFile;
    
    public PmdReportGenerator(String filepath) {
    	this.filepath = filepath;
    	this.pmd = "pmd.bat -d ";
    	this.format = "text";
    	this.ruleset = "category/java/codestyle.xml";
    	this.outputFile = "reportPmd.txt";
    }
        
	public void generateReport(){		
        try {
            String executePmdString = this.pmd + this.filepath + " -f "+ this.format +" -R " + this.ruleset;
            Process pmdProcess = Runtime.getRuntime().exec(executePmdString + " -r " + this.outputFile);
            
            pmdProcess.waitFor();
            
            System.out.println("PMD report Generated");
            
        } catch (Exception e) {
        	e.printStackTrace(); 
            System.out.println("error occured");             
        }
    }
}