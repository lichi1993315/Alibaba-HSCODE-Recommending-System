package com.he.ontology.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.he.ontology.dao.interfaces.IHscodeEdgeDao;
import com.he.ontology.model.THscodeEdge;
import com.he.ontology.service.interfaces.IHscodeEdgeService;

@Service
public class HscodeEdgeService extends BaseService<THscodeEdge, IHscodeEdgeDao> implements IHscodeEdgeService{

	@Autowired
	public HscodeEdgeService(IHscodeEdgeDao baseDao) {
		super(baseDao);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	IHscodeEdgeDao iWeiboEdgeDao;

	@Override
	public List<THscodeEdge> getTheme(String hScode) {
		// TODO Auto-generated method stub
		return iWeiboEdgeDao.getTheme(hScode);
	}

}
