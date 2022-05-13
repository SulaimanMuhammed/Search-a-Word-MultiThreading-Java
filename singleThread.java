import java.util.concurrent.BlockingQueue;

public class singleThread extends Thread{


	 private  String word;
	 private signleWordCount Counter;
     private BlockingQueue<String> queue;

	 public singleThread(BlockingQueue<String> q, String s, signleWordCount swc){
		    	  this.queue=q;
			      this.word=s;
			      this.Counter=swc;	    
	  }
	
	  public void run(){
	
		   try {
		      //  synchronized(Counter) {
			   long start = System.nanoTime();
	        		while(!queue.isEmpty()){
		        		String gg= queue.take();	
				      if(gg.equals(word)) {
				    	  Counter.result++;
						 }				      
					 }
		       // }
	        
	        		System.out.println("the words for Single thread are "+Counter.result);
	                long end = System.nanoTime();

	                // execution time
	                long execution = end - start;
	             
	                double seconds = (double)execution / 1000000000.0;
	                System.out.println("Execution Time for multi threading is"+seconds+"seconds");
	               
	
		   }catch(Exception e) {
			   
		   }
	        
		   
		  }
}
