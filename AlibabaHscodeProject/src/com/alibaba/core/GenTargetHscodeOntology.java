package com.alibaba.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

//import com.he.ontology.model.THscodeData;
//import com.he.ontology.service.interfaces.IHscodeDataService;

/**
 * 第一阶段 海关编码本体生成
 * 
 * @author HXF
 * 
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springcontext-config.xml")
public class GenTargetHscodeOntology extends AbstractJUnit4SpringContextTests {*/
public class GenTargetHscodeOntology{
	
	private Integer CountEntryAllNum = 0;
	private static TreeMap<String, Term> StaticTermMap;
	private static TreeMap<String, Double> StaticWordMap;
	private static HashMap<String, Double> StaticOntologyMap;
	private static HashMap<String, Double> StaticEdgeMap;
	private static HashMap<String, Double> StaticEntryMap;

/*	@Autowired
	IHscodeDataService iHscodeDataService;*/

	/**
	 * 只需要关注这个方法，按照这个方法的输入输出格式，自行选择调用方式
	 */
/*	@Test
	public void test() {
		
		double mTHRESHOLD = 0.02;
		double wTHRESHOLD = 0.01;		
		String hsCode = "9619009000";
		List<TProductMessage> list = iHscodeDataService.getByTheme(hsCode);
		HashMap<String, HashMap<String, Double>> staticmap = new HashMap<String, HashMap<String, Double>>();
		TreeMap<String, String> productMap = new TreeMap<String, String>();
		for (TProductMessage tHscodeData : list) {
			if (null == tHscodeData.getName() || "" == tHscodeData.getName()) {
				continue;
			}
			productMap.put(tHscodeData.getId().toString(),
					tHscodeData.getNameSeg());
		}

		HashMap<String, Double> ontologyMap = genTargetHscodeOntology(
				productMap, mTHRESHOLD, wTHRESHOLD);
				
		staticmap.put(hsCode, ontologyMap);

	}*/

	/**
	 * Main function
	 * 
	 * @param wTHRESHOLD
	 * @param mTHRESHOLD
	 * 
	 * @param productList
	 * @return
	 */
	public  static HashMap<String, Double> genTargetHscodeOntology(
			TreeMap<String, String> productMap, double mTHRESHOLD,
			double wTHRESHOLD) {

		HashMap<String, Double> ontologyMap = new HashMap<String, Double>();
		GenTargetHscodeOntology gen=new GenTargetHscodeOntology();
		gen.extractWord(productMap);
		gen.extractEntry(productMap, mTHRESHOLD, wTHRESHOLD);
		gen.updateEntryWeight();
		gen.updateEdgeWeight();
		gen.UpdateWordWeight();
		ontologyMap = StaticOntologyMap;
		return ontologyMap;

	}

	/**
	 * UpdateWordWeight
	 */
	private void UpdateWordWeight() {

		double tempAll = 0;
		List<String> ontologylist = convertToList(StaticOntologyMap);

		for (String ontology : ontologylist) {

			double temp = calOntologyWeight(ontology);
			tempAll += temp;
			StaticOntologyMap.put(ontology, temp);

		}

		for (int i = 0; i < ontologylist.size(); i++) {

			String ontology = ontologylist.get(i);
			StaticOntologyMap.put(ontology, StaticOntologyMap.get(ontology)
					/ tempAll);
			// THscodeWord tWeiboWord=tWeiboWords.get(i);
			// double result=list.get(i)/tempAll;
			// if (result>0) {
			// tWeiboWord.setWeight(result);
			// iHscodeWordService.update(tWeiboWord);
			// }
		}
	}

	/**
	 * @param ontology
	 * @return
	 */
	private double calOntologyWeight(String ontology) {
		// TODO Auto-generated method stub

		double weight = 0;
		double temp = 0;
		int count = 0;
		// String word=tWeiboWord.getWord();
		List<String> edgelist = convertToList(StaticEdgeMap);

		for (String edge : edgelist) {

			String[] edgeTemp = edge.split("->");
			String source = edgeTemp[0];
			String target = edgeTemp[1];
			if (source.equals(ontology) || target.equals(ontology)) {
				count++;
				temp += StaticEdgeMap.get(edge);
			}
		}

		weight = temp / (double) count;
		return weight;
	}

