package com.core.sentiment_analysis;
import java.io.File;
import java.io.IOException;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.ml.naivebayes.NaiveBayesEvalParameters;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class Train_model 
{
	DoccatModel model;
	public void trainModel() {
	    	
	        MarkableFileInputStreamFactory dataIn = null;
	        try {
	            dataIn = new MarkableFileInputStreamFactory(new File("C:/Users/Dell/workspace1/Sentiment_Analysis_V1.0/samplecomments.txt"));
	            ObjectStream lineStream = new PlainTextByLineStream((InputStreamFactory) dataIn, "UTF-8");
	            ObjectStream sampleStream = new DocumentSampleStream(lineStream);
	            // Specifies the minimum number of times a feature must be seen
	            TrainingParameters tp = new TrainingParameters();
	            tp.put(TrainingParameters.CUTOFF_PARAM, 0+"");
	            tp.put(TrainingParameters.ITERATIONS_PARAM,10+"");
	            tp.put(TrainingParameters.ALGORITHM_PARAM, NaiveBayesTrainer.NAIVE_BAYES_VALUE);
	            DoccatFactory df = new DoccatFactory();
	            model = DocumentCategorizerME.train("en", sampleStream, tp,
	                    df);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (dataIn != null) {
	                try {
	                    dataIn.createInputStream();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	   }
	public int classifyNewTweet(String string) throws IOException {
    	String[] comments = new String[string.length()];
    	comments = string.split(" ");
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(model);
        double[] outcomes = myCategorizer.categorize(comments);
        String category = myCategorizer.getBestCategory(outcomes);
        System.out.print("\n----------------COMMENT-----------------\n" + string + " ===> ");
        if (category.equalsIgnoreCase("0")) 
        {
        	System.out.println(" NEGATIVE ");
            return 0;
        } 
        else if(category.equalsIgnoreCase("1"))  
        {
            System.out.println(" POSITIVE ");
            return 1;
        }
        else
        {
        	System.out.println(" NEUTRAL ");
            return 2;
        }

    }
}
