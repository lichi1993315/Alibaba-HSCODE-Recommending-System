package com.he.ontology.service.interfaces;

import java.util.List;

import com.he.ontology.dao.interfaces.IHscodeEdgeDao;
import com.he.ontology.model.THscodeEdge;

public interface IHscodeEdgeService extends IBaseService<THscodeEdge, IHscodeEdgeDao>{

	List<THscodeEdge> getTheme(String hScode);

}
