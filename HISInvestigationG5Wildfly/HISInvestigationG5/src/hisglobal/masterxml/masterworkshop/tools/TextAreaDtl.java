package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public class TextAreaDtl extends ControlDtl
{

	String readOnly;

	ArrayList alTable = new ArrayList();
	ArrayList alField = new ArrayList();
	ArrayList alTableAlias = new ArrayList();
	ArrayList alDepCondition = new ArrayList();
	ArrayList alDepConditionVal = new ArrayList();
	String genCondition;

	public java.util.ArrayList getAlDepCondition()
	{
		return alDepCondition;
	}

	public void setAlDepCondition(java.util.ArrayList alDepCondition)
	{
		this.alDepCondition = alDepCondition;
	}

	public java.util.ArrayList getAlDepConditionVal()
	{
		return alDepConditionVal;
	}

	public void setAlDepConditionVal(java.util.ArrayList alDepConditionVal)
	{
		this.alDepConditionVal = alDepConditionVal;
	}

	public java.util.ArrayList getAlField()
	{
		return alField;
	}

	public void setAlField(java.util.ArrayList alField)
	{
		this.alField = alField;
	}

	public java.util.ArrayList getAlTable()
	{
		return alTable;
	}

	public void setAlTable(java.util.ArrayList alTable)
	{
		this.alTable = alTable;
	}

	public java.util.ArrayList getAlTableAlias()
	{
		return alTableAlias;
	}

	public void setAlTableAlias(java.util.ArrayList alTableAlias)
	{
		this.alTableAlias = alTableAlias;
	}

	public java.lang.String getGenCondition()
	{
		return genCondition;
	}

	public void setGenCondition(java.lang.String genCondition)
	{
		this.genCondition = genCondition;
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
