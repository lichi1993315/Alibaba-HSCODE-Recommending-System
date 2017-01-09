package com.he.ontology.dao.interfaces;

import java.util.List;

import com.he.ontology.model.THscodeData;

public interface IHscodeDataDao extends IBaseDao<THscodeData>{

	List<THscodeData> getbyTheme(String theme);

	List<THscodeData> getAll();

	List<String> get();

	List<THscodeData> getsome();

	List<String> getCategory();

	int getaccuracy(String string, int category);
	
}
