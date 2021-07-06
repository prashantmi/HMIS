package Cdac.in;

public class Student {

	 int rollNo;
	 String name;
	 String schollName;
	
	 Student(int rollNo, String name, String schollName) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.schollName = schollName;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", schollName="
				+ schollName + "]";
	}
	 
	
}
