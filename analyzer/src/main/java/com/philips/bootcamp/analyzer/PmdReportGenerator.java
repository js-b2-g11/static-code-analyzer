package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Properties;

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
			String command[] = new String[] {"pmd.bat", "-d", this.getFilepath(), "-f", "text", 
					"-R", pmdRuleset, "-r", pmdOutputFile};
			Runtime rt = Runtime.getRuntime();
			try {
				Process p = rt.exec(command);
		        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        String line;
		        while (true) {
		            line = r.readLine();
		            if (line == null) { break; }
		            System.out.println(line);
		        }
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Pmd Report generated");
		} else {
			System.out.println("Invalid/Empty file specified!");
		}
	}

	@Override
	public boolean isValidReport() {		
		if (this.getFilepath().equals(null) || this.getFilepath()=="")
			return false;
		return (FileValidator.isValidPath(this.getFilepath()));
	}
	
}