package com.he.ontology.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.model.THscodeData;
import com.he.ontology.service.interfaces.IHscodeDataService;
import com.he.ontology.service.interfaces.IHscodeWordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class TestGenEntry extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	IHscodeDataService iHscodeDataService;
	@Autowired
	IHscodeWordService iHscodeWordService;
	
	@Test
	public void testEntry(){
		List<String> datacatagorylist=iHscodeDataService.getCategory();
		System.out.println(datacatagorylist.size());
		List<String> wordcataforylist=iHscodeWordService.getCategory();
		System.out.println(wordcataforylist.size());
		List<String> resultlist=new ArrayList<String>();
		resultlist.addAll(datacatagorylist);
		resultlist.removeAll(wordcataforylist);
		
		List<THscodeData> tHscodeDatas=new ArrayList<THscodeData>();
		for (int i = 0; i < resultlist.size(); i++) {
			tHscodeDatas.addAll(iHscodeDataService.getByTheme(resultlist.get(i)));
			System.out.println(resultlist.get(i));
		}
		System.out.println(resultlist.size());
		for (THscodeData tHscodeData : tHscodeDatas) {
			System.out.println(tHscodeData.getName()+"="+tHscodeData.getResult());
		}
		System.out.println("HScode Miss="+resultlist.size()+";Data Miss="+tHscodeDatas.size());
		
		
	}
}
