package hisglobal.tools;

import java.util.*;

public abstract class Control
{
	protected String defaultValue;
	protected boolean readOnly;
	protected String label;
	protected Map constraints;

	public void setConstraints(Map _constraints)
	{
		this.constraints = _constraints;
	}

	public Map getConstraints()
	{
		return this.constraints;
	}

	public void setDefaultValue(java.lang.String defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	public java.lang.String getDefaultValue()
	{
		return defaultValue;
	}

	public java.lang.String getLabel()
	{
		return label;
	}

	public void setLabel(java.lang.String label)
	{
		this.label = label;
	}

	public boolean getReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(boolean readOnly)
	{
		this.readOnly = readOnly;
	}
}
