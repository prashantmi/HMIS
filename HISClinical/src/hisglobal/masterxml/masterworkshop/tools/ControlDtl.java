package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public abstract class ControlDtl
{
	protected String label;
	protected String index;
	HashMap constraintMap = null;
	protected String readOnly;
	String defaultValue;
	String defaultLValue;
	String showOnModify = "";
	String showOnView = "";

	public String getShowOnModify()
	{
		return showOnModify;
	}

	public void setShowOnModify(String showOnModify)
	{
		this.showOnModify = showOnModify;
	}

	public String getShowOnView()
	{
		return showOnView;
	}

	public void setShowOnView(String showOnView)
	{
		this.showOnView = showOnView;
	}

	public java.lang.String getDefaultLValue()
	{
		return defaultLValue;
	}

	public void setDefaultLValue(java.lang.String defaultLValue)
	{
		this.defaultLValue = defaultLValue;
	}

	public java.lang.String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(java.lang.String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public void setReadOnly(java.lang.String readOnly)
	{
		this.readOnly = readOnly;
	}

	public java.lang.String getReadOnly()
	{
		return readOnly;
	}

	public void setConstraintMap(java.util.HashMap constraintMap)
	{
		this.constraintMap = constraintMap;
	}

	public java.util.HashMap getConstraintMap()
	{
		return constraintMap;
	}

	public void setIndex(java.lang.String index)
	{
		this.index = index;
	}

	public java.lang.String getIndex()
	{
		return index;
	}

	public java.lang.String getLabel()
	{
		return label;
	}

	public void setLabel(java.lang.String label)
	{
		this.label = label;
	}

}
