package com.he.ontology.action.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.model.THscodeEntry;
import com.he.ontology.service.interfaces.IHscodeEntryService;

/**
 * 步骤3
 * 计算3级词条的权重，在词条抽取之后使用。
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class UpdateEntryWeight extends AbstractJUnit4SpringContextTests{

/*	@Autowired
	IHscodeEntryService iWeiboEntryService;*/
	
	/* (non-Javadoc)
	 * @see com.he.ontology.action.interfaces.IUpdateEntryWeight#updateWeight()
	 */
	@Test
	public static void updateWeight(IHscodeEntryService iWeiboEntryService, String hscode) {
		
		// TODO Auto-generated method stub
		List<THscodeEntry> tWeiboEntries=iWeiboEntryService.geTWeiboEntries(hscode);
		int all=countAll(tWeiboEntries);
			for (THscodeEntry tWeiboEntry : tWeiboEntries) {
				double weight=(double)tWeiboEntry.getNum()/(double)all;
				tWeiboEntry.setWeight(weight);
				iWeiboEntryService.update(tWeiboEntry);
			}
	}

	/**
	 * @param tWeiboEntries
	 * @return
	 */
	private static int countAll(List<THscodeEntry> tWeiboEntries) {
		// TODO Auto-generated method stub
		int temp=0;
		for (THscodeEntry tWeiboEntry : tWeiboEntries) {
			temp+=tWeiboEntry.getNum();
		}
		return temp;
	}

}
