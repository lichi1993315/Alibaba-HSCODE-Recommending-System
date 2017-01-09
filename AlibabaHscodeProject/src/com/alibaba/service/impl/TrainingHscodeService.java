package com.alibaba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dao.ITrainingHscodeDao;
import com.alibaba.model.TrainingHscodeBean;
import com.alibaba.service.ITrainingHscodeService;

@Service
@Transactional
public class TrainingHscodeService implements ITrainingHscodeService
{
	@Autowired
	ITrainingHscodeDao iTrainingHscodeDao;
	
	public List<TrainingHscodeBean> getProduct(String hsCode) {
		List<TrainingHscodeBean> trainingHscodeBean =iTrainingHscodeDao.findProductByHscode(hsCode);
    	return trainingHscodeBean;
	}

	@Override
	public List<String> getHscodeCatalogy() {
		// TODO Auto-generated method stub
		return iTrainingHscodeDao.getHscodeCatalogy();
	}

}
