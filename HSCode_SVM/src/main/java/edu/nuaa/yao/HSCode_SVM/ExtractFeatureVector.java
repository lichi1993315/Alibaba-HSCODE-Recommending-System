package edu.nuaa.yao.HSCode_SVM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;



public class ExtractFeatureVector {
	HashMap<String, TreeMap<String, Integer>> map=new HashMap<String,TreeMap<String,Integer>>();
	//统计每个hscode下每个词的词数
	TreeMap word=new TreeMap<String,Integer>();
	//存放词与词频
	Set<String> keyword=new HashSet<String>();
	//存放特征向量
	List<Word> keymap=new ArrayList<Word>();
	//存放特征词及tfidf
	
	public void loadDataSet() throws IOException {
		// TODO Auto-generated method stub
		String inputpath=Constants.inputpath;
		File rootFile=new File(inputpath);
		processFile(rootFile);
	}

	public void processFile(File rootFile) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf=new BufferedReader(new FileReader(rootFile));
		String lineTxt = null;
		int pos=1;
		while((lineTxt = bf.readLine()) != null){
             String[] line=lineTxt.split(" ");
//             System.out.println(line[0]);
             String hscode=line[0];
             
             
             TreeMap<String,Integer> word=null;
             if(!map.containsKey(hscode)){
             word=new TreeMap<String,Integer>();
             for(int i=1;i<line.length;i++){
             	String key=line[i];
             	if (word.get(key)!=null){
             		int value= ((Integer) word.get(key)).intValue();
             		value++;
             		word.put(key, value);
             	}else{
             		word.put(key, new Integer(1));
             	}
             }
             map.put(hscode, word);
             }
             else{
            //	System.out.println("sss");
             	word = map.get(hscode);
             	for(int i=1;i<line.length;i++){
             		String key=line[i];
                 	if (word.get(key)!=null){
                 		int value= ((Integer) word.get(key)).intValue();
                 		value++;
                 		word.put(key, value);
                 	}else{
                 		word.put(key, new Integer(1));
                 	}
             	}
             	
             	map.put(hscode, word);
             	
             }
		}
		bf.close();
//		for(Entry<String, TreeMap<String, Integer>> entry:map.entrySet()){
////        	System.out.println("hscode:"+entry.getKey());
//        	TreeMap<String, Integer> value = entry.getValue();
//        	for(Entry<String,Integer> e:value.entrySet()){
////        	System.out.println("单词"+e.getKey()+" 词频是"+e.getValue());
//        	}
//        }
	}

	public void sortCount() {
		// TODO Auto-generated method stub
		for(Entry<String, TreeMap<String, Integer>> entry:map.entrySet()){
			TreeMap<String, Integer> tmap = entry.getValue();
			List<Entry<String, Integer>> sorttmap = MapSort.sortMapByIntegerValue(tmap);
			 int flag=0;
             for(Entry<String, Integer> word:sorttmap){ 
             	flag++;
             	keyword.add(word.getKey());
//                 System.out.println(mapping.getKey()+":"+mapping.getValue()); 
             	if(flag==5)
             		break;
            } 
		}
	
	}

	public void getVector() throws IOException {
		// TODO Auto-generated method stub
		int i=1;
		for(String key:keyword){
			Word w=new Word();
			double tf=calTF(key);
			double idf=calIDF(key);
			double tfidf=tf*idf;
			w.setId(i);
			i++;
			w.setName(key);
			w.setTfidf(tfidf);
			if(word.containsKey(key)){
				w.setCount((Integer) word.get(key));
			}
			keymap.add(w);
		}
		FileHelper.writeTrainFile(keymap);
		FileHelper.writeTestFile(keymap);
	}

	private double calIDF(String key) throws IOException {
		// TODO Auto-generated method stub
		List<String> in = FileHelper.readFile(Constants.inputpath);
		int doccount=0;
		for(String line:in){
			String[] l = line.split(" ");
			for(String word:l){
				if (word.equals(key)){
					doccount++;
				}
			}
		}
		double idf=Math.log((double)in.size()/doccount);
		return idf;
	}

	public double calTF(String key) throws IOException {
		// TODO Auto-generated method stub
		List<String> in = FileHelper.readFile(Constants.inputpath);
		StringBuffer sb=new StringBuffer();
		for (String s:in){
			sb.append(s.trim()+" ");
		}
		String[] input = sb.toString().trim().split(" ");
		int num=0;
		for(String cutword:input){
			if (cutword.equals(key)){
				num++;
			}
		}
		double tf=(double)num/input.length;
		return tf;
	}

	public void getAccuracy() throws IOException {
		// TODO Auto-generated method stub
		List<String> in = FileHelper.readFiles(Constants.outputpath);
		List<String> testhscode=FileHelper.readFiles(Constants.testpath);
		List<String> hslist=new ArrayList<String>();
		for(String code:testhscode){
			hslist.add(code.split(" ")[0]);
		}
		List<TreeMap<String,Double>> finallist=new ArrayList<TreeMap<String,Double>>();
		String[] label = in.get(0).split(" ");
		
		for (int i=1;i<in.size();i++){
			String[] probability=in.get(i).split(" ");
			TreeMap<String,Double> tm=new TreeMap<String,Double>();
//			String hscode = probability[0];
//			System.out.println(hscode);
//			hslist.add(hscode);
			for(int j=1;j<probability.length;j++){
				Double p = Double.valueOf(probability[j]);
				tm.put(label[j]+"", p);
//				System.out.println(label[j]+" "+ p);
			}
			
			finallist.add(tm);
		}
		List<String> outputlist=new ArrayList<String>();
		int right=0;
		for(int j=0;j<finallist.size();j++){
			TreeMap<String, Double> m = finallist.get(j);
			
			String hs = hslist.get(j);
//			System.out.println(hs);
//			System.out.println(j);
//			String hscode = hs;
//			System.out.println(hscode);
			String hscode = changeToHSCode(hs);
			StringBuffer sb=new StringBuffer();
			sb.append(hscode+" ");
			List<Map.Entry<String,Double>> list = new ArrayList<Map.Entry<String,Double>>(m.entrySet());
            //然后通过比较器来实现排序
            Collections.sort(list,new Comparator<Map.Entry<String,Double>>() {
                //升序排序
            		public int compare(Entry<String, Double> o1,
                        Entry<String, Double> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }});
			 
			 
			int point=0;
			int flag=0;
			 for(Map.Entry<String,Double> mapping:list){ 
              	flag++;
              	
              	if(hs.equals(mapping.getKey())){
              		point=1;
              		right++;
              	} 
              	sb.append(changeToHSCode(mapping.getKey())+"  "+mapping.getValue()+" ");
//                  System.out.println(mapping.getKey()+":"+mapping.getValue()); 
              	if(flag==5)
              		{
              		sb.append(","+point);
              		break;
              		}
             } 
			outputlist.add(sb.toString());
		}
		System.out.println("准确率"+(double)right/finallist.size());
		FileHelper.writeFile(outputlist,Constants.finaloutputpath);
	}

	private String changeToHSCode(String key) throws IOException {
		// TODO Auto-generated method stub
		
		HashMap<String, String> index=FileHelper.readindex(Constants.indexputpath);
		String hscode = null;
		for(Entry<String,String> entry:index.entrySet()){
			if(key.equals(entry.getValue())){
				hscode=entry.getKey();
			}
		}
		return hscode;
	}

	
}
