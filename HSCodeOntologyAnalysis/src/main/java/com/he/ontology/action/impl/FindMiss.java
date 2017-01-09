package com.he.ontology.action.impl;

import java.util.ArrayList;
import java.util.List;

import com.he.ontology.service.interfaces.IHscodeDataService;
import com.he.ontology.service.interfaces.IHscodeWordService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:springcontext-config.xml")
//public class FindMiss extends AbstractJUnit4SpringContextTests{
/**
 * @author HXF
 *
 */
public class FindMiss{	
/*	@Autowired
	IHscodeDataService iHscodeDataService;
	@Autowired
	IHscodeWordService iHscodeWordService;*/
	
	public static List<String> testEntry(IHscodeDataService iHscodeDataService,IHscodeWordService iHscodeWordService){
		List<String> datacatagorylist=iHscodeDataService.getCategory();
		
		List<String> wordcataforylist=iHscodeWordService.getCategory();
		List<String> resultlist=new ArrayList<String>();
		resultlist.addAll(datacatagorylist);
		resultlist.removeAll(wordcataforylist);
		
		System.out.println(datacatagorylist.size()+"-"+wordcataforylist.size());
		return resultlist;
	/*	List<THscodeData> tHscodeDatas=new ArrayList<THscodeData>();
		for (int i = 0; i < resultlist.size(); i++) {
			tHscodeDatas.addAll(iHscodeDataService.getByTheme(resultlist.get(i)));
			System.out.println(resultlist.get(i));
		}
		for (THscodeData tHscodeData : tHscodeDatas) {
			System.out.println(tHscodeData.getName()+"="+tHscodeData.getResult());
		}
		System.out.println("HScode Miss="+resultlist.size()+";Data Miss="+tHscodeDatas.size());
		*/
		
	}
}
