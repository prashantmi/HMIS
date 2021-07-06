package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public class TextBox extends Control
{
	String labelSuffix = "";//<<bUidl setter getters for the same... 

	public TextBox()
	{
	}

	public TextBox(String _label, String _defaultValue, Map _constraints, boolean _readOnly)
	{
		super.defaultValue = _defaultValue;
		super.label = _label;
		super.readOnly = _readOnly;
		super.constraints = _constraints;
	}

	public void setLabelSuffix(java.lang.String labelSuffix)
	{
		this.labelSuffix = labelSuffix;
	}

	public java.lang.String getLabelSuffix()
	{
		return labelSuffix;
	}

	public void display()
	{
		System.out.println("**********printing TEXTBOX Details***********");
		/*System.out.println("labelSUFFIX" + this.labelSuffix);
		System.out.println("defaultvalue" + super.defaultValue);
		System.out.println("label" + super.label);
		System.out.println(" readOnly" + super.readOnly);
		System.out.println(" ConstraintMAp" + super.constraints);*/
	}

}
