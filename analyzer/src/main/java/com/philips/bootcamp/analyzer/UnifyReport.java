package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UnifyReport {
	
	public static void mergeReports(PmdReportGenerator pmdReport, CheckstyleReportGenerator checkstyleReport) {
		try {
			PrintWriter pw = new PrintWriter("mergedReport.txt");                 
			
			// BufferedReader object for pmdReport and checkstyleReport 
			BufferedReader br1 = new BufferedReader(new FileReader(pmdReport.getOutputFile())); 
			BufferedReader br2 = new BufferedReader(new FileReader(checkstyleReport.getOutputFile()));              
			  
			String line1 = br1.readLine(); 
			String line2 = br2.readLine();                       
			
			// 2 loops to copy lines of both reports into a single file 			
			
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
			
			System.out.println("Merged pmd report and checkstyle report successfully");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	
	}
	
}
