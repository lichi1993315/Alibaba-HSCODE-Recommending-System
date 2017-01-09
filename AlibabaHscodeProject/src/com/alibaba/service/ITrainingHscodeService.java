package com.alibaba.service;

import java.util.List;

import com.alibaba.model.TrainingHscodeBean;

public interface ITrainingHscodeService
{
	public List<TrainingHscodeBean> getProduct(String hsCode) ;
	public List<String> getHscodeCatalogy();
}
