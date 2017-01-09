package com.he.ontology.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.service.interfaces.IHscodeDataService;

/**
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class CalAccuracy extends AbstractJUnit4SpringContextTests{
	@Autowired
	IHscodeDataService iHscodeDataService;
	
	@Test
	public void calAccuracy(){
		
		List<String> list=new ArrayList<String>();
		List<String> hscodeList=iHscodeDataService.getCategory();
		for (int i = 0; i < hscodeList.size(); i++) {
			String hscode=hscodeList.get(i);
			int tHscodeDatas=iHscodeDataService.getAccuracyByHscode(hscode,0);
			int tHscodeAccuracyDatas=iHscodeDataService.getAccuracyByHscode(hscode,1);
			double reslut=(double)tHscodeAccuracyDatas/(double)(tHscodeDatas+tHscodeAccuracyDatas);
			
			if (reslut<0.65) {
				String finalResult=hscode+"="+reslut;
				list.add(finalResult);
			}
		}
		for (String string : list) {
			System.out.println(string);
		}
	}
}
