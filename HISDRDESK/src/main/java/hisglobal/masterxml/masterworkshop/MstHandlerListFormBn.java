package hisglobal.masterxml.masterworkshop;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class MstHandlerListFormBn extends ActionForm
{

	String controls[] = {};
	String chk[];
	String isActive = "";
	String hmode = "";
	int sortOn = 1;
	String comboSearch = "";
	String txtSearch = "";
	String pageno = "";
	String pageoptions = "";

	//String viewType="0";// >>>>value 0 represents normal list view & 1 represents veiw based on search string

	public String getPageoptions()
	{
		return pageoptions;
	}

	public void setPageoptions(String pageoptions)
	{
		this.pageoptions = pageoptions;
	}

	public String getPageno()
	{
		return pageno;
	}

	public void setPageno(String pageno)
	{
		this.pageno = pageno;
	}

	public void setTxtSearch(java.lang.String txtSearch)
	{
		this.txtSearch = txtSearch.trim();
	}

	public java.lang.String getTxtSearch()
	{
		return txtSearch;
	}

	public java.lang.String getComboSearch()
	{
		return comboSearch;
	}

	public void setComboSearch(java.lang.String comboSearch)
	{
		this.comboSearch = comboSearch;
	}

	public int getSortOn()
	{
		return sortOn;
	}

	public void setSortOn(int sortOn)
	{
		this.sortOn = sortOn;
	}

	public void setIsActive(java.lang.String isActive)
	{
		this.isActive = isActive;
	}

	/*
	 public void setControls( java.lang.String[] controls )
	 {
	 this.controls = controls;
	 }*/
	public void setControls(int idx, java.lang.String _controls)
	{
		//System.out.println("inside idex list bean:  " + idx);
		String[] tmp;

		if (this.controls != null && this.controls.length > (idx)) tmp = new String[this.controls.length];
		else tmp = new String[idx + 1];

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

	public java.lang.String[] getControls()
	{
		return controls;
	}

	public void setHmode(java.lang.String hmode)
	{
		this.hmode = hmode;
	}

	public void setChk(java.lang.String[] chk)
	{
		this.chk = chk;
	}

	public java.lang.String getIsActive()
	{
		return isActive;
	}

	public java.lang.String getHmode()
	{
		return hmode;
	}

	public java.lang.String[] getChk()
	{
		return chk;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		controls = new String[]
		{};
		chk = new String[]
		{};
		isActive = "";
		comboSearch = "";
		txtSearch = "";
		pageno = "0";
		hmode = "";

	}

	/*public ArrayList getRows()
	  {  
	   MasterTO objMasterTo = (MasterTO)session.getAttribute("TOLstObject");
	   ArrayList alData=((MasterListTO)objMasterTo).getAlData(sortOn);
	   int noOfCol=((MasterListTO)objMasterTo).getNoOfColumn()+1; 	   
	   MstHandlerListFormBn.ListRow  objListRow=this.new ListRow();				
	   Collection col=	objListRow.buildRows(alData,noOfCol ,sortOn);			
	}*/

}//end of class

