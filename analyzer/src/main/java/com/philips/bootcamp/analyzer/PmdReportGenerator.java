package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.philips.bootcamp.utils.FileValidator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PmdReportGenerator extends Tool{
		
	private String pmdRuleset;
	private String pmdOutputFile;
	private Timestamp reportStartTime;
	private Timestamp reportEndTime;
	
	public PmdReportGenerator(String filepath, String pmdRuleset, String pmdOutputFile) {
		super(filepath);
		this.pmdRuleset = pmdRuleset;
		this.pmdOutputFile = pmdOutputFile;		
	}
	
	
	public static PmdReportGenerator getPmdReportObject (String configFile) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileReader(configFile));		
		String filepath = p.getProperty("path");
		String pmdRuleset = p.getProperty("pmdRuleset");
		String pmdOutPutFile = p.getProperty("pmdOutputFile");
		PmdReportGenerator report = new PmdReportGenerator(filepath, pmdRuleset, pmdOutPutFile);						
		return report;
	}

	@Override
	public void generateReport() {		
		if (isValidReport()) {
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
					File file = new File(result.get(i));					
					String fileNameWithOutExt = file.getName().replaceFirst("[.][^.]+$", "");										
					Pattern p = Pattern.compile("^.*\\b("+result.get(i).replace("\\", "\\\\")+")\\b.*$");
				    Matcher m = p.matcher(s);
				    if(m.find()) {				    	
						BufferedWriter writer = new BufferedWriter(
	                          new FileWriter("./reports/"+fileNameWithOutExt+".txt", true)  //Set true for append mode
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

<<<<<<< HEAD
	@Override
	public boolean isValidReport() {		
		if (this.getFilepath().equals(null) || this.getFilepath()=="")
			return false;
		return (FileValidator.isValidPath(this.getFilepath()));
	}			
=======
	}
	
>>>>>>> 17a1f1a... add merge functionality, remove comments and misc
}