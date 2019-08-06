package com.philips.bootcamp.analyzer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import com.philips.bootcamp.utils.InputFile;

public class Merger{
    public void genAndMergeFile(String filepath) throws IOException, InterruptedException {
        if (InputFile.isValidPath(filepath)) {

            System.out.print("Valid file path detected!\n");
                           		
            PmdReportGenerator reportPmd = new PmdReportGenerator(filepath); 
			reportPmd.generateReport();			
            CheckstyleReportGenerator reportCheckstyle = new CheckstyleReportGenerator(filepath);
            reportCheckstyle.generateReport();


        } else {
            System.out.print("Invalid file path specified\n");
//            System.exit(0);
        }
    }
}
