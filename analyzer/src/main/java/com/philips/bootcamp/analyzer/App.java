package com.philips.bootcamp.analyzer;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws InterruptedException, IOException {
			
//			String configFilePath = "./../sca.properties";
		if(args.length>0) {
			
			String filepath = args[0];
			filepath = filepath.replace("\\", "\\\\");
			System.out.println(filepath);
			Merger mergeObj = new Merger();
			TxtFileCleaner cleaner = new TxtFileCleaner();
			cleaner.cleartxtFiles("./reports", ".txt");			
			mergeObj.genAndMergeFile(filepath);
			IssueCounter issueCounter = new IssueCounter();
			issueCounter.getIssueCount("./reports");
		}
		else {
			System.out.print("No file path specified\n");
			}	
		}		
}
