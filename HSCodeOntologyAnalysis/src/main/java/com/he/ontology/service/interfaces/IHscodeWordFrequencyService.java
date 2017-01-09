package com.he.ontology.service.interfaces;

import java.util.List;

import com.he.ontology.dao.interfaces.IHscodeFrequencyDao;
import com.he.ontology.model.THscodeWordFrequency;

public interface IHscodeWordFrequencyService extends IBaseService<THscodeWordFrequency, IHscodeFrequencyDao>{
	
	public List<THscodeWordFrequency> geTWordFrequs(String wordName,String hscode);

}
