package com.he.ontology.dao.interfaces;

import java.util.List;

import com.he.ontology.model.THscodeWordFrequency;

public interface IHscodeFrequencyDao extends IBaseDao<THscodeWordFrequency>{
	public List<THscodeWordFrequency> geTWordFrequs(String wordName,String hscode);
//	public List<THscodeWordFrequency> getByWid(String wid,String hscode);
}
