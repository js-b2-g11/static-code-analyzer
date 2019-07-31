package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import com.philips.bootcamp.utils.Values;

public class UnifyReport {
	
	public static void mergeReports(PmdReportGenerator pmdReport, CheckstyleReportGenerator checkstyleReport, Timestamp[] reportTimeStamps) {
		try {
			PrintWriter pw = new PrintWriter(Values.FINAL_OUTPUT_FILE);                 
			
			// BufferedReader object for pmdReport and checkstyleReport 
			BufferedReader br1 = new BufferedReader(new FileReader(pmdReport.getOutputFile())); 
			BufferedReader br2 = new BufferedReader(new FileReader(checkstyleReport.getOutputFile()));              
			  
			String line1 = br1.readLine(); 
			String line2 = br2.readLine();                       
						
			
			// 2 loops to copy lines of both reports into a single file 			
			
			pw.println("-----------------------------------------------------------------------------------------------");
			pw.println("\t\t\t\t\t\t\t\t\tPMD Report");
			pw.println("-----------------------------------------------------------------------------------------------\n\n");			
			pw.println("PMD report start time: " + reportTimeStamps[0] + "\n");
			while (line1 != null) 
			{             	
			    if(line1 != null) 
			    {                 	
			        pw.println(line1); 
			        line1 = br1.readLine(); 
			    }
			}			
			pw.println("PMD report end time: " + reportTimeStamps[1] + "\n");
			pw.println("-----------------------------------------------------------------------------------------------");
			pw.println("\t\t\t\t\t\t\t\t\tCheckstyle Report");
			pw.println("-----------------------------------------------------------------------------------------------\n\n");
			pw.println("Checkstyle report start time: " + reportTimeStamps[2] + "\n");
			while (line2 != null)
			{
			    if(line2 != null) 
			    {                 	
			        pw.println(line2); 
			        line2 = br2.readLine(); 
			    } 
			} 
			pw.println("Checkstyle report end time: " + reportTimeStamps[3] + "\n");
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
