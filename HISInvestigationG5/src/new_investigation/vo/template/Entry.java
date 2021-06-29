package new_investigation.vo.template;

import java.io.Serializable;
import java.util.Comparator;

public class Entry implements Serializable, Cloneable
{
	private String label = "";
	private String value = "";
	private String slno = "";	
	private String isGroupTest = "";
	private String labCode = "";
	private String testCode = "";
	private String hospitalCode = "";
	private String groupCode = "";
	
	
	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	
	
	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
		this.slno = slno;
	}

	public String getIsGroupTest() {
		return isGroupTest;
	}

	public void setIsGroupTest(String isGroupTest) {
		this.isGroupTest = isGroupTest;
	}

	
	public Entry(String _Label, String _Value)
	{
		this.label = _Label;
		//System.out.println("LAbel::" + this.label);
		this.value = _Value;
		//System.out.println("Value::" + this.value);
	}
	
	public Entry(String _Label, String _Value, String _slno, String _group,String _hospitalCode, String _labCode, 
			String _groupCode,String _testCode  
			)
	{
		this.label = _Label;
		//System.out.println("LAbel::" + this.label);
		this.value = _Value;
		//System.out.println("Value::" + this.value);
		this.slno = _slno;
		this.isGroupTest = _group;
		this.testCode = _testCode;
		this.groupCode = _groupCode;
		this.labCode = _labCode;
		this.hospitalCode = _hospitalCode;				
	}

	public Entry()
	{
	}

	public java.lang.String getLabel()
	{
		return this.label;
	}

	public java.lang.String getValue()
	{
		return this.value;
	}

	public void setLabel(java.lang.String label)
	{
		this.label = label;
	}

	public void setValue(java.lang.String value)
	{
		this.value = value;
	}

	public String toString()
	{
		return ("[" + this.label + ", " + this.value + "]");
	}

	public boolean equals(Object obj)
	{
		if (obj == this) // (2)
		return true;
		if (obj == null || !(obj instanceof Entry)) // (3)
		return false;
		Entry objEntry = (Entry) obj;
		return (objEntry.value == null ? false : objEntry.value.equals(this.value));
	}

	public int hashCode()
	{

		int hashValue = 0;
		hashValue = this.value.hashCode();
		return hashValue;
	}

	public static class EntryComparator implements Comparator
	{
		public int compare(Object obj1, Object obj2)
		{

			Entry objEntry1 = ((Entry) obj1);
			Entry objEntry2 = ((Entry) obj2);
			return objEntry1.getLabel().compareTo(objEntry2.getLabel());
		}

	}

	public Object clone()
	{
		Object obj = null;
		try
		{
			obj = super.clone();
		}
		catch (CloneNotSupportedException e)
		{

		}
		return obj;

	}
}
