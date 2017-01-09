package com.he.ontology.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.he.ontology.dao.interfaces.IHscodeWordDao;
import com.he.ontology.model.THscodeWord;
import com.he.ontology.util.Constants;

@Repository
public class HscodeWordDao extends BaseDao<THscodeWord> implements IHscodeWordDao{
	
	private static Logger log=Logger.getLogger(HscodeWordDao.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeWord> geTWeiboWords(String theme,double threshold) {
		// TODO Auto-generated method stub
		log.debug("getting instance with threshold: " + theme);
		List<THscodeWord> tWeiboWords=new ArrayList<THscodeWord>();
		String sql="select * from t_hscode_word where docsnum>=\""+threshold+ "\" AND hscode=\""+theme+"\";";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(THscodeWord.class);
        	tWeiboWords = sqlQuery.list();
        } catch (RuntimeException re) {
            log.error("get WeiboWord failed", re);
            throw re;
        }
        return tWeiboWords;
	}

	@SuppressWarnings("unchecked")
	@Override
	public THscodeWord getByName(String word) {
		
		// TODO Auto-generated method stub
		log.debug("getting instance with wordName: " + word);
		List<THscodeWord> tWeiboWords=new ArrayList<THscodeWord>();
		String sql="select * from t_hscode_word where name=\""+word+"\";";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(THscodeWord.class);
        	tWeiboWords = sqlQuery.list();
        } catch (RuntimeException re) {
            log.error("get WeiboWord failed", re);
            throw re;
        }
        return tWeiboWords.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeWord> getTargetWord(String hscode,double wTHRESHOLD) {
		// TODO Auto-generated method stub
		log.debug("getting instance with weight >0: "+hscode);
		List<THscodeWord> tWeiboWords=new ArrayList<THscodeWord>();
		String sql="select * from t_hscode_word where docsnum> (select count(*) from t_hscode_data where hscode=\""+hscode+"\")*"+wTHRESHOLD+" AND hscode=\""+hscode+"\";";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(THscodeWord.class);
        	tWeiboWords = sqlQuery.list();
        } catch (RuntimeException re) {
            log.error("get WeiboWord failed", re);
            throw re;
        }
        return tWeiboWords;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCategory() {
		// TODO Auto-generated method stub
		log.debug("getting instance with wordName: ");
		List<String> tWeiboWords=new ArrayList<String>();
		String sql="select DISTINCT(hscode) from t_hscode_word;";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
//			sqlQuery.addEntity(String.class);
        	tWeiboWords = sqlQuery.list();
        } catch (RuntimeException re) {
            log.error("get WeiboWord failed", re);
            throw re;
        }
        return tWeiboWords;
	}

}
