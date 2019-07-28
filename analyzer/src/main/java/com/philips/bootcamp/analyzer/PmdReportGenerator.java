package com.philips.bootcamp.analyzer;

public class PmdReportGenerator {
	
	private String pmd;
	private String filepath;
    private String format;
    private String ruleset;
    private String outputFile;
    
    public PmdReportGenerator(String filepath) {
    	this.filepath = filepath;
    	this.pmd = "pmd.bat -d ";
    	this.format = "text";
    	this.ruleset = "category/java/codestyle.xml";
    	this.outputFile = "reportPmd.txt";
    }
    
    public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getPmd() {
		return pmd;
	}

	public void setPmd(String pmd) {
		this.pmd = pmd;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRuleset() {
		return ruleset;
	}

	public void setRuleset(String ruleset) {
		this.ruleset = ruleset;
	}

	public void generateReport(){		
        try {
            String executePmdString = this.pmd + this.filepath + " -f "+ this.format +" -R " + this.ruleset;
            Process pmdProcess = Runtime.getRuntime().exec(executePmdString + " -r " + this.outputFile);
            
            pmdProcess.waitFor();
            
            System.out.println("PMD report Generated");
            
        } catch (Exception e) {
        	e.printStackTrace(); 
            System.out.println("error occured");             
        }
    }
}