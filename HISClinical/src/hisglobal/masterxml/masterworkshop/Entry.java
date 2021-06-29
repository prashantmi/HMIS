package hisglobal.masterxml.masterworkshop;

import java.io.Serializable;
import java.util.Comparator;

public class Entry implements Serializable
{
	private String label = "";
	private String value = "";

	public Entry(String _Label, String _Value)
	{
		this.label = _Label;
		this.value = _Value;
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
		return objEntry.value.equals(this.value);
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
}
