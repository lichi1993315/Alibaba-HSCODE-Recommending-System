package edu.nuaa.yao.HSCode_SVM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

public class FileHelper {
	
	static HashMap<String,Integer> holi=new HashMap<String, Integer>();
	String filePath;
	File fileDir=null;
	File file=null;
	static int pos=1;
	RandomAccessFile rfile;
	public FileHelper(){
		
	}
	public FileHelper(String filePath) {
		
		file=new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setFilePath(String filePath){
		file=new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void wirteToFile(String... strings) {
		
		String record="";
		for(String str:strings){
			record+=str+"	";
		}
		record+="\r\n";
		
		try {
			rfile = new RandomAccessFile(file, "rw");
			long fileLen=rfile.length();
			rfile.seek(fileLen);
			rfile.writeBytes(record);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//��randomAccess��д
	public void writeToFileNonRandom(String words){
		
		OutputStream out=null;
		try {
			out=new FileOutputStream(file);
			out.write(words.getBytes());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void closeRsrc(){
		try {
			rfile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeTrainFile(List<Word> keymap) throws IOException {
		// TODO Auto-generated method stub
		String inputpath=Constants.inputpath;
		File rootFile=new File(inputpath);
		BufferedReader bf=new BufferedReader(new FileReader(rootFile));
		String line;
		List<String> trainoutput=new ArrayList<String>();
		
		while((line = bf.readLine()) != null){
			String[] content = line.split(" ");
			String hscode = content[0];
			if(holi.containsKey(hscode)==false){
           	 holi.put(hscode, pos);
           	 pos++;
            }
			StringBuffer sb=new StringBuffer();
			
			for(int i=1;i<content.length;i++){
				for(Word keyword:keymap){
					if(content[i].equals(keyword.getName())){
						sb.append(keyword.getId()+":"+keyword.getTfidf()+" ");
					}
				}
				
				}
			if(sb.toString().trim()!=null){
				String out=holi.get(hscode)+" "+sb.toString();
//				System.out.println(out);
				trainoutput.add(out);
				
			}
//			writeIndex(holi,Constants.indexputpath);
			writeFile(trainoutput,Constants.trainpath);
		}
	}
	private static void writeIndex(HashMap<String, Integer> holi2, String indexputpath) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream fos=new FileOutputStream(new File(indexputpath));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        //简写如下：
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

        for (Entry<String, Integer> entry : holi2.entrySet()) {
        	bw.write(entry.getKey()+" "+entry.getValue()+"\n");
        	
        }
        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
        bw.close();
        osw.close();
        fos.close();
	}
	public static void writeFile(List<String> trainoutput,String path) throws IOException {
		// TODO Auto-generated method stub
		 	FileOutputStream fos=new FileOutputStream(new File(path));
	        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
	        BufferedWriter  bw=new BufferedWriter(osw);
	        //简写如下：
	        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

	        for(String arr:trainoutput){
	            bw.write(arr+"\n");
	        }
	        
	        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
	        bw.close();
	        osw.close();
	        fos.close();
	}
	public static List<String> readFile(String inputpath) throws IOException {
		// TODO Auto-generated method stub
		String inpath=Constants.inputpath;
		File rootFile=new File(inpath);
		BufferedReader bf=new BufferedReader(new FileReader(rootFile));
		String linetxt = null;
		List<String> l=new ArrayList<String>();
		while((linetxt = bf.readLine()) != null){
			String[] t=linetxt.split(" ");
			StringBuffer sb=new StringBuffer();
			for(int i=1;i<t.length;i++){
				sb.append(t[i]+" ");
			}
			l.add(sb.toString().trim());
		}
		return l;
	}
	public static void writeFiles(List<String> outputlist) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream fos=new FileOutputStream(new File(Constants.finaloutputpath));
        OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter  bw=new BufferedWriter(osw);
        //简写如下：
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
        //        new FileOutputStream(new File("E:/phsftp/evdokey/evdokey_201103221556.txt")), "UTF-8"));

        for(String arr:outputlist){
            bw.write(arr+"\n");
        }
        
        //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
        bw.close();
        osw.close();
        fos.close();
	}
	public static List<String> readFiles(String outputpath) throws IOException {
		// TODO Auto-generated method stub
		
		File rootFile=new File(outputpath);
		BufferedReader bf=new BufferedReader(new FileReader(rootFile));
		String linetxt = null;
		List<String> sb=new ArrayList<String>();
		while((linetxt = bf.readLine()) != null){
			sb.add(linetxt);
		}
		return sb;
	}
	public static HashMap<String, String> readindex(String indexputpath) throws IOException {
		// TODO Auto-generated method stub
		String inpath=Constants.indexputpath;
		File rootFile=new File(inpath);
		BufferedReader bf=new BufferedReader(new FileReader(rootFile));
		String linetxt = null;
		HashMap<String,String> map=new HashMap<String, String>();
		while((linetxt = bf.readLine()) != null){
			String[] t=linetxt.split(" ");
			map.put(t[0], t[1]);
		}
		return map;
	}
	public static void writeTestFile(List<Word> keymap) throws IOException {
		// TODO Auto-generated method stub
		String inputpath=Constants.testfilepath;
		File rootFile=new File(inputpath);
		BufferedReader bf=new BufferedReader(new FileReader(rootFile));
		String line;
		List<String> trainoutput=new ArrayList<String>();
		while((line = bf.readLine()) != null){
			String[] content = line.split(" ");
			String hscode = content[0];
			if(holi.containsKey(hscode)==false){
           	 holi.put(hscode, pos);
           	 pos++;
            }
			StringBuffer sb=new StringBuffer();
			
			for(int i=1;i<content.length;i++){
				for(Word keyword:keymap){
					if(content[i].equals(keyword.getName())){
						sb.append(keyword.getId()+":"+keyword.getTfidf()+" ");
					}
				}
				
				}
			if(sb.toString().trim()!=null){
				String out=holi.get(hscode)+" "+sb.toString();
//				System.out.println(out);
				trainoutput.add(out);
				
			}
			writeIndex(holi,Constants.indexputpath);
			writeFile(trainoutput,Constants.testpath);
		}
	}


}
