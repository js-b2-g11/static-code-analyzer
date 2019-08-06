package com.philips.bootcamp.analyzer;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {	    	
    	try {
    		
    		if (args.length > 0) {
 
    			String filepath = args[0];    			
    			Merger vf = new Merger();
    			FileFilter filter = new FileFilter();
    			filter.cleartxtFiles("./reports", ".txt");    			
    			IssueCounter Issues = new IssueCounter();
				vf.genAndMergeFile(filepath);
				Issues.getIssueCount(filepath);
    			
    		} else {    			
    			System.out.print("No file path specified.\n");    			
    		}
    		
    	} catch(Exception e) {
    		
    		System.out.print("Exception occured - here's what I know: \n");
            e.printStackTrace();
            System.exit(-1);
    	} 
    }
}
