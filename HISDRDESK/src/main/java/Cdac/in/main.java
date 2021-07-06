package Cdac.in;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
		Student s1=new Student(901, "Ashu", "cdac");
		Student s2=new Student(111, "anshu", "cdac");
		Student s3=new Student(321, "bhole", "cdac");
		Student s4=new Student(123, "vnsh", "cdac");
		Student s5=new Student(777, "hansh", "cdac");
		Student s6=new Student(333, "demo", "cdac");
		Student s7=new Student(863, "bho", "cdac");
		
		List<Student> list =new ArrayList<Student>();
		
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);
		list.add(s7);
		
		Collections.sort(list, new AgeCompratoe());
		
		System.out.println(list.toString());
		
		Iterator<Student> i=list.iterator();
		
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		
	}

}
