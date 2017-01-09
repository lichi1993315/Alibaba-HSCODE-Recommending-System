package com.he.ontology.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.model.THscodeEdge;
import com.he.ontology.model.THscodeWord;
import com.he.ontology.service.interfaces.IHscodeEdgeService;
import com.he.ontology.service.interfaces.IHscodeWordService;

/**
 * 步骤5
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class UpdateWordWeight extends AbstractJUnit4SpringContextTests{

/*	@Autowired
	IHscodeEdgeService iHscodeEdgeService;
	@Autowired
	IHscodeWordService iHscodeWordService;*/
	
	/* (non-Javadoc)
	 * @see com.he.ontology.action.interfaces.IWordWeightUpdate#update()
	 */
//	@Override
	@Test
	public static void update(IHscodeEdgeService iHscodeEdgeService,IHscodeWordService iHscodeWordService, String hscode,double wTHRESHOLD) {
		
		// TODO Auto-generated method stub
		List<THscodeWord> tWeiboWords=iHscodeWordService.getTargetWord(hscode,wTHRESHOLD);
		List<THscodeEdge> tWeiboEdges=iHscodeEdgeService.getTheme(hscode);
		List<Double> list=new ArrayList<Double>();
		
		double tempAll=0;
		
		for (THscodeWord tWeiboWord : tWeiboWords) {
			
			double temp=calculate(tWeiboWord,tWeiboEdges);
			tempAll+=temp;
			list.add(temp);
			
		}
		
		for (int i = 0; i < tWeiboWords.size(); i++) {
			
			THscodeWord tWeiboWord=tWeiboWords.get(i);
			double result=list.get(i)/tempAll;
			if (result>0) {
				tWeiboWord.setWeight(result);
				iHscodeWordService.update(tWeiboWord);
			}
		}
	}

	/**
	 * @param tWeiboWord
	 * @param tWeiboEdges
	 * @return
	 */
	private static double calculate(THscodeWord tWeiboWord, List<THscodeEdge> tWeiboEdges) {
		
		// TODO Auto-generated method stub
		double weight=0;
		double temp=0;
		int count=0;
		String word=tWeiboWord.getWord();
		
		for (THscodeEdge tWeiboEdge : tWeiboEdges) {
			if (tWeiboEdge.getTarget().equals(word) || tWeiboEdge.getSource().equals(word)) {
				count++;
				temp+=tWeiboEdge.getWeight();
			}
		}
		
		weight=temp/(double)count;
		return weight;
	}

}
