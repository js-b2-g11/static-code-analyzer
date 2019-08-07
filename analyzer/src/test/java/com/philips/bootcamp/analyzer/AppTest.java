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

import org.junit.Ignore;
import org.junit.Test;

import com.philips.bootcamp.utils.Values;

/**
 * Unit tests for SCA.
 */
public class AppTest 
{

	@Test
    public void testReportGeneration() throws InterruptedException, IOException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {"./../helloworld"};
           App.main(args);
           assertEquals("Valid file path detected!\n"
           		+ "PMD report generated\n"
           		+ "Checkstyle report generated.\n", outContent.toString());
    }
    
	@Test
    public void testReportGenerationForPMD() throws InterruptedException, IOException
    {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	String filepath = "./../helloworld";
    	PmdReportGenerator pmd = new PmdReportGenerator(filepath, 
    			"category/java/codestyle.xml");
    	pmd.generateReport();
    	assertEquals("PMD report generated\n", outContent.toString());
    }
	
	@Test
    public void testReportGenerationForCheckstyle() throws InterruptedException, IOException
    {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); 
    	System.setOut(new PrintStream(outContent));
    	String filepath = "./../helloworld";
    	CheckstyleReportGenerator crg = new CheckstyleReportGenerator(filepath, "C:/Checkstyle/checkstyle-8.22-all.jar", 
    			"/google_checks.xml");
    	crg.generateReport();
    	assertEquals("Checkstyle report generated.\n", outContent.toString());
    }
	
	
    @Test
    public void invalidDir() throws InterruptedException, IOException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {"C:\\BootCampProject\\static-code-analyzer\\nothelloworld"};
           App.main(args);
           assertEquals("Invalid file path specified"+System.getProperty("line.separator"), outContent.toString());
    }
    
    @Test
    public void noFilepath() throws InterruptedException, IOException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {};
           App.main(args);
           assertEquals("No file path specified\n", outContent.toString());
    } 
    
    @Test
    public void noFilepathforPmd() throws IOException, InterruptedException {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	String filepath = "";
    	PmdReportGenerator pmd = new PmdReportGenerator(filepath, 
    			"category/java/codestyle.xml");
    	pmd.generateReport();
    	assertEquals("Invalid/Empty file specified!"+System.getProperty("line.separator"), outContent.toString());
    } 
    
    @Test
    public void noFilepathforCheckstyle() throws IOException, InterruptedException {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	String filepath = "";
    	CheckstyleReportGenerator crg = new CheckstyleReportGenerator(filepath, "C:/Checkstyle/checkstyle-8.22-all.jar", 
    			"/google_checks.xml");
    	crg.generateReport();
    	assertEquals("Invalid/Empty file specified!"+System.getProperty("line.separator"), outContent.toString());
    }
    
    @Test
    public void invalidFilepathforPmd() throws IOException, InterruptedException {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	String filepath = "/nothelloworld";
    	PmdReportGenerator pmd = new PmdReportGenerator(filepath, 
    			"category/java/codestyle.xml");
    	pmd.generateReport();
    	assertEquals("Invalid/Empty file specified!"+System.getProperty("line.separator"), outContent.toString());
    } 
    
    @Test
    public void invalidFilepathforCheckstyle() throws IOException, InterruptedException {
    	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outContent));
    	String filepath = "/nothelloworld";
    	CheckstyleReportGenerator crg = new CheckstyleReportGenerator(filepath, "C:/Checkstyle/checkstyle-8.22-all.jar", 
    			"/google_checks.xml");
    	crg.generateReport();
    	assertEquals("Invalid/Empty file specified!"+System.getProperty("line.separator"), outContent.toString());
    }
    
}
