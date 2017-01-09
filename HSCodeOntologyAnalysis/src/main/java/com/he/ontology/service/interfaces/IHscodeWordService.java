package com.he.ontology.service.interfaces;

import java.util.List;

import com.he.ontology.dao.interfaces.IHscodeWordDao;
import com.he.ontology.model.THscodeWord;

public interface IHscodeWordService extends IBaseService<THscodeWord, IHscodeWordDao>{
	
	public List<THscodeWord> geTWeiboWords(String theme, double threshold);
	
	public THscodeWord getByWordName(String word);
	
	public List<THscodeWord> getTargetWord(String theme,double wTHRESHOLD);
	
	public List<String> getCategory();
	
}