	/**
	 * updateEdgeWeight
	 */
	private void updateEdgeWeight() {
		// TODO Auto-generated method stub

		// List<THscodeEdge> tWeiboEdges=iWeiboEdgeService.getTheme(hscode);
		// List<THscodeEntry>
		// tWeiboEntries=iWeiboEntryService.geTWeiboEntries(hscode);

		List<String> entrylist = convertToList(StaticEntryMap);
		List<String> edgelist = convertToList(StaticEdgeMap);
		double tempAll = 0;

		for (String edge : edgelist) {

			double temp = 0;
			for (String entry : entrylist) {
				if (true == jungle(edge, entry)) {
					temp += StaticEntryMap.get(entry);
				}
			}
			tempAll += temp;
			StaticEdgeMap.put(edge, temp);
			// list.add(temp);
			/*
			 * tWeiboEdge.setWeight(temp); iWeiboEdgeService.update(tWeiboEdge);
			 */
		}

		for (int i = 0; i < edgelist.size(); i++) {

			String edgeName = edgelist.get(i);
			StaticEdgeMap.put(edgeName, StaticEdgeMap.get(edgeName) / tempAll);
			// THscodeEdge tWeiboEdge=tWeiboEdges.get(i);
			// tWeiboEdge.setWeight(list.get(i)/tempAll);
			// iWeiboEdgeService.update(tWeiboEdge);
		}
	}

	/**
	 * @param edge
	 * @param entry
	 * @return
	 */
	private boolean jungle(String edge, String entry) {

		// TODO Auto-generated method stub
		boolean flag = false;
		String[] edgeTemp = edge.split("->");
		String source = edgeTemp[0];
		String target = edgeTemp[1];
		// String wordTemp=entry.getEntry();

		int num = entry.indexOf("]");
		String words = entry.substring(1, num);
		String[] word = words.split(",");

		if (source.equals(word[0]) && target.equals(word[1])) {
			flag = true;
		}
		if (source.equals(word[1]) && target.equals(word[2])) {
			flag = true;
		}

		return flag;
	}

	/**
	 * updateEntryWeight
	 */
	private void updateEntryWeight() {

		// TODO Auto-generated method stub
		Iterator<Entry<String, Double>> entryIter = StaticEntryMap.entrySet()
				.iterator();
		while (entryIter.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Double> entry = (Map.Entry<java.lang.String, java.lang.Double>) entryIter
					.next();
			double weight = entry.getValue() / (double) CountEntryAllNum;
			StaticEntryMap.put(entry.getKey(), weight);
		}
		// List<THscodeEntry>
		// tWeiboEntries=iWeiboEntryService.geTWeiboEntries(hscode);
		// int all=countAll(tWeiboEntries);
		// for (THscodeEntry tWeiboEntry : tWeiboEntries) {
		// double weight=(double)tWeiboEntry.getNum()/(double)all;
		// tWeiboEntry.setWeight(weight);
		// iWeiboEntryService.update(tWeiboEntry);
		// }
	}

