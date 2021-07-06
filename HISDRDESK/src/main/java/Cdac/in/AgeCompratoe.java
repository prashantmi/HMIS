package Cdac.in;

import java.util.Comparator;

public class AgeCompratoe implements Comparator<Student> {

	
	public int compare(Student s1, Student s2) {
	
		if(s1.rollNo > s2.rollNo)
		return +1 ;
		else if(s1.rollNo < s2.rollNo)
			return -1 ;
		else
		return 0;
	}

}
