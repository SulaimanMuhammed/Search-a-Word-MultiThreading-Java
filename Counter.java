
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class Counter extends Thread{
	//private counter
	 private  String word;
	 private wordCount CounterWords;
	 //private  wordCount w=new wordCount();
	 private BlockingQueue<String> queue;

	 public Counter(BlockingQueue<String> q, String s, wordCount wc){
		    	  this.queue=q;
			      this.word=s;
			      this.CounterWords=wc;	    
	  }
	
	  public void run(){
		   try {
			    long start = System.nanoTime();
		        	synchronized(CounterWords) {
		        		while(!queue.isEmpty()){
			        		String g= queue.take();
					      if(g.equals(word)) {
		                     
					    	  CounterWords.count++;
							 }				      
						 }
			        	
		        	}
		        	 long end = System.nanoTime();    
		        	   long execution = end - start;
		        	 double seconds = (double)execution / 1000000000;
		             System.out.println("Execution Time for multi threading is"+seconds+"seconds");
		   }catch(Exception e) {
			   
		   }
	        
		   
		  }
	 
	  
	     

	    }
	
