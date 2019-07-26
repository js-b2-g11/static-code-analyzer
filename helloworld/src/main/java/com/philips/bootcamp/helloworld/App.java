package com.philips.bootcamp.helloworld;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    // private int anotherUselessVariable = 303;        
    public void printMessage() {
    	System.out.print("Hello World!");
    }
    
    public static void main(String args[]) {
//    	App obj = new App();
//    	obj.printMessage();
    	
    	String s = null;
    	
    	try {
    		Process p = Runtime.getRuntime().exec("cmd /c pmd -d App.java -f text -R ../../../../rulesets/basic.xml");
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            
            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }    		
    	} catch(IOException e) {
    		System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
    	}    	        	
    }
}
