package com.philips.bootcamp.analyzer;

public class ValidateAndMergeFile{
    public void checkFile(String filepath) {
        if (InputFile.isValidPath(filepath)) {

            System.out.print("Valid file path detected!\n");
            PmdReportGenerator reportPmd = new PmdReportGenerator(filepath);
            reportPmd.generateReport();
            CheckstyleReportGenerator reportCheckstyle = new CheckstyleReportGenerator(filepath);
            reportCheckstyle.generateReport();
            UnifyReport.mergeReports(reportPmd, reportCheckstyle);

        } else {

            System.out.print("Invalid file path specified\n");
//            System.exit(0);
        }
    }
}
