package com.philips.bootcamp.utils;

import java.io.File;

public class InputFile {	
	
	public static boolean isValidPath(String filepath) {		
		
		File tempFile = new File(filepath);
		
		boolean exists = tempFile.exists();
		
		if (exists) {
			return true;
		} else {
			return false;
		}		
	}
	
}
