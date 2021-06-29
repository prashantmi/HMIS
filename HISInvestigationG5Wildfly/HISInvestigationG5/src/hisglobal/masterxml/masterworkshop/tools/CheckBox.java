package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public class CheckBox extends Control
{
	protected ArrayList value;
	protected ArrayList text;

	public CheckBox()
	{
	}

	public CheckBox(String _label, String _defaultValue, ArrayList _value, ArrayList _text, boolean _readOnly,
			Map _constraints)
	{
		super.defaultValue = _defaultValue;
		super.label = _label;
		super.readOnly = _readOnly;
		super.constraints = _constraints;

		this.text = _text;
		this.value = _value;
	}

	public java.util.ArrayList getText()
	{
		return text;
	}

	public void setText(java.util.ArrayList text)
	{
		this.text = text;
	}

	public java.util.ArrayList getValue()
	{
		return value;
	}

	public void setValue(java.util.ArrayList value)
	{
		this.value = value;
	}

	public void display()
	{
		System.out.println("**********printing CheckBOX Details***********");
		System.out.println("al value" + value);
		System.out.println("al text" + text);
		System.out.println("defaultvalue" + super.defaultValue);
		System.out.println("label" + super.label);
		System.out.println(" readOnly" + super.readOnly);
		System.out.println(" ConstraintMAp" + super.constraints);
	}

}
