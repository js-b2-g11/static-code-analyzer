package com.philips.bootcamp.analyzer;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {	    	
    	try {
    		
    		if (args.length > 0) {
 
    			String filepath = args[0];
    			filepath = filepath.replace("\\", "\\\\");
    			System.out.println(filepath);
    			Merger vf = new Merger();
    			IssueCounter Issues = new IssueCounter();
    			
    			
				vf.genAndMergeFile(filepath);
				
				Issues.getIssueCount(filepath);
//				EmailReport.executeSendEmail();
    			
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
