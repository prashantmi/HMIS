package hisglobal.masterxml.masterworkshop.tools;

import java.util.*;

public class Combo extends Control
{
	protected ArrayList value;
	protected ArrayList text;

	protected String labelSuffix;
	protected boolean submitRequired;
	protected String selectionMandatory;

	public Combo(String _label, String _defaultValue, ArrayList _value, ArrayList _text, boolean _readOnly)
	{
		super.defaultValue = _defaultValue;
		super.label = _label;
		super.readOnly = _readOnly;
		this.text = _text;
		this.value = _value;
	}

	public Combo(String _label, String _defaultValue, ArrayList _value, ArrayList _text, boolean _readOnly, Map _constraints)
	{
		super.defaultValue = _defaultValue;
		super.label = _label;
		super.constraints = _constraints;
		super.readOnly = _readOnly;
		this.text = _text;
		this.value = _value;
	}

	public void setSubmitRequired(boolean submitRequired)
	{
		this.submitRequired = submitRequired;
	}

	public boolean getSubmitRequired()
	{
		return submitRequired;
	}

	public void setLabelSuffix(java.lang.String labelSuffix)
	{
		this.labelSuffix = labelSuffix;
	}

	public java.lang.String getLabelSuffix()
	{
		return labelSuffix;
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
		System.out.println("********printing combo object details*****");
		/*System.out.println("labelsuffix" + this.labelSuffix);
		System.out.println("al value" + value);
		System.out.println("al text" + text);
		System.out.println("defaultvalue" + super.defaultValue);
		System.out.println("label" + super.label);
		System.out.println(" readOnly" + super.readOnly);*/
	}

	public ArrayList getOptions()
	{
		ArrayList al = new ArrayList();
		for (int i = 0; i < text.size(); i++)
		{
			Combo.ComboOption objClass = this.new ComboOption();
			objClass.setLabel((String) text.get(i));
			objClass.setValue((String) value.get(i));
			al.add(objClass);
		}
		return al;
	}

	//Inner Class for fetching the Arraylist of combo Options
	public class ComboOption
	{
		private String label = "";
		private String value = "";

		public String toString()
		{
			System.out.println(super.toString());
			//System.out.println("label:  " + label);
			//System.out.println("Value:        " + value);
			return (super.toString() + "   label:  " + this.getLabel() + "  Value:        " + this.getValue());

		}

		public java.lang.String getLabel()
		{
			return this.label;
		}

		public java.lang.String getValue()
		{
			return this.value;
		}

		public void setLabel(java.lang.String label)
		{
			this.label = label;
		}

		public void setValue(java.lang.String value)
		{
			this.value = value;
		}
	}

	public String getSelectionMandatory()
	{
		return selectionMandatory;
	}

	public void setSelectionMandatory(String selectionMandatory)
	{
		this.selectionMandatory = selectionMandatory;
	}

}
