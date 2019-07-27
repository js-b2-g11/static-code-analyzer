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
    		
    		Process pmdProcess;
    		Process checkstyleProcess;
    		
    		pmdProcess = Runtime.getRuntime().exec("pmd.bat -d C:\\Users\\320053825\\Documents\\static-code-analyzer\\helloworld\\src\\main\\java\\com\\philips\\bootcamp\\helloworld\\App.java -f text " +  
                           "-R category/java/codestyle.xml -r reportPMD.txt");    		
    	 	
    		pmdProcess.waitFor();
    		
			checkstyleProcess = Runtime.getRuntime().exec("cmd /c java -jar C:\\Users\\320053825\\Downloads\\checkstyle-8.22-all.jar -c /google_checks.xml C:\\Users\\320053825\\Documents\\static-code-analyzer\\helloworld\\src\\main\\java\\com\\philips\\bootcamp\\helloworld\\App.java > reportCheckstyle.txt");
    		
			checkstyleProcess.waitFor();
    		
    		PrintWriter pw = new PrintWriter("file3.txt");                 
    		    		
            // BufferedReader object for file1.txt 
            BufferedReader br1 = new BufferedReader(new FileReader("reportPMD.txt")); 
            BufferedReader br2 = new BufferedReader(new FileReader("reportCheckstyle.txt"));              
              
            String line1 = br1.readLine(); 
            String line2 = br2.readLine();                       
            
            // loop to copy lines of  
            // file1.txt and file2.txt  
            // to  file3.txt alternatively
            pw.println("------------------------------------------------------------------");
            pw.println("\t\t\t\t\t\t\tPMD Report");
            pw.println("------------------------------------------------------------------");
            while (line1 != null) 
            {             	
                if(line1 != null) 
                {                 	
                    pw.println(line1); 
                    line1 = br1.readLine(); 
                }
            }
            pw.println("------------------------------------------------------------------");
            pw.println("\t\t\t\t\t\t\tCheckstyle Report");
            pw.println("------------------------------------------------------------------");
            while (line2 != null)
            {
                if(line2 != null) 
                {                 	
                    pw.println(line2); 
                    line2 = br2.readLine(); 
                } 
            } 
          
            pw.flush(); 
              
            // closing resources 
            br1.close(); 
            br2.close(); 
            pw.close(); 
            
            System.out.println("Merged file1.txt and file2.txt alternatively into file3.txt"); 
    		
    	} catch(IOException e) {
    		System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
    	} 
    }
}
