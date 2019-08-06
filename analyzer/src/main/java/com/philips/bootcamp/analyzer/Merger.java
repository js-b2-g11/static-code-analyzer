package com.philips.bootcamp.analyzer;

import java.io.IOException;

public class Merger{
    public void genAndMergeFile(String configFilePath) throws IOException, InterruptedException {                                	
            PmdReportGenerator reportPmd = PmdReportGenerator.getPmdReportObject(configFilePath);
			reportPmd.generateReport();			
            CheckstyleReportGenerator reportCheckstyle = CheckstyleReportGenerator.getCheckstyleReportObject(configFilePath);
            reportCheckstyle.generateReport();
    }
}
