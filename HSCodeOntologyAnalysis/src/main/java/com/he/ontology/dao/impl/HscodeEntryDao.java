package com.he.ontology.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.he.ontology.dao.interfaces.IHscodeEntryDao;
import com.he.ontology.model.THscodeEntry;

@Repository
public class HscodeEntryDao extends BaseDao<THscodeEntry> implements IHscodeEntryDao {

	private static Logger log=Logger.getLogger(HscodeEntryDao.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeEntry> getEntries(String theme) {
		// TODO Auto-generated method stub
		log.debug("getting instance with entry: ");
		List<THscodeEntry> tWeiboEntries=new ArrayList<THscodeEntry>();
		String sql="select * from t_hscode_entry where hscode=\""+theme+"\";";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(THscodeEntry.class);
			tWeiboEntries = sqlQuery.list();
        	
        } catch (RuntimeException re) {
            log.error("get weiboEntry", re);
            throw re;
        }
        return tWeiboEntries;
	}

}
