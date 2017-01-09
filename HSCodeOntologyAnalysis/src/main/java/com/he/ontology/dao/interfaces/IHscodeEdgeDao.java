package com.he.ontology.dao.interfaces;

import java.util.List;

import com.he.ontology.model.THscodeEdge;

public interface IHscodeEdgeDao extends IBaseDao<THscodeEdge>{

	List<THscodeEdge> getTheme(String hScode);

}
