package com.alibaba.action;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.core.GiveProductHscodeAdvice;
import com.alibaba.split.jieba.Jieba;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class GiveHscodeTest extends AbstractJUnit4SpringContextTests{
	
	@Test
	public void test() {
		
		/*第二阶段输入*/
		String productName = "Lansinoh防溢乳垫60枚/盒";
		
		/*获取staticMap，即第一阶段的输出*/
		HashMap<String, HashMap<String, Double>> staticMap = new HashMap<String, HashMap<String, Double>>();
		
		/*第二阶段的输出*/
		System.out.println(GiveProductHscodeAdvice.calAndGiveTopFiveAdvice(Jieba.testCutForSearch(productName), staticMap));
	}
}
