package com.philips.bootcamp.analyzer;

import java.sql.Timestamp;
import java.util.Date;

public class MergeFile{
    public void checkFileAndMerge(String filepath) {
        if (InputFile.isValidPath(filepath)) {

            System.out.println("Valid file path detected!");
                           		
			Timestamp pmdStartTime, pmdEndTime, checkstyleStartTime, checkstyleEndTime;
			
            PmdReportGenerator reportPmd = new PmdReportGenerator(filepath); 
            
			pmdStartTime = new Timestamp(new Date().getTime());
			
			reportPmd.setReportStartTime(pmdStartTime);            
			reportPmd.generateReport();
            
			pmdEndTime = new Timestamp(new Date().getTime());                      		
			reportPmd.setReportEndTime(pmdEndTime);
			
            CheckstyleReportGenerator reportCheckstyle = new CheckstyleReportGenerator(filepath);
                        
            checkstyleStartTime = new Timestamp(new Date().getTime());
            reportCheckstyle.setReportStartTime(checkstyleStartTime);
            reportCheckstyle.generateReport();
            
            checkstyleEndTime = new Timestamp(new Date().getTime());
            reportCheckstyle.setReportEndTime(checkstyleEndTime);
            Timestamp[] reportTimeStamps = {pmdStartTime, pmdEndTime, checkstyleStartTime, checkstyleEndTime};
            
            UnifyReport.mergeReports(reportPmd, reportCheckstyle);

        } else {

            System.out.print("Invalid file path specified\n");
//            System.exit(0);
        }
    }
}
