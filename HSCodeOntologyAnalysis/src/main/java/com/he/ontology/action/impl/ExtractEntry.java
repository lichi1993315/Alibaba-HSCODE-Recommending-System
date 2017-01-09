package com.he.ontology.action.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.model.THscodeData;
import com.he.ontology.model.THscodeEdge;
import com.he.ontology.model.THscodeEntry;
import com.he.ontology.model.THscodeWord;
import com.he.ontology.model.THscodeWordFrequency;
import com.he.ontology.service.interfaces.IHscodeDataService;
import com.he.ontology.service.interfaces.IHscodeEdgeService;
import com.he.ontology.service.interfaces.IHscodeEntryService;
import com.he.ontology.service.interfaces.IHscodeWordFrequencyService;
import com.he.ontology.service.interfaces.IHscodeWordService;

/**
 * 抽取三级词条，并生成edge，步骤2
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class ExtractEntry extends AbstractJUnit4SpringContextTests{

/*	@Autowired
	IHscodeDataService iWeiboContentSegService;
	@Autowired
	IHscodeWordService iWeiboWordService;
	@Autowired
	IHscodeEntryService iEntryService;
	@Autowired
	IHscodeWordFrequencyService iWordFrequService;
	@Autowired
	IHscodeEdgeService iWeiboEdgeService;*/
	
	/* (non-Javadoc)
	 * @see com.he.ontology.action.interfaces.IExtractEntry#extractEntry()
	 */
	@Test
	public static void extractEntry(IHscodeDataService iWeiboContentSegService,IHscodeWordService iWeiboWordService,IHscodeEntryService iEntryService,IHscodeWordFrequencyService iWordFrequService,IHscodeEdgeService iWeiboEdgeService, String hscode, Double mTHRESHOLD,Double wTHRESHOLD) {
		// TODO Auto-generated method stub
		
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		
		List<THscodeData> list=iWeiboContentSegService.getByTheme(hscode);
//		计算高频词的阈值
		double threshold=calThreshold(list,wTHRESHOLD);
		List<THscodeWord> tWeiboWords=iWeiboWordService.geTWeiboWords(hscode,threshold);

		for (int i = 0; i < tWeiboWords.size(); i++) {
			
			List<THscodeWordFrequency> tList=iWordFrequService.geTWordFrequs(tWeiboWords.get(i).getWord(),hscode);
			Set<THscodeWordFrequency> tSet=convert(tList);
			for (int j = i+1; j < tWeiboWords.size(); j++) {
				Set<THscodeWordFrequency> tSet2=convert(iWordFrequService.geTWordFrequs(tWeiboWords.get(j).getWord(),hscode));
				int num=countsim(tSet, tSet2);
				double Hinf=(double)num/(double)(tSet.size()+tSet2.size());
				if (Hinf>mTHRESHOLD) {
//					大于共现阈值的双词词条
					System.out.println("["+tWeiboWords.get(i).getWord()+","+tWeiboWords.get(j).getWord()+"]="+Hinf);
					list1.add(tWeiboWords.get(i).getWord());
					list2.add(tWeiboWords.get(j).getWord());
					THscodeEdge tWeiboEdge=new THscodeEdge();
					tWeiboEdge.setSource(tWeiboWords.get(i).getWord());
					tWeiboEdge.setTarget(tWeiboWords.get(j).getWord());
					tWeiboEdge.setHscode(hscode);
					iWeiboEdgeService.save(tWeiboEdge);
				}
			}
			
		}
		
		List<String> resultList=countHinf(list1,list2);
		for (int i = 0; i < resultList.size(); i++) {
			THscodeEntry tWeiboEntry=new THscodeEntry();
			tWeiboEntry.setEntry(resultList.get(i));
			tWeiboEntry.setHscode(hscode);
			int count=0;
			for (THscodeData tWeiboContentSeg : list) {
				String temp=tWeiboContentSeg.getNameSeg();
				int end=StringUtils.indexOf(resultList.get(i), "]");
				String tempstr=StringUtils.substring(resultList.get(i), 1, end);
				String[] strs=tempstr.split(",");
				if (strs.length>=3) {
					if (temp.contains(strs[0]) && temp.contains(strs[1]) && temp.contains(strs[2])) {
						count++;
					}
				}
			}
			tWeiboEntry.setNum(count);
			if (count>0) {
				iEntryService.save(tWeiboEntry);
			}
			System.out.println(resultList.get(i));
		}
	}

	/**
	 * 如果某词条第二个词和某词条第一个词相同，生成三个词组成的词条
	 * @param list1
	 * @param list2
	 * @return
	 */
	private static List<String> countHinf(List<String> list1,List<String> list2) {
		List<String> resList=new ArrayList<String>();
		for (int i = 0; i < list2.size(); i++) {
			for (int j = i+1; j < list1.size(); j++) {
				if (list1.get(j).equals(list2.get(i))) {
					resList.add("["+list1.get(i)+","+list2.get(i)+","+list2.get(j)+"]");
				}
			}
		}
		
		return resList;
		
	}
	
	/**
	 * 转化成可以保存的set格式，方便后面做交集，得出词汇A与词汇B同时出现的窗体数（微博数）
	 * @param list
	 * @return
	 */
	private static Set<THscodeWordFrequency> convert(List<THscodeWordFrequency> list) {
		
		Set<THscodeWordFrequency> tWordFrequs=new HashSet<THscodeWordFrequency>();
		for (THscodeWordFrequency tWordFrequ : list) {
			tWordFrequs.add(tWordFrequ);
		}
		return tWordFrequs;
		
	}
	
	/**
	 * 计算共线率
	 * @param tSet
	 * @param tSet2
	 * @return
	 */
	private static int countsim(Set<THscodeWordFrequency> tSet,Set<THscodeWordFrequency> tSet2) {
		
		Set<String> resSet=new HashSet<String>();
		Set<String> resSet2=new HashSet<String>();
		Set<String> resSet3=new HashSet<String>();
		
		Iterator<THscodeWordFrequency> iterator=tSet.iterator();
		while (iterator.hasNext()) {
			THscodeWordFrequency tWordFrequ = (THscodeWordFrequency) iterator.next();
			resSet.add(tWordFrequ.getTHscodeData().getId().toString());
		}
		Iterator<THscodeWordFrequency> iterator2=tSet2.iterator();
		while (iterator2.hasNext()) {
			THscodeWordFrequency tWordFrequ = (THscodeWordFrequency) iterator2.next();
			resSet2.add(tWordFrequ.getTHscodeData().getId().toString());
		}
		
		resSet3.addAll(resSet);
		resSet3.retainAll(resSet2);
		return resSet3.size();
		
	}
	
	/**
	 * 计算高频特征词的阈值
	 * @param list
	 * @return
	 */
	private static double calThreshold(List<THscodeData> list,double wTHRESHOLD) {
		
		double thresholdnum=wTHRESHOLD*list.size();
		return thresholdnum;
		
	}
}
