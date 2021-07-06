package hisglobal.masterxml.masterworkshop;

import org.apache.struts.action.ActionForm;

public class XmlGeneratorFB extends ActionForm
{

	private String masterName;
	private String masterNameAsFileName;
	private String tableHeading;
	private String listTable;
	private String[] listControl;
	private String[] lstCtrlDefaultValue;
	private String lstCtrlReadOnly;
	private String lstCtrlLabel;
	private String[] lstControlType;
	private String[] lstControlIndex;
	private String lstCtrlIsDynamic;// can be avoided as static dynamic are mutually exclusive if aloptiovalues.size==0
									// implies that dynamic combo
	private String lstCtrlTextField;
	private String lstCtrlValueField;
	// private String defaultValue;
	private String[] lstCtrlGenCondition;
	private String[] lstCtrlSubmitRequired;
	private String[] lstCtrlSelectionMandatory;
	private String[] lstCtrlAlTable;
	private String[] lstCtrlAlTableAlias;
	private String[] lstCtrlDynOptionValues;
	private String[] lstCtrlDynOptiontext;
	private String[] lstCtrlAlDepCondition;
	private String[] lstCtrlAlOptionValues;
	private String[] lstCtrlAlOptiontext;

	// private lstCtrlonstraints;

	public String getMasterName()
	{
		return masterName;
	}

	public void setMasterName(String masterName)
	{
		this.masterName = masterName;
	}

	public String getMasterNameAsFileName()
	{
		return masterNameAsFileName;
	}

	public void setMasterNameAsFileName(String masterNameAsFileName)
	{
		this.masterNameAsFileName = masterNameAsFileName;
	}

	public String getTableHeading()
	{
		return tableHeading;
	}

	public void setTableHeading(String tableHeading)
	{
		this.tableHeading = tableHeading;
	}

	public String[] getListControl()
	{
		return listControl;
	}

	public String getListTable()
	{
		return listTable;
	}

	public String[] getLstControlType()
	{
		return lstControlType;
	}

	public String[] getLstCtrlAlDepCondition()
	{
		return lstCtrlAlDepCondition;
	}

	public String[] getLstCtrlAlOptiontext()
	{
		return lstCtrlAlOptiontext;
	}

	public String[] getLstCtrlAlOptionValues()
	{
		return lstCtrlAlOptionValues;
	}

	public String[] getLstCtrlAlTable()
	{
		return lstCtrlAlTable;
	}

	public String[] getLstCtrlAlTableAlias()
	{
		return lstCtrlAlTableAlias;
	}

	public String[] getLstCtrlDefaultValue()
	{
		return lstCtrlDefaultValue;
	}

	public String[] getLstCtrlGenCondition()
	{
		return lstCtrlGenCondition;
	}

	public String getLstCtrlIsDynamic()
	{
		return lstCtrlIsDynamic;
	}

	public String getLstCtrlLabel()
	{
		return lstCtrlLabel;
	}

	public String getLstCtrlReadOnly()
	{
		return lstCtrlReadOnly;
	}

	public String[] getLstCtrlSelectionMandatory()
	{
		return lstCtrlSelectionMandatory;
	}

	public String[] getLstCtrlSubmitRequired()
	{
		return lstCtrlSubmitRequired;
	}

	public String getLstCtrlTextField()
	{
		return lstCtrlTextField;
	}

	public String getLstCtrlValueField()
	{
		return lstCtrlValueField;
	}

	public void setListControl(int idx, String _listControl)
	{

		String[] tmp;
		tmp = new String[idx + 1];

		if (this.getListControl() != null)
		{
			for (int i = 0; i < this.listControl.length; i++)
				tmp[i] = this.listControl[i];
		}
		tmp[idx] = _listControl;
		this.listControl = tmp;
	}

	public void setListTable(String listTable)
	{
		this.listTable = listTable;
	}

	public void setLstControlType(int idx, String _controlType)
	{
		String[] tmp;
		tmp = new String[idx + 1];

		if (this.getListControl() != null)
		{
			for (int i = 0; i < this.listControl.length; i++)
				tmp[i] = this.listControl[i];
		}
		tmp[idx] = _controlType;
		this.lstControlType = tmp;
	}

	public void setLstCtrlAlDepCondition(String[] lstCtrlAlDepCondition)
	{
		this.lstCtrlAlDepCondition = lstCtrlAlDepCondition;
	}

	public void setLstCtrlAlOptiontext(String[] lstCtrlAlOptiontext)
	{
		this.lstCtrlAlOptiontext = lstCtrlAlOptiontext;
	}

	public void setLstCtrlAlOptionValues(String[] lstCtrlAlOptionValues)
	{
		this.lstCtrlAlOptionValues = lstCtrlAlOptionValues;
	}

	public void setLstCtrlAlTable(String[] lstCtrlAlTable)
	{
		this.lstCtrlAlTable = lstCtrlAlTable;
	}

	public void setLstCtrlAlTableAlias(String[] lstCtrlAlTableAlias)
	{
		this.lstCtrlAlTableAlias = lstCtrlAlTableAlias;
	}

	public void setLstCtrlDefaultValue(String[] lstCtrlDefaultValue)
	{
		this.lstCtrlDefaultValue = lstCtrlDefaultValue;
	}

	public void setLstCtrlGenCondition(String[] lstCtrlGenCondition)
	{
		this.lstCtrlGenCondition = lstCtrlGenCondition;
	}

	public void setLstCtrlIsDynamic(String lstCtrlIsDynamic)
	{
		this.lstCtrlIsDynamic = lstCtrlIsDynamic;
	}

	public void setLstCtrlLabel(String lstCtrlLabel)
	{
		this.lstCtrlLabel = lstCtrlLabel;
	}

	public void setLstCtrlReadOnly(String lstCtrlReadOnly)
	{
		this.lstCtrlReadOnly = lstCtrlReadOnly;
	}

	public void setLstCtrlSelectionMandatory(String[] lstCtrlSelectionMandatory)
	{
		this.lstCtrlSelectionMandatory = lstCtrlSelectionMandatory;
	}

	public void setLstCtrlSubmitRequired(String[] lstCtrlSubmitRequired)
	{
		this.lstCtrlSubmitRequired = lstCtrlSubmitRequired;
	}

	public void setLstCtrlTextField(String lstCtrlTextField)
	{
		this.lstCtrlTextField = lstCtrlTextField;
	}

	public void setLstCtrlValueField(String lstCtrlValueField)
	{
		this.lstCtrlValueField = lstCtrlValueField;
	}

}
