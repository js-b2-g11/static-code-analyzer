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
    			ValidateFile vf = new ValidateFile();
				vf.checkFile(filepath);
    			
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
