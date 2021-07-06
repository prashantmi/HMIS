package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

/*
 * Copyright ©.
 */
/**
 * @author Administrator
 */
public class ControlDtlWithSuffix extends ControlDtl
{
	//String staticLData = null;	          
	ArrayList alLTable = new ArrayList();
	ArrayList alLTableAlias = new ArrayList();
	ArrayList alLField = new ArrayList();
	ArrayList aldepLcondition = new ArrayList();
	ArrayList aldepLconditionVal = new ArrayList();
	String genLCondition;

	public java.util.ArrayList getAldepLcondition()
	{
		return aldepLcondition;
	}

	public void setAldepLcondition(java.util.ArrayList aldepLcondition)
	{
		this.aldepLcondition = aldepLcondition;
	}

	public java.util.ArrayList getAldepLconditionVal()
	{
		return aldepLconditionVal;
	}

	public void setAldepLconditionVal(java.util.ArrayList aldepLconditionVal)
	{
		this.aldepLconditionVal = aldepLconditionVal;
	}

	public java.util.ArrayList getAlLField()
	{
		return alLField;
	}

	public void setAlLField(java.util.ArrayList alLField)
	{
		this.alLField = alLField;
	}

	public java.util.ArrayList getAlLTable()
	{
		return alLTable;
	}

	public void setAlLTable(java.util.ArrayList alLTable)
	{
		this.alLTable = alLTable;
	}

	public java.util.ArrayList getAlLTableAlias()
	{
		return alLTableAlias;
	}

	public void setAlLTableAlias(java.util.ArrayList alLTableAlias)
	{
		this.alLTableAlias = alLTableAlias;
	}

	public java.lang.String getGenLCondition()
	{
		return genLCondition;
	}

	public void setGenLCondition(java.lang.String genLCondition)
	{
		this.genLCondition = genLCondition;
	}
}
