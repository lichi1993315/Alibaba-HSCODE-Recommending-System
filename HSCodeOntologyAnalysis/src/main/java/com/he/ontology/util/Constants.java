package com.he.ontology.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 系统常量类
 * @author KOC-RY
 *
 */
public class Constants {
	public final static int COLLECT_DATA_REFRESH_CYCLE = 30;
	public final static DateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String NLPIR_DATA_DIR="src/main/resources";
	public static int CHAR_SET=1;//default is GBK_CODE (GBK encoding), and it can be set with UTF8_CODE (UTF8 encoding) and BIG5_CODE (BIG5 encoding).
	public static int WordMean=0;//默认带词性
	public static String USERDICT="src/main/resources/userDic.txt";//不带用户字典
	public static String STOPTXTPATH="src/main/resources/stopWord.txt";
//	public static Double WTHRESHOLD=0.1;//特征词阈值
//	public static Double MTHRESHOLD=0.1;//共现概率阈值
}
