package com.he.ontology.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.he.ontology.dao.interfaces.IHscodeFrequencyDao;
import com.he.ontology.model.THscodeWordFrequency;
import com.he.ontology.service.interfaces.IHscodeWordFrequencyService;

@Service
public class HscodeWordFrequencyService extends BaseService<THscodeWordFrequency, IHscodeFrequencyDao> implements IHscodeWordFrequencyService{

	@Autowired
	public HscodeWordFrequencyService(IHscodeFrequencyDao iWordFrequDao) {
		super(iWordFrequDao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	IHscodeFrequencyDao iWordFrequDao;
	@Override
	public List<THscodeWordFrequency> geTWordFrequs(String wordName,String hscode) {
		// TODO Auto-generated method stub
		return iWordFrequDao.geTWordFrequs(wordName,hscode);
	}

/*	@Override
	public List<THscodeWordFrequency> getByDocName(String wid,String hscode) {
		// TODO Auto-generated method stub
		return iWordFrequDao.getByWid(wid,hscode);
	}*/

}
