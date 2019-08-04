package com.philips.bootcamp.analyzer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import com.philips.bootcamp.utils.FileValidator;

public class FileMerger{
    public void genAndMergeFile(String filepath) throws IOException, InterruptedException {
        
    	if (FileValidator.isValidPath(filepath)) {

            System.out.print("Valid file path detected!\n");
                           		
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
            
            UnifyReport.mergeReports(reportPmd, reportCheckstyle);

        } else {

            System.out.print("Invalid file path specified\n");

        }
    }
}
