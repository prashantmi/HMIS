package Cdac.in;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample implements Callable {

	@Override
	public Object call() throws Exception {
		Thread.sleep(10000);
		return Thread.currentThread().getName();
	}
}
class CallDemo
{
	public static void main(String ar[]) {
		
		List<Future<String>> llist=new ArrayList<Future<String>>();
		
		CallableExample c[]={
				new CallableExample(),
				new CallableExample(),
				new CallableExample(),
				new CallableExample(),
				new CallableExample(),
				new CallableExample(),
				new CallableExample(),
		};
		
		ExecutorService pool=Executors.newFixedThreadPool(3);
		
		for(int i=0 ; i<c.length;i++)
		{
			Future<String> f= pool.submit(c[i]);
			llist.add(f);
		}
		pool.shutdown();
		 for(Future<String> fu : llist)
		 {
			 try {
				System.out.println(new Date()+ "::"+fu.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
		//System.out.println(llist.toString());
	}
}