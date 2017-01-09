package com.he.ontology.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.he.ontology.dao.interfaces.IHscodeEdgeDao;
import com.he.ontology.model.THscodeEdge;

@Repository
public class HscodeEdgeDao extends BaseDao<THscodeEdge> implements IHscodeEdgeDao{

	private static Logger log=Logger.getLogger(HscodeEdgeDao.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeEdge> getTheme(String hScode) {
		// TODO Auto-generated method stub
		log.debug("getting instance with all");
		List<THscodeEdge> tWeiboEdges=new ArrayList<THscodeEdge>();
		String sql="select * from t_hscode_edge where hscode=\""+hScode+"\";";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(THscodeEdge.class);
        	tWeiboEdges = sqlQuery.list();
        	
        } catch (RuntimeException re) {
            log.error("get weiboedge", re);
            throw re;
        }
        return tWeiboEdges;
	}

}
