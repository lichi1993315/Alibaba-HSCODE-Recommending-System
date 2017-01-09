package com.he.ontology.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LAB
 *
 */
public class DeleteUnUsefulWord {
	
	/**
	 * @param spilt
	 * @return
	 */
	public static String ExcuteWeibo(String spilt){
		
		HashSet<String> stopwordSet=setStopwordSet(Constants.STOPTXTPATH);
		
		String[] strTemp=spilt.split(" ");
		List<String> wordList=new ArrayList<String>();
		String temp="";
		Pattern pattern=Pattern.compile("http");
		
		@SuppressWarnings("unused")
		Matcher matcher;
		for (int i = 0; i < strTemp.length; i++) {
			wordList.add(strTemp[i]);
		}
		for (int i = 0; i < wordList.size(); i++) {
			if (stopwordSet.contains(wordList.get(i)) || true==pattern.matcher(wordList.get(i)).find()) {
				wordList.remove(i);
			}
		}
		String result="";
		for (int i = 0; i < wordList.size(); i++) {
			result+=wordList.get(i)+" ";
		}
		temp=result+"====="+wordList.size();
		return temp;
	
	}
	
	/**
	 * StopWordSet 建立
	 * @param txtPath
	 */
	private static HashSet<String> setStopwordSet(String stoptxtPath) {
		
		String str = "";
		HashSet<String> stopwordSet = new HashSet<String>();
		
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferedIn = new BufferedReader(new FileReader(stoptxtPath));
			while ((str = bufferedIn.readLine()) != null) {
				stopwordSet.add(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stopwordSet;
	}
}
