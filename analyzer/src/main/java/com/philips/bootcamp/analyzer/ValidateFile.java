package com.philips.bootcamp.analyzer;

public class ValidateFile{
    public void checkFile(String filepath) {
        if (InputFile.isValidPath(filepath)) {

            System.out.println("Valid file path detected!");
            PmdReportGenerator reportPmd = new PmdReportGenerator(filepath);
            reportPmd.generateReport();
            CheckstyleReportGenerator reportCheckstyle = new CheckstyleReportGenerator(filepath);
            reportCheckstyle.generateReport();
            UnifyReport.mergeReports(reportPmd, reportCheckstyle);

        } else {

            System.out.println("Invalid file path specified");
            System.exit(0);
        }
    }
}
