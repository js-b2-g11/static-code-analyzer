package com.philips.bootcamp.analyzer;

import java.io.IOException;
import java.sql.Timestamp;

import com.philips.bootcamp.utils.Values;

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
    private Timestamp reportStartTime;
    private Timestamp reportEndTime;
    
    public PmdReportGenerator(String filepath) {
    	this.filepath = filepath;
    	this.pmd = Values.PMD_CMD;
    	this.format = "text";
    	this.ruleset = Values.PMD_RULESET;
    	this.outputFile = Values.PMD_OUTPUT_FILE;
    }
        
	public void generateReport() throws IOException, InterruptedException{		
		
		if(filepath == null || filepath.trim().length() == 0) {
			try {
	            throw new Exception("Filepath is not specified enter a valid filepath");
	        } catch (Exception e) {
//	            e.printStackTrace();
	            System.out.print("Filepath not specified\n");
	        } 
		}
		else 
        {
            String executePmdString = pmd + filepath + " -f "+ format +" -R " + ruleset;
            Process pmdProcess = Runtime.getRuntime().exec(executePmdString + " -r " + outputFile);
            
            pmdProcess.waitFor();
            
            System.out.print("PMD report generated\n");
        }
	}
}