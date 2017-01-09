package com.he.ontology.action.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.action.model.Term;
import com.he.ontology.model.THscodeData;
import com.he.ontology.model.THscodeWord;
import com.he.ontology.model.THscodeWordFrequency;
import com.he.ontology.service.interfaces.IHscodeDataService;
import com.he.ontology.service.interfaces.IHscodeWordService;

/**
 * 抽取词与词、文档分布，步骤1，所有主题都需要使用到
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class ExtractWord extends AbstractJUnit4SpringContextTests {

/*	@Autowired
	IHscodeWordService iHscodeWordService;
	@Autowired
	IHscodeDataService iHscodeDataService;*/

	@Test
	public static void extractWord(IHscodeWordService iHscodeWordService,IHscodeDataService iHscodeDataService, String hscode) {
		// TODO Auto-generated method stub
		
		List<THscodeData> list = iHscodeDataService.getByTheme(hscode);
		TreeMap<String, THscodeData> weiboMap = new TreeMap<String, THscodeData>();
		
		for (THscodeData tHscodeData : list) {
			if (null==tHscodeData.getName() || ""== tHscodeData.getName()) {
				continue;
			}
			weiboMap.put(tHscodeData.getId().toString(), tHscodeData);
		}
		
		TreeMap<String, THscodeWord> words = setTermSet(weiboMap,hscode);
		Iterator<Entry<String, THscodeWord>> iterator=words.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, com.he.ontology.model.THscodeWord> entry = (Map.Entry<java.lang.String, com.he.ontology.model.THscodeWord>) iterator
					.next();
			THscodeWord tWeiboWord=entry.getValue();
			iHscodeWordService.save(tWeiboWord);
		}
	}


	/**
	 * @param weiboMap
	 * @param theme
	 * @return
	 */
	private static TreeMap<String, THscodeWord> setTermSet(TreeMap<String, THscodeData> weiboMap,String hscode) {

//		the most important
		TreeMap<String, THscodeWord> words = new TreeMap<String, THscodeWord>();
		HashMap<String,Term> termMap=new HashMap<String,Term>();
		String currentArticleKey = weiboMap.firstKey();

		while (currentArticleKey != null) {
			
			THscodeData tWeiboContentSeg = weiboMap.get(currentArticleKey);
			String[] termArray = tWeiboContentSeg.getNameSeg().split(" ");

//			对一个词进行处理
			for (String t : termArray) {

				if ("".equals(t) || " ".equals(t) || "\"".equals(t)) {
					continue;
				}
				if (!words.keySet().contains(t)) {
					
					THscodeWord newWeiboWord = new THscodeWord();
					Term term=new Term();
					
					newWeiboWord.setNum(1);
					newWeiboWord.setWord(t);
					newWeiboWord.setHscode(hscode);
					
					term.inDocInfo.put(currentArticleKey, 1);
					term.wordname=t;

					words.put(t, newWeiboWord);
					termMap.put(t, term);
				} else {
					THscodeWord oldWord = words.get(t);
					Term oldTerm=termMap.get(t);
					
					oldWord.setNum(oldWord.getNum()+1);
					if (null==oldTerm.inDocInfo.get(currentArticleKey)) {
						oldTerm.inDocInfo.put(currentArticleKey, 1);
					}else {
						oldTerm.inDocInfo.put(currentArticleKey, oldTerm.inDocInfo.get(currentArticleKey)+1);
					}
					words.put(t, oldWord);
					termMap.put(t, oldTerm);
				}
			}

			currentArticleKey = weiboMap.higherKey(currentArticleKey);
		}

		Iterator<String> it=words.keySet().iterator();
		while (it.hasNext()) {
			String string = (String) it.next();
			Set<THscodeWordFrequency> tWordFrequs =new HashSet<THscodeWordFrequency>();
			Iterator<Entry<String, Integer>> it2= termMap.get(string).inDocInfo.entrySet().iterator();
			
			while (it2.hasNext()) {
				Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) it2
						.next();
				THscodeWordFrequency tWordFrequ=new THscodeWordFrequency();
				tWordFrequ.setNum(entry.getValue());
				tWordFrequ.setWordname(string);
				tWordFrequ.setTHscodeData(weiboMap.get(entry.getKey()));
				tWordFrequ.setTHscodeWord(words.get(string));
				tWordFrequ.setHscode(hscode);
				tWordFrequs.add(tWordFrequ);
			}
			
			words.get(string).setTHscodeWordFrequencies(tWordFrequs);
			words.get(string).setDocsnum(tWordFrequs.size());
		}

		return words;
	}
	
}
