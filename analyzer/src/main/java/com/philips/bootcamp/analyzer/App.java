package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {	    	
    	try {
    		
    		if (args.length > 0) {
    			
    			String filepath = args[0];
    			
    			if (InputFile.isValidPath(filepath)) {
        			
    				System.out.println("Valid file path detected!");
    				
    				PmdReportGenerator reportPmd = new PmdReportGenerator(filepath);
    	    		
    	    		reportPmd.generateReport();
    	    		
    	    		CheckstyleReportGenerator reportCheckstyle = new CheckstyleReportGenerator(filepath);
    	    		
    	    		reportCheckstyle.generateReport();
    	    		
    	    		UnifyReport.mergeReports(reportPmd, reportCheckstyle);    	    		    	    		
    				
        		} else {
        			
        			System.out.println("Invalid file path specified");
        			System.exit(0);
        		}
    			
    		} else {
    			
    			System.out.println("No file path specified.");
    			System.exit(0);    			
    		}
    		
    	} catch(Exception e) {
    		
    		System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
            
    	} 
    }
}
