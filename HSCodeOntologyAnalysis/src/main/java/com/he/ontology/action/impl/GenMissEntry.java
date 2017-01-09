package com.he.ontology.action.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.service.interfaces.IHscodeDataService;
import com.he.ontology.service.interfaces.IHscodeEdgeService;
import com.he.ontology.service.interfaces.IHscodeEntryService;
import com.he.ontology.service.interfaces.IHscodeWordFrequencyService;
import com.he.ontology.service.interfaces.IHscodeWordService;

/**
 * 以低一倍的共现阈值生成遗漏的数据
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class GenMissEntry extends AbstractJUnit4SpringContextTests{
	@Autowired
	IHscodeWordService iHscodeWordService;
	@Autowired
	IHscodeDataService iHscodeDataService;
	@Autowired
	IHscodeEntryService iHscodeEntryService;
	@Autowired
	IHscodeWordFrequencyService iHscodeWordFrequencyService;
	@Autowired
	IHscodeEdgeService iHscodeEdgeService;
	private final Double MTHRESHOLD=0.05;
	private final Double WTHRESHOLD=0.05;
	
	@Test
	public void genMissEntry() {
		
		List<String> strings=FindMiss.testEntry(iHscodeDataService, iHscodeWordService);
		for (int i = 0; i < strings.size(); i++) {
			String Hscode=strings.get(i);
			ExtractWord.extractWord(iHscodeWordService, iHscodeDataService,Hscode);
			ExtractEntry.extractEntry(iHscodeDataService, iHscodeWordService, iHscodeEntryService, iHscodeWordFrequencyService, iHscodeEdgeService,Hscode, MTHRESHOLD,WTHRESHOLD);
			UpdateEntryWeight.updateWeight(iHscodeEntryService,Hscode);
			UpdateEdgeWeight.update(iHscodeEdgeService, iHscodeEntryService,Hscode);
			UpdateWordWeight.update(iHscodeEdgeService, iHscodeWordService,Hscode, WTHRESHOLD);
		}
		
	}
}
