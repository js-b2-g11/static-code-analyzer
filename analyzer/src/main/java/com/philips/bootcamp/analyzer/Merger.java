package com.philips.bootcamp.analyzer;

import java.io.IOException;

import com.philips.bootcamp.utils.FileValidator;

public class Merger{
    public void genAndMergeFile(String filepath) throws IOException, InterruptedException {                                	
            if(FileValidator.isValidPath(filepath)) {
            	
            System.out.print("Valid file path detected!\n");	
    		PmdReportGenerator reportPmd = PmdReportGenerator.getPmdReportObject(filepath);
			reportPmd.generateReport();			
            CheckstyleReportGenerator reportCheckstyle = CheckstyleReportGenerator.getCheckstyleReportObject(filepath);
            reportCheckstyle.generateReport();
            }
    else {
    	System.out.println("Invalid file path specified");
    }
    }
}
