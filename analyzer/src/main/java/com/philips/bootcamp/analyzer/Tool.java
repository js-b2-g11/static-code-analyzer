package com.philips.bootcamp.analyzer;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Tool {
		
	private String filepath;			
	
	public Tool(String filepath) {
		this.filepath = filepath;
	}
		
	public abstract void generateReport() throws IOException;
	
	public abstract boolean isValidReport();
	
}
