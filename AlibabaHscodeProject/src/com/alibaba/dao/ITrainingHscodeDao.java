package com.alibaba.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.alibaba.model.TrainingHscodeBean;

public interface ITrainingHscodeDao 
{
	
    /**
     * @param hscode
     * @return
     * @throws DataAccessException
     */
    public List<TrainingHscodeBean> findProductByHscode(String hscode) throws DataAccessException;
    public List<String> getHscodeCatalogy() throws DataAccessException;
}

