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
import com.he.ontology.util.Constants;
import com.he.ontology.util.DeleteUnUsefulWord;
import com.he.ontology.util.Nlpir;

/**
 * 进行分词，使用nlpir
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class SpiltWord extends AbstractJUnit4SpringContextTests{

	@Autowired
	IHscodeDataService iHscodeDataService;
	
	/* (non-Javadoc)
	 * @see com.he.ontology.action.interfaces.ISpiltWord#SplitWordAndSave()
	 */
	@Test
	public void SplitWordAndSave() {
		// TODO Auto-generated method stub
//		List<THscodeData> tHscodeDatas = iHscodeDataService.getByTheme(Constants.HScode);
		List<THscodeData> tHscodeDatas = iHscodeDataService.getAll();
		for (int i = 0; i < tHscodeDatas.size(); i++) {
			
			THscodeData tHscodeData = tHscodeDatas.get(i);

			String spilt=Nlpir.diveWord(Constants.NLPIR_DATA_DIR, Constants.CHAR_SET, tHscodeData.getName(), Constants.WordMean, Constants.USERDICT);
			
			String results=DeleteUnUsefulWord.ExcuteWeibo(spilt);
			String[] result=results.split("=====");
			
			tHscodeData.setNameSeg(result[0]);
			tHscodeData.setCount(Integer.parseInt(result[1]));
			iHscodeDataService.update(tHscodeData);
		}
	}
}
