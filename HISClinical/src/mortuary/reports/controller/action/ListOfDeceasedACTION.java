package mortuary.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import registration.RegistrationConfig;

import mortuary.MortuaryConfig;
import mortuary.reports.controller.fb.ListOfDeceasedFB;
import mortuary.reports.controller.util.ListOfDeceasedUTL;


public class ListOfDeceasedACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ListOfDeceasedFB fb=(ListOfDeceasedFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ListOfDeceasedUTL.getEssential(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		ListOfDeceasedFB fb=(ListOfDeceasedFB)form;
		//Anant Patel
		String hosCode ="";
        StringBuffer sb=null;
        if(fb.getMultipleHospitalCode()[0].equals("0")) // for all hospitals
        {
        	List<Entry> listAllHospital=(List)request.getSession().getAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST);                 

          for(int i=0;i<listAllHospital.size();i++){
                hosCode+=listAllHospital.get(i).getValue()+",";
          }
         
          sb=new StringBuffer(hosCode);                    
          sb.deleteCharAt(hosCode.length()-1); // for deleting last comma
          fb.setAllHospitalCode(sb.toString());
        }
        // for multiple selected hospitals
        else if(fb.getMultipleHospitalCode().length>1 && !fb.getMultipleHospitalCode()[0].equals("0")){                
          for(int i=0;i<fb.getMultipleHospitalCode().length;i++)
          {
                hosCode+=fb.getMultipleHospitalCode()[i]+",";
          }
           sb=new StringBuffer(hosCode);
          sb.deleteCharAt(hosCode.length()-1); // for deleting last comma
          fb.setAllHospitalCode(sb.toString());               
        }
        // for single selected hospital
        else{
          fb.setAllHospitalCode(fb.getMultipleHospitalCode()[0]);                
        }
        // for multi_hospital selection end here
		
		if(fb.getEntryMode().equals("%"))
			fb.setEntryModeLabel("ALL");
		
		if(fb.getEntryMode().equals(MortuaryConfig.ENTRY_MODE_INHOUSE_SEND_ONLINE))
			fb.setEntryModeLabel("Online");
		
		if(fb.getEntryMode().equals(MortuaryConfig.ENTRY_MODE_INHOUSE_SEND_OFFLINE))
			fb.setEntryModeLabel("Offline");
		
		if(fb.getEntryMode().equals(MortuaryConfig.ENTRY_MODE_BROUGHT_DEAD_FROM_SPOT_BY_POLICE))
			fb.setEntryModeLabel("On the Spot");
		
		if(fb.getEntryMode().equals(MortuaryConfig.ENTRY_MODE_SEND_FROM_OTHER_HOSPITAL))
			fb.setEntryModeLabel("Other Hospital");
		
		
		byte[] byteArray =ListOfDeceasedUTL.generateTextReport(fb,request,getServlet().getServletContext(),response);
		try 
		{	
			OutputStream os =response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			
				if(byteArray!=null)
				{
						response.setHeader("Pragma","no-cache");		 		
						bos.write(byteArray, 0, byteArray.length);
						response.getOutputStream().flush();
						bos.close();			
				}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}

