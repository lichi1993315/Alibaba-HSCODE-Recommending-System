package com.he.ontology.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.he.ontology.model.THscodeData;
import com.he.ontology.model.THscodeWord;
import com.he.ontology.service.interfaces.IHscodeDataService;
import com.he.ontology.service.interfaces.IHscodeWordService;

/**
 * @author HXF
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class GiveTargetHscodeAdvice extends AbstractJUnit4SpringContextTests{

	@Autowired
	IHscodeDataService iHscodeDataService;
	@Autowired
	IHscodeWordService iHscodeWordService;//用来获取词条权重
	private final Double WTHRESHOLD=0.05;//most by 最低特征词阈值
	
	/* (non-Javadoc)
	 * @see com.he.ontology.action.interfaces.IWeiboSimCount#cal()
	 */
	@Test
	public void calAndGiveOneAdvice() {
		
		// TODO Auto-generated method stub
/*		List<String> list=iHscodeWordService.getCategory();
		List<THscodeData> tHscodeDatas=new ArrayList<THscodeData>();
		for (int x = 0; x < list.size(); x++) {
			tHscodeDatas.addAll(iHscodeDataService.getByTheme(list.get(x)));
		}*/
		
		List<THscodeData> tHscodeDatas=iHscodeDataService.getByTheme("2106909090");
		List<String> list=iHscodeWordService.getCategory();
		HashMap<String, HashMap<String, Double>> staticmap=new HashMap<String, HashMap<String, Double>>();
		for (int i = 0; i < list.size(); i++) {
			String temphscode=list.get(i);
			List<THscodeWord> tHscodeWords=iHscodeWordService.getTargetWord(temphscode,WTHRESHOLD);
			HashMap<String, Double> wordmap=new HashMap<String, Double>();
			
			for (THscodeWord tHscodeWord : tHscodeWords) {
				if (tHscodeWord.getWeight()!=0) {
					wordmap.put(tHscodeWord.getWord(), tHscodeWord.getWeight());
				}
				
			}
			staticmap.put(temphscode, wordmap);
		}
		
		
		for (int j = 0; j < tHscodeDatas.size(); j++) {
			
			THscodeData tHscodeData=tHscodeDatas.get(j);
			TreeMap<Double, String> treeMap=new TreeMap<Double,String>();
			String name=tHscodeData.getNameSeg();
			if (name!=null) {
				HashMap<String, Double> dataMap=Cal(name);
				
				for (int k = 0; k < list.size(); k++) {
					String hscode=list.get(k);
					HashMap<String, Double> map=staticmap.get(hscode);
					if (map.isEmpty()) {
						treeMap.put(-0.0,hscode);
					}else {
						
						double sim=calSim(dataMap,map);
						treeMap.put(-sim, hscode);
					}
					
				}
				
				Iterator<Entry<Double, String>> iterator=treeMap.entrySet().iterator();
				int count=1;
				String temp="";
				while (iterator.hasNext()) {
					Map.Entry<java.lang.Double, java.lang.String> entry = (Map.Entry<java.lang.Double, java.lang.String>) iterator
							.next();
					
					if (count>5) {
						break;
					}
					double actualValue=-entry.getKey();
					temp+=entry.getValue()+"="+actualValue+" ";
					count++;
				}
				tHscodeData.setAdvice(temp);
				iHscodeDataService.update(tHscodeData);
			}
			
		}
		
	}
	
	/**
	 * @param string
	 * @return
	 */
	private HashMap<String, Double> Cal(String string) {
		// TODO Auto-generated method stub
		HashMap<String, Double> dataMap=new HashMap<String,Double>();
		List<String> strings=new ArrayList<String>();
		String[] temp=string.split(" ");
		double count=temp.length;
		for (int i = 0; i < count; i++) {
			String tempWord=temp[i];
			if (dataMap.keySet().contains(tempWord)) {
				dataMap.put(tempWord, dataMap.get(tempWord)+1);
			}
			else {
				dataMap.put(tempWord, 1.0);
				strings.add(tempWord);
			}
		}
		
		for (String string2 : temp) {
			dataMap.put(string2, dataMap.get(string2)/count);
		}
		
		return dataMap;
	}

	/**
	 * @param tWeiboWordFrequencies
	 * @param map
	 * @return 
	 */
	private double calSim(HashMap<String, Double> dataMap ,HashMap<String, Double> entrymap) {
		
		List<Double> doc=new ArrayList<Double>();
		List<Double> entry=new ArrayList<Double>();
		Iterator<Entry<String, Double>> iterator=dataMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Double> entry2 = (Map.Entry<java.lang.String, java.lang.Double>) iterator
					.next();
			String word=entry2.getKey();
			double tempWeight=0;
			if (entrymap.keySet().contains(word)) {
				tempWeight=entrymap.get(word);
			}
			doc.add(entry2.getValue());
			entry.add(tempWeight);
		}
		
		double sim=GenSim(doc,entry);
		return sim;
		
	}

	/**
	 * @param document
	 * @param entry
	 * @return
	 */
	private double GenSim(List<Double> document, List<Double> entry) {
		// TODO Auto-generated method stub
		
		double plusResult=0;
		double muldoc=0;
		double mulentry=0;
		for (int i = 0; i < document.size(); i++) {
			plusResult+=document.get(i)*entry.get(i);
			muldoc+=document.get(i)*document.get(i);
			mulentry+=entry.get(i)*entry.get(i);
		}
		if (mulentry==0 || muldoc==0) {
			return 0.0;
		}else {
			return plusResult/(Math.sqrt(mulentry)*Math.sqrt(muldoc));
		}
		
	}

}
