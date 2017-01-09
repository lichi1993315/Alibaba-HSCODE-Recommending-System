package com.he.ontology.dao.interfaces;

import java.util.List;

import com.he.ontology.model.THscodeEntry;

public interface IHscodeEntryDao extends IBaseDao<THscodeEntry>{

	public List<THscodeEntry> getEntries(String theme) ;
}
