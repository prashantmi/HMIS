package Cdac.in;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo implements Runnable {

	String Name;
	public ThreadPoolDemo(String Name)
	{
		this.Name=Name;
	}
	
	public void run() {
		
		System.out.println("Name of the Current "+Thread.currentThread().getName()+"   Object Of class"+Name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
class Test
{
	public static void main(String ar[])
	{
		ThreadPoolDemo r[]={
				new ThreadPoolDemo("Ashu"),
				new ThreadPoolDemo("Ankit"),
				new ThreadPoolDemo("Ashish"),
				new ThreadPoolDemo("Ashok"),
				new ThreadPoolDemo("Deepu"),
				new ThreadPoolDemo("Akarsh"),
				new ThreadPoolDemo("chinny"),
				new ThreadPoolDemo("bunny"),
				new ThreadPoolDemo("lucky"),
				new ThreadPoolDemo("qwert"),
				new ThreadPoolDemo("durga"),
				new ThreadPoolDemo("mohan"),
		};
		
		ExecutorService pool=Executors.newFixedThreadPool(2);
		
		for(int i=0;i<r.length;i++)
		{
			pool.execute(r[i]);
		}
		pool.shutdown();
	}
	
	
	
}