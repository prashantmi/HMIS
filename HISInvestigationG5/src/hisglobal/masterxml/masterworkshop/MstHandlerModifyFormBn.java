package hisglobal.masterxml.masterworkshop;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class MstHandlerModifyFormBn extends ActionForm
{

	/**
	 * 
	 */

	Object controls[];
	String isActive = "";
	String staticPage = "";
	String hmode = "";

	public String getStaticPage()
	{
		return staticPage;
	}

	public void setStaticPage(String staticPage)
	{
		this.staticPage = staticPage;
	}

	public MultiSelect getMultiSelect(int idx)
	{
		return new MultiSelect(idx);
	}

	/*
	 * public java.lang.String[] getMultiSelect(int idx) { System.out.println("insides getMultiSelect idex: "+idx); Object[]
	 * tmp; if(this.controls != null && idx< controls.length) { return (String[])this.controls[idx]; } else { tmp = new
	 * Object[idx+1]; if(this.controls!=null){ for(int i =0; i<this.controls.length; i++) tmp[i] = this.controls[i];
	 * 
	 * for(int i = this.controls.length; i<tmp.length; i++) { tmp[i]=new Object(); } } }
	 * 
	 * this.controls = tmp; return (String[])this.controls[idx]; }
	 */

	/*
	 * public void setMultiSelect( int idx, String[] _controls ) { System.out.println("insidesetMultiSelect idex: "+idx);
	 * Object[] tmp;
	 * 
	 * if(this.controls != null && this.controls.length >(idx)) tmp = new Object[this.controls.length]; else tmp = new
	 * Object[idx+1];
	 * 
	 * 
	 * System.out.println("inside setMulticontrol idx: "+idx+" tmp length: "+tmp.length+" value : "+_controls);
	 * 
	 * 
	 * if(this.controls!=null){ for(int i =0; i<this.controls.length; i++) tmp[i] = this.controls[i]; } tmp[idx] =
	 * _controls; this.controls = tmp; }
	 */

	public void setIsActive(java.lang.String isActive)
	{
		this.isActive = isActive;
	}

	public String getControl(int idx)
	{
		return (String) this.controls[idx];
	}

	public void setContrl(int idx, String _controls)
	{
		//System.out.println("inside idex:  " + idx);
		Object[] tmp;

		if (this.controls != null && this.controls.length > (idx)) tmp = new Object[this.controls.length];
		else tmp = new Object[idx + 1];

		if (_controls.getClass().isArray())
		{
			//System.out.println("its an array()..");
		}
		//System.out.println("inside idx: " + idx + " tmp length:  " + tmp.length + " value : " + _controls);

		if (this.controls != null)
		{
			for (int i = 0; i < this.controls.length; i++)
				tmp[i] = this.controls[i];
		}
		tmp[idx] = _controls;
		this.controls = tmp;

	}

	public void setControl(int idx, String _controls)
	{
		//System.out.println("inside idex:  " + idx);
		Object[] tmp;

		if (this.controls != null && this.controls.length > (idx)) tmp = new Object[this.controls.length];
		else tmp = new Object[idx + 1];

		if (_controls.getClass().isArray())
		{
			//System.out.println("its an array()..");
		}
		//System.out.println("inside idx: " + idx + " tmp length:  " + tmp.length + " value : " + _controls);

		if (this.controls != null)
		{
			for (int i = 0; i < this.controls.length; i++)
				tmp[i] = this.controls[i];
		}
		tmp[idx] = _controls;
		this.controls = tmp;

	}

	public void setControls(java.lang.String[] _controls)
	{
		this.controls = _controls;
	}

	public java.lang.Object[] getControls()
	{
		return controls;
	}

	public void setHmode(java.lang.String hmode)
	{
		this.hmode = hmode;
	}

	public java.lang.String getIsActive()
	{
		return isActive;
	}

	public java.lang.String getHmode()
	{
		return hmode;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		controls = new String[]
		{};
		isActive = "";
	}

	public class MultiSelect
	{
		int idx;

		public MultiSelect(int _idx)
		{
			this.idx = _idx;
		}

		public void setData(String[] _controls)
		{
			//System.out.println("insidesetMultiSelect idex:  " + idx);
			Object[] tmp;

			if (controls != null && controls.length > (idx)) tmp = new Object[controls.length];
			else tmp = new Object[idx + 1];

			//System.out.println("inside setData idx: " + idx + " tmp length:  " + tmp.length + " value : " + _controls);

			if (controls != null)
			{
				for (int i = 0; i < controls.length; i++)
					tmp[i] = controls[i];
			}
			tmp[idx] = _controls;
			controls = tmp;
		}

		public String[] getData()
		{
			try
			{
				//System.out.println("insides getdATA idex:  " + idx);
				Object[] tmp;
				if (controls != null && idx < controls.length)
				{
					return (String[]) controls[idx];
				}
				else
				{
					tmp = new Object[idx + 1];
					if (controls != null)
					{
						for (int i = 0; i < controls.length; i++)
							tmp[i] = controls[i];
						for (int i = controls.length; i < tmp.length; i++)
						{
							tmp[i] = new Object();
						}
					}
					tmp[idx] = new String[0];
				}
				controls = tmp;
				return (String[]) controls[idx];
			}
			catch (Exception e)
			{
				System.out.println("EXcdep[tion" + e);
			}
			return new String[0];
		}
	}

}//end of class 
