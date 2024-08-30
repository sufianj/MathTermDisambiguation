package wcl.math;

import it.uniroma1.lcl.jlt.util.Language;
import it.uniroma1.lcl.wcl.data.dataset.AnnotatedDataset;
import it.uniroma1.lcl.wcl.data.dataset.Dataset;
import it.uniroma1.lcl.wcl.data.sentence.Sentence;
import it.uniroma1.lcl.wcl.classifiers.lattice.TripleLatticeClassifier;
import it.uniroma1.lcl.wcl.classifiers.lattice.WCLClassifier;
import it.uniroma1.lcl.wcl.data.sentence.SentenceAnnotation;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public class ParseCSVWriteHypernym {
	public static void main(String[] args){
				
		Language targetLanguage = Language.EN; 
	    String trainingDatasetFile ="data/training/wiki_good.EN.html";
		 // load the training set for the target language 
	    Dataset ts = null;
	    WCLClassifier c = null;
		try {
		      
			ts = new AnnotatedDataset(trainingDatasetFile, targetLanguage);
	        // obtain an instance of the WCL classifier
		    c = new TripleLatticeClassifier(targetLanguage);
	        c.train(ts);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != c) {
			//extracted_pw_hypernyms(ts,c, targetLanguage);
			extracted_ax_hypernyms(ts, c, targetLanguage); 
		}

	}
	
	private static void extracted_ax_hypernyms(Dataset ts, WCLClassifier c, Language targetLanguage) {
		String arXiv_fileName = "data/duplicated_term_def_for_opencsv.csv"; //Note: opencsv cannot parse csv that contains latex!!!
		String arxiv_with_hyper_fileName = "data/duplicated_term_def+hyper.csv";
        List<ArXivCSVBean> axbeans = null;
		try {
			axbeans = new CsvToBeanBuilder(new FileReader(arXiv_fileName))
			        .withType(ArXivCSVBean.class)
			        .build()
			        .parse();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null != axbeans) {
	        //get hypernyms
		    for (ArXivCSVBean axbean: axbeans) {
		    	
		    	try {
					Sentence sentence = Sentence.createFromString(axbean.getPlain_text_term(),
							axbean.getPlain_text_def(), 
					        targetLanguage);
					SentenceAnnotation sa = c.test(sentence);
					axbean.setIs_definition(sa.isDefinition());
					if (sa.isDefinition())
						axbean.setHypernym(sa.getHyper());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
	       }
		    
		    //write to csv		    
			try {
				StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(new FileWriter(arxiv_with_hyper_fileName)).build();
			    beanToCsv.write(axbeans);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvDataTypeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvRequiredFieldEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		    
		}
	}

	private static void extracted_pw_hypernyms(Dataset ts, WCLClassifier c, Language targetLanguage)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		String proofwiki_with_hyper_fileName = "data/parsed_disambiguation_list_without===_+hyper.csv";
		String proofwiki_fileName = "data/parsed_disambiguation_list_without===.csv";

        
        List<ProofwikiCSVBean> pwbeans = null;
		try {
			pwbeans = new CsvToBeanBuilder(new FileReader(proofwiki_fileName))
			        .withType(ProofwikiCSVBean.class)
			        .build()
			        .parse();
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null != pwbeans) {
	        //get hypernyms
		    for (ProofwikiCSVBean pwbean: pwbeans) {
		    	
		    	try {
					Sentence sentence = Sentence.createFromString(pwbean.getTerm(),
					        pwbean.getPlain_text_definition(), 
					        targetLanguage);
					SentenceAnnotation sa = c.test(sentence);
					pwbean.setIs_definition(sa.isDefinition());
					if (sa.isDefinition())
						pwbean.setHypernym(sa.getHyper());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
	       }
		    
		    //write to csv		    
			try {
				StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(new FileWriter(proofwiki_with_hyper_fileName)).build();
			    beanToCsv.write(pwbeans);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		    
		}
	}

}
