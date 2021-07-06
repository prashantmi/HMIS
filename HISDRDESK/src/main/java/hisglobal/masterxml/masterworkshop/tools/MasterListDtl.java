package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public class MasterListDtl extends MasterDtl
{

	protected ArrayList alColumnHeading = new ArrayList();
	protected ArrayList alFields = new ArrayList();
	protected ArrayList alTable = new ArrayList();
	protected ArrayList alTableAlias = new ArrayList();
	protected ArrayList alControls = new ArrayList();
	protected ArrayList alPrimarykey = new ArrayList();
	protected ArrayList alClause = new ArrayList();
	protected ArrayList alAlphanumericClause = new ArrayList();
	

	protected String gencondition, tblHeading, depcondition, isvalidfield, listTable, orderby, genConditionInactive,
			hospitalCode;

	public void setAlClause(java.util.ArrayList alClause)
	{
		this.alClause = alClause;
	}

	public java.lang.String getListTable()
	{
		return listTable;
	}

	public void setListTable(java.lang.String listTable)
	{
		this.listTable = listTable;
	}

	public java.util.ArrayList getAlAlphanumericClause()
	{
		return alAlphanumericClause;
	}
	
	public java.util.ArrayList getAlClause()
	{
		return alClause;
	}
	
	public void setAlAlphanumericClause(java.util.ArrayList alAlphanumericClause)
	{
		this.alAlphanumericClause = alAlphanumericClause;
	}

	public java.lang.String getIsvalidfield()
	{
		return isvalidfield;
	}

	public void setIsvalidfield(java.lang.String isvalidfield)
	{
		this.isvalidfield = isvalidfield;
	}

	public java.lang.String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(java.lang.String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public void setAlControls(java.util.ArrayList alControls)
	{
		this.alControls = alControls;
	}

	public java.util.ArrayList getAlControls()
	{
		return alControls;
	}

	public void setTblHeading(java.lang.String tblHeading)
	{
		this.tblHeading = tblHeading;
	}

	public void setAlTableAlias(java.util.ArrayList alTableAlias)
	{
		this.alTableAlias = alTableAlias;
	}

	public void setAlTable(java.util.ArrayList alTable)
	{
		this.alTable = alTable;
	}

	public void setAlFields(java.util.ArrayList alFields)
	{
		this.alFields = alFields;
	}

	public void setAlColumnHeading(java.util.ArrayList alColumnHeading)
	{
		this.alColumnHeading = alColumnHeading;
	}

	public void setGencondition(java.lang.String gencondition)
	{
		this.gencondition = gencondition;
	}

	public void setDepcondition(java.lang.String depcondition)
	{
		this.depcondition = depcondition;
	}

	public java.lang.String getGencondition()
	{
		return gencondition;
	}

	public java.lang.String getDepcondition()
	{
		return depcondition;
	}

	public java.lang.String getTblHeading()
	{
		return tblHeading;
	}

	public java.util.ArrayList getAlPrimarykey()
	{
		return alPrimarykey;
	}

	public void setAlPrimarykey(java.util.ArrayList alPrimarykey)
	{
		this.alPrimarykey = alPrimarykey;
	}

	public java.util.ArrayList getAlTableAlias()
	{
		return alTableAlias;
	}

	public java.util.ArrayList getAlTable()
	{
		return alTable;
	}

	public java.util.ArrayList getAlFields()
	{
		return alFields;
	}

	public java.util.ArrayList getAlColumnHeading()
	{
		return alColumnHeading;
	}

	public void display()
	{
		System.out.println("--------------------printing control details-------------------");

		for (int i = 0; i < alControls.size(); i++)
		{
			ComboDtl objComboDtl = (ComboDtl) alControls.get(i);
		/*	System.out.println("Combo label" + objComboDtl.getLabel());
			System.out.println("Combo Index" + objComboDtl.getIndex());
			System.out.println("text field" + objComboDtl.getTextField());
			System.out.println("value field" + objComboDtl.getValueField());
			System.out.println("Default value" + objComboDtl.getDefaultValue());
			System.out.println("Combo query gen  condition" + objComboDtl.getGenCondition());
			System.out.println("Combo query  dep condition" + objComboDtl.getAlDepCondition());
			System.out.println("AL Table" + objComboDtl.alTable);
			System.out.println("AL Table" + objComboDtl.alTableAlias);
			System.out.println("AL option values" + objComboDtl.alOptionValues);
			System.out.println("AL text values" + objComboDtl.alOptiontext);*/
			System.out.println("--------------------end of printing control details-------------------");
		}

		System.out.println("--------------------printing Query details-------------------");
		/*System.out.println("AL col heading " + alColumnHeading);
		System.out.println("AL  Fields " + alFields);
		System.out.println("AL table " + alTable);
		System.out.println("AL table alias" + alTableAlias);
		System.out.println("AL isvalid feild" + isvalidfield);
		System.out.println("AL isvalid feild" + hospitalCode);
		System.out.println("primary key" + alPrimarykey);
		System.out.println("general Condition" + gencondition);
		System.out.println("dependent Condition" + depcondition);
		System.out.println("Table heading" + tblHeading);
		System.out.println("Master Name" + masterName);
		System.out.println("Master title" + masterTitle);*/
		System.out.println("--------------------end of printing Query details-------------------");

	}

	public String getOrderby()
	{
		return orderby;
	}

	public void setOrderby(String orderby)
	{
		this.orderby = orderby;
	}

	public String getGenConditionInactive()
	{
		return genConditionInactive;
	}

	public void setGenConditionInactive(String genConditionInactive)
	{
		this.genConditionInactive = genConditionInactive;
	}

}//end of class

