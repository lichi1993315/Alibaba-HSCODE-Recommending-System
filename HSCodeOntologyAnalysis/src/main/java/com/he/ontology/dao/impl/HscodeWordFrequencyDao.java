package com.he.ontology.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.he.ontology.dao.interfaces.IHscodeFrequencyDao;
import com.he.ontology.model.THscodeWordFrequency;

@Repository
public class HscodeWordFrequencyDao extends BaseDao<THscodeWordFrequency> implements IHscodeFrequencyDao{

	private static Logger log=Logger.getLogger(HscodeWordFrequencyDao.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeWordFrequency> geTWordFrequs(String wordName,String hscode) {
		// TODO Auto-generated method stub
			log.debug("getting instance with wordname: " + wordName);
			List<THscodeWordFrequency> tWordFrequs=new ArrayList<THscodeWordFrequency>();
			String sql="select * from t_hscode_word_frequency where wordname=\""+wordName+"\" AND hscode=\""+hscode+"\";";
	        try {
	        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
				sqlQuery.addEntity(THscodeWordFrequency.class);
				tWordFrequs = sqlQuery.list();
				
	        } catch (RuntimeException re) {
	            log.error("get THscodeWordFrequ failed "+sql, re);
	            throw re;
	        }
	        return tWordFrequs;
	}

/*	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeWordFrequency> getByWid(String wid,String hscode) {
		log.debug("getting instance with wid: " + hscode);
		List<THscodeWordFrequency> tWordFrequs=new ArrayList<THscodeWordFrequency>();
		String sql="select * from t_weibo_word_frequency where wid=\""+wid+"\" AND hscode=\""+hscode+"\";";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(THscodeWordFrequency.class);
			tWordFrequs = sqlQuery.list();
			
        } catch (RuntimeException re) {
            log.error("get TWordFrequ failed", re);
            throw re;
        }
        return tWordFrequs;
	}*/

}
