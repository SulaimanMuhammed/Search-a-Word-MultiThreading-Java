import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Project {

	public static void main(String[] args) throws IOException, InterruptedException {
//the things for reading pdf
		 Scanner input=new Scanner(System.in);
		 ExecutorService sc=Executors.newCachedThreadPool();
		ExecutorService single=Executors.newFixedThreadPool(1);
         PDDocument doc = PDDocument.load(new File("D:\\dd.pdf"));
         String textPdf = new PDFTextStripper().getText(doc);     
         String[] words=textPdf.split("\\s"); 
//creating the blocking queue
         BlockingQueue<String> multiQueue=new LinkedBlockingQueue<>();
         BlockingQueue<String> singleQueue=new LinkedBlockingQueue<>();
         wordCount wc=new wordCount();
         signleWordCount swc=new signleWordCount();
         System.out.println("please enter a word to look for");
         String wordInput=input.next(); 
 // get the start time
     
       
//crating the loop to put values multiThreads
     for(int x=0; x<words.length;x++){       	 

    	  multiQueue.put(words[x]);
               }
//putting values into the single thread
            for(int i=0; i<words.length/5;i++) {
            	Counter C1=new Counter(multiQueue, wordInput, wc);
            	sc.execute(C1);
            }
         // get the end time
        

         // execution time
      
         System.out.println("the words are "+wc.count);
        
        
//creating the Single thread
      // get the start time
       //  long startSingle = System.nanoTime();
       
//crating the loop to put values Single thread
          for(int z=0; z<words.length;z++){       	 
             	 singleQueue.put(words[z]);
                    }
               singleThread SC=new singleThread(singleQueue, wordInput, swc);
                single.execute(SC);	 
               

         // get the end time
        // long endSingle= System.nanoTime();

         // execution time
        // long executionSingle = endSingle - startSingle;
       
        // double secondsSingle = (double)executionSingle / 1000000000.0;
          //     System.out.println("Execution Time for Single thread is"+secondsSingle+"seconds");
              
        }
	 public static ExecutorService newCachedThreadPool() { 
	        return new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, 
	        new SynchronousQueue<Runnable>(),
	        new ThreadPoolExecutor.CallerRunsPolicy());
	        
	    }
	 }
