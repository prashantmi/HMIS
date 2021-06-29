package hisglobal.masterxml.masterworkshop;

import java.util.*;
import org.apache.struts.action.*;
import javax.servlet.http.*;

public class MasterHandlerFormBn extends ActionForm
{
  
  String controls[];
  String chk[];
  String isActive="";
  String hmode="";  
  
 
  public void setIsActive( java.lang.String isActive )
  {
  	this.isActive = isActive;
  }

  public void setControls( java.lang.String[] controls )
  {
  	this.controls = controls;
  }

  public java.lang.String[] getControls( )
  {
  	return controls;
  }

  public void setHmode( java.lang.String hmode )
  {
  	this.hmode = hmode;
  }

  public void setChk( java.lang.String[] chk )
  {
  	this.chk = chk;
  }

  public java.lang.String getIsActive( )
  {
  	return isActive;
  }

  public java.lang.String getHmode( )
  {
  	return hmode;
  }

  public java.lang.String[] getChk( )
  {
  	return chk;
  }  
  public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		return;
	}  
   	
}//end of class 