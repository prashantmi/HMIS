package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public class MasterAddDtl extends MasterDtl
{
	public static final String CLASS_COMBO_DTL = "hisglobal.masterxml.masterworkshop.tools.ComboDtl";
	public static final String CLASS_TEXT_DTL = "hisglobal.masterxml.masterworkshop.tools.TextDtl";
	public static final String CLASS_TEXTAREA_DTL = "hisglobal.masterxml.masterworkshop.tools.TextAreaDtl";
	public static final String CLASS_RADIOBUTTON_DTL = "hisglobal.masterxml.masterworkshop.tools.RadioDtl";
	public static final String CLASS_CHECKBOX_DTL = "hisglobal.masterxml.masterworkshop.tools.CheckBoxDtl";

	protected ArrayList alControls = new ArrayList();

	//Insert Query Details...
	protected ArrayList alFields = new ArrayList();
	protected ArrayList alValueMap = new ArrayList(); //index of the control.. 1, 2, 3 4.. the value maps to
	protected ArrayList alpkFields = new ArrayList();
	protected ArrayList alpkValue = new ArrayList();
	protected ArrayList alRedundentQuery = new ArrayList();
	protected String staticPage;
	protected String table, hospitalCode,redundentQuery;

	// Above details are used to build an insert query

	public String getRedundentQuery() {
		return redundentQuery;
	}

	public void setRedundentQuery(String redundentQuery) {
		this.redundentQuery = redundentQuery;
	}
	public void setTable(java.lang.String table)
	{
		this.table = table;
	}

	public java.lang.String getTable()
	{
		return table;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public java.lang.String getStaticPage()
	{
		return staticPage;
	}

	public void setStaticPage(java.lang.String staticPage)
	{
		this.staticPage = staticPage;
	}

	public void setAlValueMap(java.util.ArrayList alValueMap)
	{
		this.alValueMap = alValueMap;
	}

	public void setAlFields(java.util.ArrayList alFields)
	{
		this.alFields = alFields;
	}

	public void setAlControls(java.util.ArrayList alControls)
	{
		this.alControls = alControls;
	}

	public java.util.ArrayList getAlpkFields()
	{
		return alpkFields;
	}

	public java.util.ArrayList getAlpkValue()
	{
		return alpkValue;
	}

	public void setAlpkValue(java.util.ArrayList alpkValue)
	{
		this.alpkValue = alpkValue;
	}

	public void setAlpkFields(java.util.ArrayList alpkFields)
	{
		this.alpkFields = alpkFields;
	}

	public java.util.ArrayList getAlValueMap()
	{
		return alValueMap;
	}

	public java.util.ArrayList getAlFields()
	{
		return alFields;
	}
	
	public java.util.ArrayList getAlRedundentQuery()
	{
		return alRedundentQuery;
	}

	
	public java.util.ArrayList getAlControls()
	{
		return alControls;
	}

	public void display()
	{

		System.out.println("--------------------printing control details-------------------");
		//System.out.println("alControls.size:::::::::" + alControls.size());
		for (int i = 0; i < alControls.size(); i++)
		{
			Object objControl = alControls.get(i);
			//System.out.println("control class" + objControl.getClass().getName());
			if (objControl.getClass().getName().equalsIgnoreCase(CLASS_COMBO_DTL))
			{
				//System.out.println("inside combo print");
				ComboDtl objComboDtl = (ComboDtl) objControl;
				/*System.out.println("Combo label" + objComboDtl.getLabel());
				System.out.println("Combo Index" + objComboDtl.getIndex());
				System.out.println("text field" + objComboDtl.getTextField());
				System.out.println("value field" + objComboDtl.getValueField());
				System.out.println("Default value" + objComboDtl.getDefaultValue());
				System.out.println("Combo query gen  condition" + objComboDtl.getGenCondition());
				System.out.println("Combo query  dep condition" + objComboDtl.getAlDepCondition());
				System.out.println("AL Table" + objComboDtl.alTable);
				System.out.println("AL TableAlias" + objComboDtl.alTableAlias);
				System.out.println("AL option values" + objComboDtl.alOptionValues);
				System.out.println("AL text values" + objComboDtl.alOptiontext);
				System.out.println("Constraintsmap" + objComboDtl.getConstraintMap());*/
				if (objComboDtl.getLabelSuffix() != null)
				{
					/*System.out.println("alLTable" + objComboDtl.getLabelSuffix().getAlLTable());
					System.out.println("alLTableAlias" + objComboDtl.getLabelSuffix().getAlLTableAlias());
					System.out.println("alLField" + objComboDtl.getLabelSuffix().getAlLField());
					System.out.println("aldepLcondition" + objComboDtl.getLabelSuffix().getAldepLcondition());
					System.out.println("aldepLconditionVal" + objComboDtl.getLabelSuffix().getAldepLconditionVal());
					System.out.println("genLCondition" + objComboDtl.getLabelSuffix().getGenLCondition());*/
				}
			}

			if (objControl.getClass().getName().equalsIgnoreCase(CLASS_TEXT_DTL))
			{

				//System.out.println("inside text print");
				TextDtl objTextDtl = (TextDtl) objControl;

				/*System.out.println("readOnly" + objTextDtl.readOnly);
				System.out.println("constraintMap" + objTextDtl.constraintMap);
				System.out.println("alTable" + objTextDtl.alTable);
				System.out.println("alField" + objTextDtl.alField);
				System.out.println("alTableAlias" + objTextDtl.alTableAlias);
				System.out.println("alDepCondition" + objTextDtl.alDepCondition);
				System.out.println("genCondition" + objTextDtl.genCondition);
				System.out.println("alDepConditionVal" + objTextDtl.alDepConditionVal);*/
				if (objTextDtl.getLabelSuffix() != null)
				{
					/*System.out.println("alLTable" + objTextDtl.getLabelSuffix().getAlLTable());
					System.out.println("alLTableAlias" + objTextDtl.getLabelSuffix().getAlLTableAlias());
					System.out.println("alLField" + objTextDtl.getLabelSuffix().getAlLField());
					System.out.println("aldepLcondition" + objTextDtl.getLabelSuffix().getAldepLcondition());
					System.out.println("aldepLconditionVal" + objTextDtl.getLabelSuffix().getAldepLconditionVal());
					System.out.println("genLCondition" + objTextDtl.getLabelSuffix().getGenLCondition());*/
				}
			}

			if (objControl.getClass().getName().equalsIgnoreCase(CLASS_TEXTAREA_DTL))
			{
				TextAreaDtl objTextAreaDtl = (TextAreaDtl) objControl;
				/*System.out.println("readOnly" + objTextAreaDtl.readOnly);
				System.out.println("constraintMap" + objTextAreaDtl.constraintMap);
				System.out.println("alTable" + objTextAreaDtl.alTable);
				System.out.println("alField" + objTextAreaDtl.alField);
				System.out.println("alTableAlias" + objTextAreaDtl.alTableAlias);
				System.out.println("alDepCondition" + objTextAreaDtl.alDepCondition);
				System.out.println("alDepConditionVal" + objTextAreaDtl.alDepConditionVal);
				System.out.println("genCondition" + objTextAreaDtl.genCondition);*/

			}

			if (objControl.getClass().getName().equalsIgnoreCase(CLASS_RADIOBUTTON_DTL))
			{
				RadioDtl objRadioDtl = (RadioDtl) objControl;
				/*System.out.println("alOptionText" + objRadioDtl.getAlOptionText());
				System.out.println("alOptionValue" + objRadioDtl.getAlOptionValue());
				System.out.println("defaullval" + objRadioDtl.getDefaultValue());
				System.out.println("readOnly" + objRadioDtl.getReadOnly());
*/
			}
			if (objControl.getClass().getName().equalsIgnoreCase(CLASS_CHECKBOX_DTL))
			{
				CheckBoxDtl objCheckBox = (CheckBoxDtl) objControl;
				/*System.out.println("alOptionText" + objCheckBox.getAlOptionText());
				System.out.println("alOptionValue" + objCheckBox.getAlOptionValue());
				System.out.println("defaullval" + objCheckBox.getDefaultValue());
				System.out.println("readOnly" + objCheckBox.getReadOnly());*/
			}

			System.out.println("--------------------end of printing control details-------------------");

		}

		System.out.println("--------------------printing insert Query details-------------------");
		/*System.out.println(" alControls " + alControls);
		System.out.println("alValueMap " + alValueMap);
		System.out.println("alFields " + alFields);
		System.out.println("pkValue" + alpkValue);
		System.out.println("trable" + table);
		System.out.println("Master Name" + masterName);
		System.out.println("Master title" + masterTitle);*/
		System.out.println("--------------------end of printing insert Query details-------------------");
	}

	

}//end of class

