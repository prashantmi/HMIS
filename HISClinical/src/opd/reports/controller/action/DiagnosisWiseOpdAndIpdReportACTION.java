
package opd.reports.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.reports.controller.fb.DiagnosisWiseOpdAndIpdReportFB;
import opd.reports.controller.util.DiagnosisWiseOpdAndIpdReportUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import registration.RegistrationConfig;

public class DiagnosisWiseOpdAndIpdReportACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DiagnosisWiseOpdAndIpdReportFB fb= (DiagnosisWiseOpdAndIpdReportFB)form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		
		DiagnosisWiseOpdAndIpdReportUTIL.getEssential(fb,request);
		//DiagnosisWiseOpdAndIpdReportUTIL.getAllConsultant(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward TEXT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws HisReportDataNotFoundException
	{
		DiagnosisWiseOpdAndIpdReportFB fb= (DiagnosisWiseOpdAndIpdReportFB) form;	
		
		try{
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
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		/*if(fb.getIsDynamicQueryRequired().equals(OpdConfig.DYNAMIC_QUERY_REQ_YES))
		{
			// Setting Dynamic Query
			StringBuilder sbDynamicQuery = new StringBuilder();
			sbDynamicQuery.append(" ");
				// Date
			
			if(fb.getChoice().equals(Config.CHOICE_DATE_WISE))
			{
				sbDynamicQuery.append("AND"); sbDynamicQuery.append(" ");
				sbDynamicQuery.append("TRUNC");	sbDynamicQuery.append("(");
				sbDynamicQuery.append("a.HRGDT_EPISODE_DATE");
				sbDynamicQuery.append(")"); sbDynamicQuery.append(">"); sbDynamicQuery.append("=");
				sbDynamicQuery.append("TRUNC"); sbDynamicQuery.append("(");
				sbDynamicQuery.append("TO_DATE"); sbDynamicQuery.append("(");
				sbDynamicQuery.append("'"); sbDynamicQuery.append(fb.getFromDate()); sbDynamicQuery.append("'");
				sbDynamicQuery.append(",'dd-Mon-yyyy'");sbDynamicQuery.append(")");sbDynamicQuery.append(")");
				
				sbDynamicQuery.append(" "); sbDynamicQuery.append("AND"); sbDynamicQuery.append(" ");
				sbDynamicQuery.append("TRUNC");	sbDynamicQuery.append("(");
				sbDynamicQuery.append("a.HRGDT_EPISODE_DATE");
				sbDynamicQuery.append(")"); sbDynamicQuery.append("<"); sbDynamicQuery.append("=");
				sbDynamicQuery.append("TRUNC"); sbDynamicQuery.append("(");
				sbDynamicQuery.append("TO_DATE"); sbDynamicQuery.append("(");
				sbDynamicQuery.append("'"); sbDynamicQuery.append(fb.getToDate()); sbDynamicQuery.append("'");
				sbDynamicQuery.append(",'dd-Mon-yyyy'");sbDynamicQuery.append(")");sbDynamicQuery.append(")");
			}
			else
			{
				sbDynamicQuery.append("AND"); sbDynamicQuery.append(" ");
				sbDynamicQuery.append("TRUNC");	sbDynamicQuery.append("(");
				sbDynamicQuery.append("a.HRGDT_EPISODE_DATE");
				sbDynamicQuery.append(")"); sbDynamicQuery.append("=");
				sbDynamicQuery.append("TRUNC"); sbDynamicQuery.append("("); sbDynamicQuery.append("SYSDATE");sbDynamicQuery.append(")");
				
				sbDynamicQuery.append(" "); sbDynamicQuery.append("AND"); sbDynamicQuery.append(" ");
				sbDynamicQuery.append("TO_DATE"); sbDynamicQuery.append("("); sbDynamicQuery.append("TO_CHAR"); sbDynamicQuery.append("(");
				sbDynamicQuery.append("a.HRGDT_EPISODE_DATE"); sbDynamicQuery.append(",'hh24:mi'");
				sbDynamicQuery.append(")"); sbDynamicQuery.append(",'hh24:mi'"); sbDynamicQuery.append(")"); 
				sbDynamicQuery.append(">"); sbDynamicQuery.append("=");
				sbDynamicQuery.append("TO_DATE"); sbDynamicQuery.append("(");
				sbDynamicQuery.append("'"); sbDynamicQuery.append(fb.getFromHour()); 
				sbDynamicQuery.append(":"); sbDynamicQuery.append(fb.getFromMin()); sbDynamicQuery.append("'");
				sbDynamicQuery.append(",'hh24:mi'"); sbDynamicQuery.append(")");  
				
				sbDynamicQuery.append(" "); sbDynamicQuery.append("AND"); sbDynamicQuery.append(" ");
				sbDynamicQuery.append("TO_DATE"); sbDynamicQuery.append("("); sbDynamicQuery.append("TO_CHAR"); sbDynamicQuery.append("(");
				sbDynamicQuery.append("a.HRGDT_EPISODE_DATE"); sbDynamicQuery.append(",'hh24:mi'");
				sbDynamicQuery.append(")"); sbDynamicQuery.append(",'hh24:mi'"); sbDynamicQuery.append(")"); 
				sbDynamicQuery.append("<"); sbDynamicQuery.append("=");
				sbDynamicQuery.append("TO_DATE"); sbDynamicQuery.append("(");
				sbDynamicQuery.append("'");sbDynamicQuery.append(fb.getToHour()); sbDynamicQuery.append(":"); 
				sbDynamicQuery.append(fb.getToMin()); sbDynamicQuery.append("'");
				sbDynamicQuery.append(",'hh24:mi'"); sbDynamicQuery.append(")");  			
			}
			
			sbDynamicQuery.append(" "); sbDynamicQuery.append("ORDER BY"); sbDynamicQuery.append(" ");
			sbDynamicQuery.append("TRUNC(a.HRGDT_EPISODE_DATE)");
			
			fb.setDynamicQuery(sbDynamicQuery.toString());
			System.out.println("DynamicQuery :"+fb.getDynamicQuery());
		}*/
		
		
		try {	
			byte[] byteArray =DiagnosisWiseOpdAndIpdReportUTIL.generateTextReport(fb,request,getServlet().getServletContext(),response);		
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
		catch(HisReportDataNotFoundException e)
		{
			return mapping.findForward("ERROR");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
			return null;
	 }

}
