package com.philips.bootcamp.analyzer;

import java.sql.Timestamp;
import java.util.Date;

public class ValidateAndMergeFile{
    public void checkFile(String filepath) {
        if (InputFile.isValidPath(filepath)) {

            System.out.println("Valid file path detected!");
                           		
			Timestamp pmdStartTime, pmdEndTime, checkstyleStartTime, checkstyleEndTime;
			
            PmdReportGenerator reportPmd = new PmdReportGenerator(filepath); 
            
			pmdStartTime = new Timestamp(new Date().getTime());			
            System.out.println(pmdStartTime);
			reportPmd.generateReport();
            
			pmdEndTime = new Timestamp(new Date().getTime());                      
			System.out.println(pmdEndTime);
			
            CheckstyleReportGenerator reportCheckstyle = new CheckstyleReportGenerator(filepath);
                        
            checkstyleStartTime = new Timestamp(new Date().getTime());
            System.out.println(checkstyleStartTime);
            reportCheckstyle.generateReport();
            
            checkstyleEndTime = new Timestamp(new Date().getTime());
            System.out.println(checkstyleEndTime);
            Timestamp[] reportTimeStamps = {pmdStartTime, pmdEndTime, checkstyleStartTime, checkstyleEndTime};
            
            UnifyReport.mergeReports(reportPmd, reportCheckstyle, reportTimeStamps);

        } else {

            System.out.println("Invalid file path specified");
            System.exit(0);
        }
    }
}
