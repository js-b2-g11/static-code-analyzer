package com.philips.bootcamp.helloworld;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testPrint() {
        // TODO Auto-generated method stub
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 
        System.setOut(new PrintStream(outContent));
        App hw = new App();
        hw.printMessage();
        assertEquals("Hello World!", outContent.toString());
 
    }
}
