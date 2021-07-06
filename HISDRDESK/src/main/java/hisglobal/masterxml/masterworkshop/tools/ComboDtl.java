package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public class ComboDtl extends ControlDtl
{
	ControlDtlWithSuffix labelSuffix = null;

	boolean isDynamic;//can be avoided as static dynamic are mutually exclusive if aloptiovalues.size==0 implies that dynamic combo
	String textField;
	String valueField;
	//String defaultValue;
	String genCondition;
	String submitRequired;
	String selectionMandatory = "true";
	String hospitalCode;
	ArrayList alTable = new ArrayList();
	ArrayList alTableAlias = new ArrayList();
	ArrayList alOptionValues = new ArrayList();
	ArrayList alOptiontext = new ArrayList();
	ArrayList alDepCondition = new ArrayList();

	public void setSubmitRequired(java.lang.String submitRequired)
	{
		this.submitRequired = submitRequired;
	}

	public java.lang.String getSubmitRequired()
	{
		return submitRequired;
	}

	public java.util.ArrayList getAlDepCondition()
	{
		return alDepCondition;
	}

	public void setAlDepCondition(java.util.ArrayList alDepCondition)
	{
		this.alDepCondition = alDepCondition;
	}

	public java.util.ArrayList getAlOptiontext()
	{
		return alOptiontext;
	}

	public void setAlOptiontext(java.util.ArrayList alOptiontext)
	{
		this.alOptiontext = alOptiontext;
	}

	public java.util.ArrayList getAlOptionValues()
	{
		return alOptionValues;
	}

	public void setAlOptionValues(java.util.ArrayList alOptionValues)
	{
		this.alOptionValues = alOptionValues;
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

	public boolean getIsDynamic()
	{
		return isDynamic;
	}

	public void setIsDynamic(boolean isDynamic)
	{
		this.isDynamic = isDynamic;
	}

	public hisglobal.masterxml.masterworkshop.tools.ControlDtlWithSuffix getLabelSuffix()
	{
		return labelSuffix;
	}

	public void setLabelSuffix(hisglobal.masterxml.masterworkshop.tools.ControlDtlWithSuffix labelSuffix)
	{
		this.labelSuffix = labelSuffix;
	}

	public java.lang.String getTextField()
	{
		return textField;
	}

	public void setTextField(java.lang.String textField)
	{
		this.textField = textField;
	}

	public java.lang.String getValueField()
	{
		return valueField;
	}

	public void setValueField(java.lang.String valueField)
	{
		this.valueField = valueField;
	}

	public String getSelectionMandatory()
	{
		//System.out.println("inside selectionMandatory::::::::" + selectionMandatory);
		return selectionMandatory;
	}

	public void setSelectionMandatory(String selectionMandatory)
	{
		this.selectionMandatory = selectionMandatory;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	//ArrayList alDepConditionVal=new ArrayList();
	//HashMap constraintMap=new HashMap();

}
