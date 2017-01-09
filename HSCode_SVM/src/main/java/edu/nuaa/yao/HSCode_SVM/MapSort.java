package edu.nuaa.yao.HSCode_SVM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MapSort {
	
	 public static void main(String[] args) {  
	        Map<String, Integer> map = new TreeMap<String, Integer>();  
	        map.put("KFC", 2);  
	        map.put("WNBA", 4);  
	        map.put("NBA", 5);  
	        map.put("CBA", 1);  
	       // Map<String, Integer> resultMap = sortMapByIntegerValue(map); //��Value��������  
	       
	 }
	          
	/**
	 * ʹ�� Map��value��������
	 * @param map
	 * @return
	 */
	public static TreeMap<String, Double> sortMapByValue(TreeMap<String, Double> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		TreeMap<String, Double> sortedMap = new TreeMap<String, Double>();
		List<Map.Entry<String,Double>> list = new ArrayList<Map.Entry<String,Double>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Double>>() {
            //升序排序
            public int compare(Entry<String, Double> o1,
                    Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }});
        Iterator<Map.Entry<String, Double>> iter = list.iterator();
        Map.Entry<String, Double> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}
	public static List<Entry<String, Integer>> sortMapByIntegerValue(TreeMap<String, Integer> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>();
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }});
        
		return list;
		
	}
}





//�Ƚ�����
 class MapValueComparator implements Comparator<Map.Entry<String, Double>> {
	public int compare(Entry<String, Double> me1, Entry<String, Double> me2) {
		return me2.getValue().compareTo(me1.getValue());
	}
	
	
}
//�Ƚ�����
class MapIntValueComparator implements Comparator<Map.Entry<String, Integer>> {
	public int compare(Entry<String, Integer> me1, Entry<String, Integer> me2) {
		return me2.getValue().compareTo(me1.getValue());
	}
	
	
}