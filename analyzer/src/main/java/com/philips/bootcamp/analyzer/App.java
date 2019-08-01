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
    			MergeFile vf = new MergeFile();
				vf.genAndMergeFile(filepath);
				EmailReport.executeSendEmail();
    			
    		} else {
    			
    			System.out.print("No file path specified.\n");
//    			System.exit(0);    			
    		}
    		
    	} catch(Exception e) {
    		
    		System.out.print("exception happened - here's what I know: \n");
            e.printStackTrace();
            System.exit(-1);
    	} 
    }
}
