package com.alibaba.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 * 第二阶段，海关编码的预测
 * 
 * @author HXF
 * 
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class GiveProductHscodeAdvice extends AbstractJUnit4SpringContextTests {*/
public class GiveProductHscodeAdvice{
/*	@Autowired
	IHscodeWordService iHscodeWordService;
	private final Double WTHRESHOLD = 0.01;

	*//**
	 * 只需要关注这个方法，按照这个方法的输入输出格式，自行选择调用方式
	 *//*
	@Test
	public void test() {
		
		String productName = "美国 直 邮 Lansinoh 防 溢 乳 垫 抛弃 型 一次性 隔 奶 垫 超薄 乳 贴 60 片";

		HashMap<String, HashMap<String, Double>> staticMap = new HashMap<String, HashMap<String, Double>>();
		List<String> list = iHscodeWordService.getCategory();
		for (int i = 0; i < list.size(); i++) {
			String temphscode = list.get(i);
			List<THscodeWord> tHscodeWords = iHscodeWordService.getTargetWord(
					temphscode, WTHRESHOLD);
			HashMap<String, Double> wordmap = new HashMap<String, Double>();

			for (THscodeWord tHscodeWord : tHscodeWords) {
				if (tHscodeWord.getWeight() != 0) {
					wordmap.put(tHscodeWord.getWord(), tHscodeWord.getWeight());
				}

			}
			staticMap.put(temphscode, wordmap);
		}

		System.out.println(calAndGiveTopFiveAdvice(productName, staticMap));
	}*/

	/**
	 * Main Function
	 * 
	 * @param productName
	 * @return
	 */
	public static String calAndGiveTopFiveAdvice(String productName,
			HashMap<String, HashMap<String, Double>> staticMap) {

		// List<THscodeData> tHscodeDatas =
		// iHscodeDataService.getByTheme("2106909090");
		// for (int j = 0; j < tHscodeDatas.size(); j++) {
		// THscodeData tHscodeData = tHscodeDatas.get(j);
		// String name = tHscodeData.getNameSeg();

		String topFiveAdvice = "";
		TreeMap<Double, String> treeMap = new TreeMap<Double, String>();
		GiveProductHscodeAdvice give=new GiveProductHscodeAdvice();
		
		if (productName != null) {
			HashMap<String, Double> dataMap = give.Cal(productName);

			Iterator<Entry<String, HashMap<String, Double>>> staticMapIter = staticMap
					.entrySet().iterator();
			while (staticMapIter.hasNext()) {
				Map.Entry<java.lang.String, java.util.HashMap<java.lang.String, java.lang.Double>> entry = (Map.Entry<java.lang.String, java.util.HashMap<java.lang.String, java.lang.Double>>) staticMapIter
						.next();
				HashMap<String, Double> map = entry.getValue();
				String hscode = entry.getKey();

				if (map.isEmpty()) {
					treeMap.put(-0.0, hscode);
				} else {

					double sim = give.calSim(dataMap, map);
					treeMap.put(-sim, hscode);
				}
			}

			// for (int k = 0; k < list.size(); k++) {
			// String hscode = list.get(k);
			// HashMap<String, Double> map = staticmap.get(hscode);
			// if (map.isEmpty()) {
			// treeMap.put(-0.0, hscode);
			// } else {
			//
			// double sim = calSim(dataMap, map);
			// treeMap.put(-sim, hscode);
			// }
			//
			// }

			Iterator<Entry<Double, String>> iterator = treeMap.entrySet()
					.iterator();
			int count = 1;
			String temp = "";
			while (iterator.hasNext()) {
				Map.Entry<java.lang.Double, java.lang.String> entry = (Map.Entry<java.lang.Double, java.lang.String>) iterator
						.next();

				if (count > 5) {
					break;
				}
				double actualValue = -entry.getKey();
				temp += entry.getValue() + "=" + actualValue + " ";
				count++;
			}
			topFiveAdvice = productName+":("+temp+")";
		}
		return topFiveAdvice;

		// }

	}

	/**
	 * @param string
	 * @return
	 */
	private HashMap<String, Double> Cal(String string) {
		// TODO Auto-generated method stub
		HashMap<String, Double> dataMap = new HashMap<String, Double>();
		List<String> strings = new ArrayList<String>();
		String[] temp = string.split(" ");
		double count = temp.length;
		for (int i = 0; i < count; i++) {
			String tempWord = temp[i];
			if (dataMap.keySet().contains(tempWord)) {
				dataMap.put(tempWord, dataMap.get(tempWord) + 1);
			} else {
				dataMap.put(tempWord, 1.0);
				strings.add(tempWord);
			}
		}

		for (String string2 : temp) {
			dataMap.put(string2, dataMap.get(string2) / count);
		}

		return dataMap;
	}

	/**
	 * @param tWeiboWordFrequencies
	 * @param map
	 * @return
	 */
	private double calSim(HashMap<String, Double> dataMap,
			HashMap<String, Double> entrymap) {

		List<Double> doc = new ArrayList<Double>();
		List<Double> entry = new ArrayList<Double>();
		Iterator<Entry<String, Double>> iterator = dataMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Double> entry2 = (Map.Entry<java.lang.String, java.lang.Double>) iterator
					.next();
			String word = entry2.getKey();
			double tempWeight = 0;
			if (entrymap.keySet().contains(word)) {
				tempWeight = entrymap.get(word);
			}
			doc.add(entry2.getValue());
			entry.add(tempWeight);
		}

		double sim = GenSim(doc, entry);
		return sim;

	}

	/**
	 * @param document
	 * @param entry
	 * @return
	 */
	private double GenSim(List<Double> document, List<Double> entry) {
		// TODO Auto-generated method stub

		double plusResult = 0;
		double muldoc = 0;
		double mulentry = 0;
		for (int i = 0; i < document.size(); i++) {
			plusResult += document.get(i) * entry.get(i);
			muldoc += document.get(i) * document.get(i);
			mulentry += entry.get(i) * entry.get(i);
		}
		if (mulentry == 0 || muldoc == 0) {
			return 0.0;
		} else {
			return plusResult / (Math.sqrt(mulentry) * Math.sqrt(muldoc));
		}

	}

}
