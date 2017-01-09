/**
 * 
 */
package com.alibaba.split.jieba;

import java.nio.file.Paths;
import java.util.List;

import com.alibaba.split.jieba.JiebaSegmenter.SegMode;

import junit.framework.TestCase;

/**
 * @author matrix
 * 
 */
public class Jieba extends TestCase {
    

    @Override
    protected void setUp() throws Exception {
        WordDictionary.getInstance().init(Paths.get("conf"));
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * @param raw
     * @return
     */
    public static String testCutForSearch(String raw) {
    	JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> tokens = segmenter.process(raw, SegMode.INDEX);
        String result=forMat(tokens);
        return result;
    }


	private static String forMat(List<SegToken> tokens) {
		// TODO Auto-generated method stub
		String result="";
		for (SegToken segToken : tokens) {
			result+=segToken.toString()+" ";
		}
		return result;
	}

}
