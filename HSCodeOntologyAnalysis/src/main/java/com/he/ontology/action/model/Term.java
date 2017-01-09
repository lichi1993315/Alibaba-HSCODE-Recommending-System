package com.he.ontology.action.model;

import java.util.HashMap;
import java.util.Map;

public class Term {

//	该term所对应的文档及在每个文档中出现的次数
	public Map<String, Integer> inDocInfo;
	public String wordname; 

	public Term() {
		wordname=null;
		inDocInfo = new HashMap<String, Integer>();
	}
	
}
