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
    //Positive Testcsae	
    @Test
    public void testPositve() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        App helloWorldObjPos = new App();
        helloWorldObjPos.printMessage();
        assertEquals("Hello World!", outContent.toString());
    }
}
