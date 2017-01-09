package com.he.ontology.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.he.ontology.dao.interfaces.IHscodeDataDao;
import com.he.ontology.model.THscodeData;

@Repository
public class HscodeDataDao extends BaseDao<THscodeData> implements IHscodeDataDao{
	
	private static Logger log=Logger.getLogger(HscodeDataDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeData> getbyTheme(String hscode) {
		// TODO Auto-generated method stub
		log.debug("getting instance with theme: " + hscode);
		List<THscodeData> tHscodeDatas=new ArrayList<THscodeData>();
		String sql="select * from t_hscode_data where hscode=\""+hscode+"\";";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(THscodeData.class);
			tHscodeDatas = sqlQuery.list();
        	
        } catch (RuntimeException re) {
            log.error("get HscodeData failed", re);
            throw re;
        }
        return tHscodeDatas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeData> getAll() {
		// TODO Auto-generated method stub
		log.debug("getting instance with theme: ");
		List<THscodeData> tHscodeDatas=new ArrayList<THscodeData>();
		String sql="select * from t_hscode_data;";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
			sqlQuery.addEntity(THscodeData.class);
			tHscodeDatas = sqlQuery.list();
        	
        } catch (RuntimeException re) {
            log.error("get HscodeData failed", re);
            throw re;
        }
        return tHscodeDatas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> get() {
		// TODO Auto-generated method stub
		log.debug("getting instance with theme: ");
		List<String> strings=new ArrayList<String>();
		String sql="SELECT DISTINCT(hscode) FROM t_hscode_data LIMIT 387,30;";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        	strings = sqlQuery.list();
        	
        } catch (RuntimeException re) {
            log.error("get HscodeData failed", re);
            throw re;
        }
        return strings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<THscodeData> getsome() {
		// TODO Auto-generated method stub
				log.debug("getting instance with theme: ");
				List<THscodeData> strings=new ArrayList<THscodeData>();
				String sql="SELECT * FROM t_hscode_data LIMIT 27668;";
		        try {
		        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		        	sqlQuery.addEntity(THscodeData.class);
		        	strings = sqlQuery.list();
		        	
		        } catch (RuntimeException re) {
		            log.error("get HscodeData failed", re);
		            throw re;
		        }
		        return strings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCategory() {
		// TODO Auto-generated method stub
		log.debug("getting instance with theme: ");
		List<String> strings=new ArrayList<String>();
		String sql="SELECT DISTINCT(hscode) FROM t_hscode_data;";
        try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        	strings = sqlQuery.list();
        	
        } catch (RuntimeException re) {
            log.error("get HscodeData failed", re);
            throw re;
        }
        return strings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getaccuracy(String hscode,int num) {
		// TODO Auto-generated method stub
		log.debug("getting instance with hscode: " + hscode);
		String sql="select result from t_hscode_data where hscode=\""+hscode+"\" And result=\""+num+"\";";
		List<Integer> accuracyNums =new ArrayList<Integer>();
		try {
        	SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        	accuracyNums = sqlQuery.list();
        	
        } catch (RuntimeException re) {
            log.error("get HscodeData failed", re);
            throw re;
        }
		int result=accuracyNums.size();
        return result;
	}
}
