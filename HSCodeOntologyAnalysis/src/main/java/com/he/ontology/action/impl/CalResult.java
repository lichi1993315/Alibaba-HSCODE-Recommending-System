package com.he.ontology.action.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.model.THscodeData;
import com.he.ontology.service.interfaces.IHscodeDataService;

/**
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class CalResult extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	IHscodeDataService iHscodeDataService;
	
	@Test
	public void finallyDo(){
		
//		List<String> list=iHscodeWordService.getCategory();
		List<THscodeData> tHscodeDatas=iHscodeDataService.getSome();
		int temp=0;
//		for (int x = 0; x < list.size(); x++) {
//			tHscodeDatas.addAll(iHscodeDataService.getByTheme(list.get(x)));
//		}
		int num=tHscodeDatas.size();
		for (int i = 0; i < num; i++) {
			
			int result=0;
			THscodeData tHscodeData=tHscodeDatas.get(i);
			String Hscode=tHscodeData.getHscode();
			String Advice=tHscodeData.getAdvice();
			if (Advice.contains(Hscode)) {
				result=1;
				temp++;
			}
			tHscodeData.setResult(result);
			iHscodeDataService.update(tHscodeData);
		}
		System.out.println(temp+"/"+num);
	}
}
