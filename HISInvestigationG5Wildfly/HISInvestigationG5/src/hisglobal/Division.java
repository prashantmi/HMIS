package hisglobal;

import hisglobal.tools.Control;

import java.util.*;

public class Division extends Control
{
	ArrayList controls = new ArrayList();
	String id = "";

	public Division()
	{
	}

	//getter & setter methods

	public java.lang.String getId()
	{
		return id;
	}

	public void setId(java.lang.String id)
	{
		this.id = id;
	}

	public java.util.ArrayList getControls()
	{
		return controls;
	}

	public void setControls(java.util.ArrayList controls)
	{
		this.controls = controls;
	}

}