	/**
	 * @param productMap
	 * @param mTHRESHOLD
	 * @param wTHRESHOLD
	 */
	private void extractEntry(TreeMap<String, String> productMap,
			Double mTHRESHOLD, Double wTHRESHOLD) {
		// TODO Auto-generated method stub

		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		HashMap<String, Double> edgeMap = new HashMap<String, Double>();
		HashMap<String, Double> entryMap = new HashMap<String, Double>();

		double threshold = calThreshold(productMap.size(), wTHRESHOLD);
		HashMap<String, Double> ontologyMap = getOntology(threshold);
		StaticOntologyMap = ontologyMap;
		List<String> ontologyWords = convertToList(ontologyMap);

		for (int i = 0; i < ontologyWords.size(); i++) {

			Term termI = StaticTermMap.get(ontologyWords.get(i));
			// List<THscodeWordFrequency>
			// tList=iWordFrequService.geTWordFrequs(tWeiboWords.get(i).getWord(),hscode);
			// Set<THscodeWordFrequency> tSet=convert(tList);

			for (int j = i + 1; j < ontologyWords.size(); j++) {
				Term termJ = StaticTermMap.get(ontologyWords.get(j));
				double messageValue = calMessageValue(termI, termJ);
				// int num=countsim(termI, termJ);
				// double Hinf=(double)num/(double)(tSet.size()+tSet2.size());
				if (messageValue > mTHRESHOLD) {

					System.out.println("[" + termI.wordname + ","
							+ termJ.wordname + "]=" + messageValue);

					list1.add(termI.wordname);
					list2.add(termJ.wordname);

					edgeMap.put(termI.wordname + "->" + termJ.wordname, 0.0);

				}
			}
		}

		StaticEdgeMap = edgeMap;
		List<String> resultList = countHinf(list1, list2);

		for (int i = 0; i < resultList.size(); i++) {

			// THscodeEntry tWeiboEntry=new THscodeEntry();
			// tWeiboEntry.setEntry(resultList.get(i));
			// tWeiboEntry.setHscode(hscode);
			int count = 0;
			String currentEntryKey = productMap.firstKey();
			while (currentEntryKey != null) {

				String singleProductContent = productMap.get(currentEntryKey);
				// String temp=tWeiboContentSeg.getNameSeg();
				int end = StringUtils.indexOf(resultList.get(i), "]");
				String tempstr = StringUtils.substring(resultList.get(i), 1,
						end);
				String[] strs = tempstr.split(",");
				if (strs.length >= 3) {
					if (singleProductContent.contains(strs[0])
							&& singleProductContent.contains(strs[1])
							&& singleProductContent.contains(strs[2])) {
						count++;
					}
				}
				// if (docs>threshold) {
				// ontologyMap.put(currentEntryKey, 0.0);
				// }
				currentEntryKey = productMap.higherKey(currentEntryKey);
			}

			// for (THscodeData tWeiboContentSeg : list) {
			// String temp=tWeiboContentSeg.getNameSeg();
			// int end=StringUtils.indexOf(resultList.get(i), "]");
			// String tempstr=StringUtils.substring(resultList.get(i), 1, end);
			// String[] strs=tempstr.split(",");
			// if (strs.length>=3) {
			// if (temp.contains(strs[0]) && temp.contains(strs[1]) &&
			// temp.contains(strs[2])) {
			// count++;
			// }
			// }
			// }
			// tWeiboEntry.setNum(count);
			CountEntryAllNum += count;

			if (count > 0) {
				entryMap.put(resultList.get(i), (double) count);
			}
			// System.out.println(resultList.get(i));
		}
		StaticEntryMap = entryMap;
	}

	/**
	 * @param termI
	 * @param termJ
	 * @return
	 */
	private double calMessageValue(Term termI, Term termJ) {
		// TODO Auto-generated method stub
		Set<String> resSet = termI.inDocInfo.keySet();
		Set<String> resSet2 = termJ.inDocInfo.keySet();
		Set<String> resSet3 = new HashSet<String>();

		resSet3.addAll(resSet);
		resSet3.retainAll(resSet2);
		double Hinf = (double) resSet3.size()
				/ (double) (resSet.size() + resSet2.size());
		return Hinf;
	}

	/**
	 * @param ontologyMap
	 * @return
	 */
	private List<String> convertToList(HashMap<String, Double> ontologyMap) {
		// TODO Auto-generated method stub

		List<String> ontologyWords = new ArrayList<String>();
		Iterator<String> ontoLogyWordIter = ontologyMap.keySet().iterator();
		while (ontoLogyWordIter.hasNext()) {
			String string = (String) ontoLogyWordIter.next();
			ontologyWords.add(string);
		}
		return ontologyWords;
	}

