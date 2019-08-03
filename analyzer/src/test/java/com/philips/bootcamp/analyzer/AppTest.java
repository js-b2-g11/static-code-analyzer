package com.philips.bootcamp.analyzer;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;

import com.philips.bootcamp.utils.Values;

public class AppTest 
{	
    @Test
    public void testReportGeneration() throws InterruptedException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {"../helloworld"};
           App.main(args);
           assertEquals("Valid file path detected!\n"
           		+ "PMD report generated\n"
           		+ "Checkstyle report generated.\n"
           		+ "Merged pmd report and checkstyle report successfully\n", outContent.toString());
    }
    
	@Test
    public void testReportGenerationForPMD() throws InterruptedException, IOException
    {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	String filepath = "../helloworld";
    	PmdReportGenerator pmd = new PmdReportGenerator(filepath);
    	pmd.generateReport();
    	assertEquals("PMD report generated\n", outContent.toString());
    }
	
	@Test
    public void testReportGenerationForChecksytle() throws InterruptedException, IOException
    {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); 
    	System.setOut(new PrintStream(outContent));
    	String filepath = "../helloworld";
    	CheckstyleReportGenerator crg = new CheckstyleReportGenerator(filepath);
    	crg.generateReport();
    	assertEquals("Checkstyle report generated.\n", outContent.toString());
    }
	
//	@Ignore("Run this testcase after building the package, Comment this code before testing")
	@Test
	public void testReportFileLineCount() throws InterruptedException, IOException
	{
		String filepath = "../helloworld";
		PmdReportGenerator pmd = new PmdReportGenerator(filepath);
		CheckstyleReportGenerator cgr = new CheckstyleReportGenerator(filepath);
		pmd.generateReport();
		cgr.generateReport();
		
		ReportMerger.mergeReports(pmd,cgr);	
		File f1 = new File("../analyzer/mergedReport.txt");
		int lineCount=0;
		FileReader fr = new FileReader(f1);
		BufferedReader br = new BufferedReader(fr);
		
		while((br.readLine())!=null)
		{
			lineCount++;
		}
		fr.close();
		assertEquals(lineCount, ReportMerger.countCheckstyleReportlines + ReportMerger.countPmdReportlines + Values.DEFAULT_LINECOUNT);
	}
	
    @Test
    public void invalidDir() throws InterruptedException, IOException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {"../analyzer1"};
           App.main(args);
           assertEquals("Invalid file path specified\n", outContent.toString());
    }
    
    @Test
    public void noFilepath() throws InterruptedException, IOException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {};
           App.main(args);
           assertEquals("No file path specified.\n", outContent.toString());
    }
    
    @Test
    public void noFilepathforPmd() throws IOException, InterruptedException {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	String filepath = "";
    	PmdReportGenerator pmd = new PmdReportGenerator(filepath);
    	pmd.generateReport();
    	assertEquals("Filepath not specified or incorrect filepath\n",outContent.toString());
    }
    
    @Test
    public void noFilepathforCheckstyle() throws IOException, InterruptedException {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	String filepath = "";
    	CheckstyleReportGenerator crg = new CheckstyleReportGenerator(filepath);
    	crg.generateReport();
    	assertEquals("Filepath not specified or incorrect filepath\n",outContent.toString());
    }
}
