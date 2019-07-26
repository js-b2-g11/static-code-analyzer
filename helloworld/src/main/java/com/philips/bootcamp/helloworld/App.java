package com.philips.bootcamp.helloworld;

/**
 * Hello world!
 *
 */
public class App 
{
    // private int anotherUselessVariable = 303;        
    public void printMessage() {
    	System.out.print("Hello World!");
    }
    
    public static void main(String args[]) {
    	App obj = new App();
    	obj.printMessage();
    }
}
