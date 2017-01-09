package com.he.ontology.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.he.ontology.dao.interfaces.IHscodeEntryDao;
import com.he.ontology.model.THscodeEntry;
import com.he.ontology.service.interfaces.IHscodeEntryService;

@Service
public class HscodeEntryService extends BaseService<THscodeEntry, IHscodeEntryDao> implements IHscodeEntryService{

	@Autowired
	public HscodeEntryService(IHscodeEntryDao baseDao) {
		super(baseDao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	IHscodeEntryDao iWeiboEntryDao;
	
	@Override
	public List<THscodeEntry> geTWeiboEntries(String theme) {
		// TODO Auto-generated method stub
		return iWeiboEntryDao.getEntries(theme);
	}

}
