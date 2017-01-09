package com.he.ontology.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.he.ontology.dao.interfaces.IHscodeDataDao;
import com.he.ontology.model.THscodeData;
import com.he.ontology.service.interfaces.IHscodeDataService;

@Service
public class HscodeDataService extends BaseService<THscodeData, IHscodeDataDao> implements IHscodeDataService{

	@Autowired
	public HscodeDataService(IHscodeDataDao iHscodeDataDao) {
		super(iHscodeDataDao);
		// TODO Auto-generated constructor stub
	}
	@Autowired
	IHscodeDataDao iHscodeDataDao;
	@Override
	public List<THscodeData> getByTheme(String theme) {
		// TODO Auto-generated method stub
		return iHscodeDataDao.getbyTheme(theme);
	}
	@Override
	public List<THscodeData> getAll() {
		// TODO Auto-generated method stub
		return iHscodeDataDao.getAll();
	}
	@Override
	public List<String> getHscodeCategory() {
		// TODO Auto-generated method stub
		return iHscodeDataDao.get();
	}
	@Override
	public List<THscodeData> getSome() {
		// TODO Auto-generated method stub
		return iHscodeDataDao.getsome();
	}
	@Override
	public List<String> getCategory() {
		// TODO Auto-generated method stub
		return iHscodeDataDao.getCategory();
	}
	@Override
	public int getAccuracyByHscode(String string,int category) {
		// TODO Auto-generated method stub
		return iHscodeDataDao.getaccuracy(string,category);
	}
}
