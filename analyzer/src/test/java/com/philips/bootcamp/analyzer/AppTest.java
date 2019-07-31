package com.philips.bootcamp.analyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

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
    public void positiveTestcase() throws InterruptedException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {"C:/BootCampProject/static-code-analyzer/analyzer"};
           App.main(args);
           assertEquals("Valid file path detected!\n"
           		+ "PMD report generated\n"
           		+ "Checkstyle report generated.\n"
           		+ "Merged pmd report and checkstyle report successfully\n", outContent.toString());
    }
    
    @Test
    public void negativeTestcaseInvalidDir() throws InterruptedException, IOException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {"C:/BootCampProject/static-code-analyzer/analyzer1"};
           App.main(args);
           assertEquals("Invalid file path specified\n", outContent.toString());
    }
    
    @Test
    public void negativeTestcaseNoFile() throws InterruptedException, IOException
    {
    	   final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
           System.setOut(new PrintStream(outContent));
           String[] args = {};
           App.main(args);
           assertEquals("No file path specified.\n", outContent.toString());
    }
}