	/**
	 * @param threshold
	 * @return
	 */
	private HashMap<String, Double> getOntology(double threshold) {
		// TODO Auto-generated method stub

		HashMap<String, Double> ontologyMap = new HashMap<String, Double>();
		String currentWordKey = StaticWordMap.firstKey();
		while (currentWordKey != null) {

			Double docs = StaticWordMap.get(currentWordKey);
			if (docs > threshold) {
				ontologyMap.put(currentWordKey, 0.0);
			}

			currentWordKey = StaticWordMap.higherKey(currentWordKey);
		}
		return ontologyMap;
	}

	/**
	 * 如果某词条第二个词和某词条第一个词相同，生成三个词组成的词条
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	private List<String> countHinf(List<String> list1, List<String> list2) {

		List<String> resList = new ArrayList<String>();
		for (int i = 0; i < list2.size(); i++) {
			for (int j = i + 1; j < list1.size(); j++) {
				if (list1.get(j).equals(list2.get(i))) {
					resList.add("[" + list1.get(i) + "," + list2.get(i) + ","
							+ list2.get(j) + "]");
				}
			}
		}

		return resList;

	}

	/**
	 * @param size
	 * @param wTHRESHOLD
	 * @return
	 */
	private double calThreshold(int size, Double wTHRESHOLD) {

		double thresholdnum = wTHRESHOLD * size;
		return thresholdnum;

	}

	/**
	 * @param productMap
	 */
	private void extractWord(TreeMap<String, String> productMap) {
		// TODO Auto-generated method stub

		TreeMap<String, Term> termMap = setTerms(productMap);
		StaticTermMap = termMap;

	}

	/**
	 * @param productMap
	 * @return
	 */
	private TreeMap<String, Term> setTerms(TreeMap<String, String> productMap) {
		// TODO Auto-generated method stub

		TreeMap<String, Term> termMap = new TreeMap<String, Term>();
		TreeMap<String, Double> wordMap = new TreeMap<String, Double>();

		String currentArticleKey = productMap.firstKey();
		while (currentArticleKey != null) {

			String singleProductContent = productMap.get(currentArticleKey);
			String[] termArray = singleProductContent.split(" ");

			// 对一个词进行处理
			for (String t : termArray) {

				if ("".equals(t) || " ".equals(t) || "\"".equals(t)) {
					continue;
				}
				if (!wordMap.keySet().contains(t)) {

					// THscodeWord newWeiboWord = new THscodeWord();
					wordMap.put(t, 1.0);

					Term term = new Term();
					term.inDocInfo.put(currentArticleKey, 1);
					term.wordname = t;
					termMap.put(t, term);
					// newWeiboWord.setNum(1);
					// newWeiboWord.setWord(t);
					// newWeiboWord.setHscode(hscode);
					// words.put(t, newWeiboWord);

				} else {
					// THscodeWord oldWord = words.get(t);
					// Term oldTerm = termMap.get(t);
					// oldWord.setNum(oldWord.getNum() + 1);

					Term oldTerm = termMap.get(t);

					if (null == oldTerm.inDocInfo.get(currentArticleKey)) {
						oldTerm.inDocInfo.put(currentArticleKey, 1);
						wordMap.put(t, wordMap.get(t) + 1);
					} else {
						oldTerm.inDocInfo.put(currentArticleKey,
								oldTerm.inDocInfo.get(currentArticleKey) + 1);
					}
					// words.put(t, oldWord);
					termMap.put(t, oldTerm);
				}
			}

			currentArticleKey = productMap.higherKey(currentArticleKey);
		}
		StaticWordMap = wordMap;
		return termMap;

	}

	/**
	 * 用于记录倒排索引记录
	 * 
	 * @author HXF
	 * 
	 */
	public class Term {

		// 该term所对应的文档及在每个文档中出现的次数
		public Map<String, Integer> inDocInfo;
		public String wordname;

		public Term() {
			wordname = null;
			inDocInfo = new HashMap<String, Integer>();
		}

	}
}
