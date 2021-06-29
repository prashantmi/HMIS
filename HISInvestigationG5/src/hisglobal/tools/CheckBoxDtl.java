package hisglobal.tools;

import hisglobal.masterxml.masterworkshop.tools.ControlDtl;

import java.util.*;

public class CheckBoxDtl extends ControlDtl
{
	ArrayList alOptionText = new ArrayList();
	ArrayList alOptionValue = new ArrayList();
	//String defaultval;
	String readOnly;

	public java.util.ArrayList getAlOptionText()
	{
		return alOptionText;
	}

	public void setAlOptionText(java.util.ArrayList alOptionText)
	{
		this.alOptionText = alOptionText;
	}

	public java.util.ArrayList getAlOptionValue()
	{
		return alOptionValue;
	}

	public void setAlOptionValue(java.util.ArrayList alOptionValue)
	{
		this.alOptionValue = alOptionValue;
	}

	public java.lang.String getReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(java.lang.String readOnly)
	{
		this.readOnly = readOnly;
	}

}
