package com.alibaba.action;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.core.GenTargetHscodeOntology;
import com.alibaba.model.TrainingHscodeBean;
import com.alibaba.service.ITrainingHscodeService;
import com.alibaba.split.jieba.Jieba;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class GenOntologyTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	ITrainingHscodeService iTrainingHscodeService;
	
	@Test
	public void test() {
		
		/*实验阈值，人工设定*/
		double mTHRESHOLD = 0.02;
		double wTHRESHOLD = 0.01;
		
		/*开始本体生成，自动读取所有Hscode*/
		List<String> hsCodeList=iTrainingHscodeService.getHscodeCatalogy();
		HashMap<String, HashMap<String, Double>> staticMap = new HashMap<String, HashMap<String, Double>>();

		for (String hsCode : hsCodeList) {
			
			List<TrainingHscodeBean> trainingHscodeBeans = iTrainingHscodeService.getProduct(hsCode);
			TreeMap<String, String> productMap = new TreeMap<String, String>();
			
			for (TrainingHscodeBean trainingHscodeBean : trainingHscodeBeans) {
				if (null == trainingHscodeBean.getPname() || "" == trainingHscodeBean.getPname()) {
					continue;
				}
				productMap.put(trainingHscodeBean.getPid().toString(),
						Jieba.testCutForSearch(trainingHscodeBean.getPname()));
			}

			HashMap<String, Double> ontologyMap = GenTargetHscodeOntology.genTargetHscodeOntology(
					productMap, mTHRESHOLD, wTHRESHOLD);
					
			staticMap.put(hsCode, ontologyMap);
		}
		
		/*第一阶段输出*/
			System.out.println(staticMap);
	}
}
