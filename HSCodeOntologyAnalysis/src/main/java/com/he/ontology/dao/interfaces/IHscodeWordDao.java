package com.he.ontology.dao.interfaces;

import java.util.List;

import com.he.ontology.model.THscodeWord;

public interface IHscodeWordDao extends IBaseDao<THscodeWord>{
	
	public List<THscodeWord> geTWeiboWords(String theme, double threshold);
	
	public THscodeWord getByName(String word);
	
	public List<THscodeWord> getTargetWord(String theme,double wTHRESHOLD);
	
	public List<String> getCategory();
}
