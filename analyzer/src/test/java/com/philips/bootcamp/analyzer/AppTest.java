package com.philips.bootcamp.analyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.Buffer;

import org.junit.Test;

import com.philips.bootcamp.utils.Values;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws InterruptedException 
     * @throws IOException 
     */

	
    @Test
    public void TestReportGeneration() throws InterruptedException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {"../analyzer"};
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
    	String filepath = "../analyzer";
    	PmdReportGenerator pmd = new PmdReportGenerator(filepath);
    	pmd.generateReport();
    	assertEquals("PMD report generated\n", outContent.toString());
    }
	
	@Test
    public void testReportGenerationForChecksytle() throws InterruptedException, IOException
    {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); 
    	System.setOut(new PrintStream(outContent));
    	String filepath = "../analyzer";
    	CheckstyleReportGenerator crg = new CheckstyleReportGenerator(filepath);
    	crg.generateReport();
    	assertEquals("Checkstyle report generated.\n", outContent.toString());
    }
	
	@Test
	public void testReportFileLineCount() throws InterruptedException, IOException
	{
		String filepath = "../analyzer";
		PmdReportGenerator pmd = new PmdReportGenerator(filepath);
		CheckstyleReportGenerator cgr = new CheckstyleReportGenerator(filepath);
		pmd.generateReport();
		cgr.generateReport();
		UnifyReport.mergeReports(pmd, cgr);	
		File f1 = new File("../analyzer/mergedReport.txt");
		int lineCount=0;
		FileReader fr = new FileReader(f1);
		BufferedReader br = new BufferedReader(fr);
		String s;
		while((s=br.readLine())!=null)
		{
			lineCount++;
		}
		fr.close();
		assertEquals(lineCount, UnifyReport.countCheckstyleReportlines + UnifyReport.countPmdReportlines + Values.DEFAULT_LINECOUNT);
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
}
