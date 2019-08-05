package com.philips.bootcamp.analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;

import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {
	
	public void parseCheckstyleIssues(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader input = new FileReader("./reportCheckstyle.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;

		Map<String, Integer> map = new HashMap<String, Integer>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		while ( (myLine = bufRead.readLine()) != null)
		{    
		    String[] array = myLine.split(" ");
		    
			Pattern p = Pattern.compile("\\[(.*?)\\]");
			
			for (int i=0;i<array.length;i++) {
			
				Matcher m = p.matcher(array[i]);
				
				if(m.find()) {
					//System.out.println(m.group());
					String s = m.group(1);
				
					if(!map.containsKey(s))
					{
						map.put(s, 1);
					}
					else 
					{
						int count = map.get(s);
						map.put(s,count+1);
					}
				}
			}   	
		}
	       map.forEach((k,v) -> System.out.println(k + " = " + v));
	       try {
	            mapper.writeValue(new File("result.json"), map);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       bufRead.close();
	}
}
