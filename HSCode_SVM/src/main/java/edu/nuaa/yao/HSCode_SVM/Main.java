package edu.nuaa.yao.HSCode_SVM;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.nuaa.yao.libsvm.svm_predict;
import edu.nuaa.yao.libsvm.svm_train;

public class Main {
		public static void main(String[] args) throws IOException {
			ExtractFeatureVector fv=new ExtractFeatureVector();
			fv.loadDataSet();
			System.out.println("1");
			fv.sortCount();
			System.out.println("2");
			fv.getVector();
			System.out.println("3");
			String[] trainArgs = {"-b","1",Constants.trainpath,Constants.modelpath};//directory of training file
			System.out.println(" ------SVM开始-----");
			svm_train.main(trainArgs);
			System.out.println(" ------训练结束-----");
			String[] testArgs = {"-b","1",Constants.testpath, Constants.modelpath, Constants.outputpath};//directory of test file, model file, result file
			System.out.println(" ------开始预测-----");
			svm_predict.main(testArgs);
			System.out.println(" ------预测结束-----");
			fv.getAccuracy();
		}
		
}
