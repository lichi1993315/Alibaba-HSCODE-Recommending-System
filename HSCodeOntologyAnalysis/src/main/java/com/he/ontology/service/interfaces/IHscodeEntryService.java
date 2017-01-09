package com.he.ontology.service.interfaces;

import java.util.List;

import com.he.ontology.dao.interfaces.IHscodeEntryDao;
import com.he.ontology.model.THscodeEntry;

public interface IHscodeEntryService extends IBaseService<THscodeEntry, IHscodeEntryDao>{
	
	public List<THscodeEntry> geTWeiboEntries(String theme);
}
