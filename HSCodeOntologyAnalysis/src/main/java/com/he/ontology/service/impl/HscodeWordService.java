package com.he.ontology.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.he.ontology.dao.interfaces.IHscodeWordDao;
import com.he.ontology.model.THscodeWord;
import com.he.ontology.service.interfaces.IHscodeWordService;

@Service
public class HscodeWordService extends BaseService<THscodeWord, IHscodeWordDao> implements IHscodeWordService{

	@Autowired
	public HscodeWordService(IHscodeWordDao iWeiboWordDao) {
		super(iWeiboWordDao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	IHscodeWordDao iWeiboWordDao;
	
	@Override
	public List<THscodeWord> geTWeiboWords(String theme,double threshold) {
		// TODO Auto-generated method stub
		return iWeiboWordDao.geTWeiboWords(theme,threshold);
	}

	@Override
	public THscodeWord getByWordName(String word) {
		// TODO Auto-generated method stub
		return iWeiboWordDao.getByName(word);
	}

	@Override
	public List<THscodeWord> getTargetWord(String theme,double wTHRESHOLD)  {
		// TODO Auto-generated method stub
		return iWeiboWordDao.getTargetWord(theme,wTHRESHOLD);
	}

	@Override
	public List<String> getCategory() {
		// TODO Auto-generated method stub
		return iWeiboWordDao.getCategory();
	}

}
