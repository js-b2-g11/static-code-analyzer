package com.philips.bootcamp.analyzer;

public class App {
	public static void main(String[] args) throws InterruptedException {
		try {
			String configFilePath = "./../sca.properties";
			Merger mergeObj = new Merger();
			TxtFileCleaner cleaner = new TxtFileCleaner();
			cleaner.cleartxtFiles("./reports", ".txt");			
			mergeObj.genAndMergeFile(configFilePath);
			IssueCounter issueCounter = new IssueCounter();
			issueCounter.getIssueCount("./reports");

		} catch (Exception e) {

			System.out.print("Exception occured - here's what I know: \n");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
