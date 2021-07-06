package Cdac.in;

import java.io.Closeable;

class Employee implements AutoCloseable  {

	public void close(){
		System.out.println("Close Method Called");
	}
}

class Test3
{
	
	public static void  main(String ar[])
	{
		try(Employee e=new Employee()){
			System.out.println("Employee Class");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}