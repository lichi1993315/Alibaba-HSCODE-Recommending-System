package com.he.ontology.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.model.THscodeEdge;
import com.he.ontology.model.THscodeEntry;
import com.he.ontology.service.interfaces.IHscodeEdgeService;
import com.he.ontology.service.interfaces.IHscodeEntryService;

/**
 * 步骤4
 * 生成Edge的权重
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class UpdateEdgeWeight extends AbstractJUnit4SpringContextTests{

/*	@Autowired
	IHscodeEdgeService iWeiboEdgeService;
	@Autowired
	IHscodeEntryService iWeiboEntryService;*/
	/* (non-Javadoc)
	 * @see com.he.ontology.action.interfaces.IEdgeWeightUpdate#update()
	 */
	@Test
	public static void update(IHscodeEdgeService iWeiboEdgeService,IHscodeEntryService iWeiboEntryService, String hscode) {
		// TODO Auto-generated method stub
		List<THscodeEdge> tWeiboEdges=iWeiboEdgeService.getTheme(hscode);
		List<THscodeEntry> tWeiboEntries=iWeiboEntryService.geTWeiboEntries(hscode);
		List<Double> list=new ArrayList<Double>();
		double tempAll=0;
		
		for (THscodeEdge tWeiboEdge : tWeiboEdges) {
		
			double temp=0;
			for (THscodeEntry tWeiboEntry : tWeiboEntries) {
				if (true==jungle(tWeiboEdge,tWeiboEntry)) {
					temp+=tWeiboEntry.getWeight();
				}
			}
			tempAll+=temp;
			list.add(temp);
			/*tWeiboEdge.setWeight(temp);
			iWeiboEdgeService.update(tWeiboEdge);*/
		}
		
		for (int i = 0; i < tWeiboEdges.size(); i++) {
			THscodeEdge tWeiboEdge=tWeiboEdges.get(i);
			tWeiboEdge.setWeight(list.get(i)/tempAll);
			iWeiboEdgeService.update(tWeiboEdge);
		}
	}
	
	/**
	 * @param tWeiboEdge
	 * @param tWeiboEntry
	 * @return
	 */
	private static boolean jungle(THscodeEdge tWeiboEdge, THscodeEntry tWeiboEntry) {
		
		// TODO Auto-generated method stub
		boolean flag=false;
		String source=tWeiboEdge.getSource();
		String target=tWeiboEdge.getTarget();
		String wordTemp=tWeiboEntry.getEntry();
		int num=wordTemp.indexOf("]");
		String words=wordTemp.substring(1, num);
		String[] word=words.split(",");
		if (source.equals(word[0]) && target.equals(word[1])) {
			flag=true;
		}
		if (source.equals(word[1]) && target.equals(word[2])) {
			flag=true;
		}
		
		return flag;
	}

}
