package hisglobal.masterxml.masterworkshop.tools;

import java.io.*;
import java.util.*;

public class TextArea extends Control
{

	public TextArea()
	{
	}

	public TextArea(String _label, String _defaultValue, Map _constraints, boolean _readOnly)
	{
		super.defaultValue = _defaultValue;
		super.label = _label;
		super.readOnly = _readOnly;
		super.constraints = _constraints;
	}

	public void display()
	{
		System.out.println("**********printing TEXTAREA Details***********");

		/*System.out.println("defaultvalue" + super.defaultValue);
		System.out.println("label" + super.label);
		System.out.println(" readOnly" + super.readOnly);
		System.out.println(" ConstraintMAp" + super.constraints);*/
	}

}
