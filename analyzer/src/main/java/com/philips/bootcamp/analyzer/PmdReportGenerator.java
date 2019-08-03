package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.philips.bootcamp.utils.InputFile;
import com.philips.bootcamp.utils.Values;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PmdReportGenerator {
	
	private String pmd;
	private String filepath;
    private String format;
    private String ruleset;
    private String outputFile;
    private Timestamp reportStartTime;
    private Timestamp reportEndTime;
    
    public PmdReportGenerator(String filepath) {
    	this.filepath = filepath;
    	this.pmd = Values.PMD_CMD;
    	this.format = "text";
    	this.ruleset = Values.PMD_RULESET;
    	this.outputFile = Values.PMD_OUTPUT_FILE;
    }
        
	public void generateReport() throws IOException, InterruptedException{		
		
		int filepathLength = filepath.trim().length();
		if((filepath == null || filepathLength == 0) && InputFile.isValidPath(filepath)) {
			System.out.print("Filepath not specified or incorrect filepath\n");
		}
		else 
        {
            String executePmdString = pmd + filepath + " -f "+ format +" -R " + ruleset;
            Runtime rt=Runtime.getRuntime();
         
            Process pmdProcess = rt.exec(executePmdString);
            
            JavaFileGetter jfg = new JavaFileGetter();
            List<String> result = jfg.getFile(filepath);
            
            BufferedReader stdInput = new BufferedReader(new 
       		     InputStreamReader(pmdProcess.getInputStream()));
            
            String s = null;
			while ((s = stdInput.readLine()) != null) {

				for(int i=0;i<result.size();i++) {

			    Pattern p = Pattern.compile("^.*\\b("+result.get(i).replace("\\", "\\\\")+")\\b.*$");
			    Matcher m = p.matcher(s);
			    if(m.find()) {
			    	String[] arr = (m.group(1).split(".java"));
//			    	System.out.println(arr[1]);
			        BufferedWriter writer = new BufferedWriter(
                            new FileWriter(filepath+arr[1].replace("\\", "_")+".txt", true)  //Set true for append mode
                        ); 
			        writer.newLine();   //Add new line
			        writer.write(m.group());
			        writer.close();
			        
			    	}   	
				}
			}
			System.out.print("PMD report generated\n");
		}

        }
}