package hisglobal.masterxml.masterworkshop.tools;

class ComboOption
{
	private String prem = "";
	private String value = "";

	public String toString()
	{
		System.out.println(super.toString());
		System.out.println("label:  " + prem);
		System.out.println("Value:        " + value);
		return (super.toString() + "   label:  " + this.getPrem() + "  Value:        " + this.getValue());
	}

	public java.lang.String getPrem()
	{
		return this.prem;
	}

	public java.lang.String getValue()
	{
		return this.value;
	}

	public void setLabel(java.lang.String label)
	{
		this.prem = label;
	}

	public void setValue(java.lang.String value)
	{
		this.value = value;
	}
}
