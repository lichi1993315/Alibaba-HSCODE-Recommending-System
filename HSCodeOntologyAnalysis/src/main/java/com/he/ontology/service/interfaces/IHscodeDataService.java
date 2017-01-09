package com.he.ontology.service.interfaces;

import java.util.List;

import com.he.ontology.dao.interfaces.IHscodeDataDao;
import com.he.ontology.model.THscodeData;

public interface IHscodeDataService extends IBaseService<THscodeData, IHscodeDataDao>{
	
	List<THscodeData> getByTheme(String theme);
	
	List<THscodeData> getAll();
	
	List<String> getHscodeCategory();
	
	List<THscodeData> getSome();
	
	List<String> getCategory();
	
	int getAccuracyByHscode(String string, int i);
}
