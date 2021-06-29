package opd.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.reports.controller.data.CountNoOfPatientOfAnoRectangleDiseaseReportDATA;
import opd.reports.controller.fb.CountNoOfPatientOfAnoRectangleDiseaseReportFB;
import opd.transaction.controller.data.OpdDiagnosisDATA;
import registration.RegistrationConfig;

import a.a.e;

import com.sun.tools.jdi.LinkedHashMap;

public class CountNoOfPatientOfAnoRectangleDiseaseReportUTIL extends ReportUTIL
{
	public static String getAllHospitalCode(CountNoOfPatientOfAnoRectangleDiseaseReportFB fb,HttpServletRequest request)
	{
		
		String hosCode ="", strAllHospitalCode="";
		Status objStatus= new Status();
        StringBuffer sb=null;
	try{	
		setSysdate(request);
		
		if(fb.getMultipleHospitalCode()[0].equals("0")) // for all hospitals
        {
        	List<Entry> listAllHospital=(List)request.getSession().getAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST);                 

          for(int i=0;i<listAllHospital.size();i++){
                hosCode+=listAllHospital.get(i).getValue()+",";
          }
         
          sb=new StringBuffer(hosCode);                    
          sb.deleteCharAt(hosCode.length()-1); // for deleting last comma
          strAllHospitalCode=sb.toString();
        }
        // for multiple selected hospitals
        else if(fb.getMultipleHospitalCode().length>1 && !fb.getMultipleHospitalCode()[0].equals("0")){                
          for(int i=0;i<fb.getMultipleHospitalCode().length;i++)
          {
                hosCode+=fb.getMultipleHospitalCode()[i]+",";
          }
           sb=new StringBuffer(hosCode);
          sb.deleteCharAt(hosCode.length()-1); // for deleting last comma
          strAllHospitalCode=sb.toString();               
        }
        // for single selected hospital
        else{
        	strAllHospitalCode=fb.getMultipleHospitalCode()[0];                
        }
        // for multi_hospital selection end here

		
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
	}
	catch(HisDataAccessException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR,"","Error");
	}		
	finally
	{
		WebUTIL.setStatus(request,objStatus);
		System.out.println("   -----> objStatus in finally  : "+objStatus);
		System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		
	}	
	return strAllHospitalCode;
  }
	
	
	public static void getEssential(CountNoOfPatientOfAnoRectangleDiseaseReportFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		HttpSession objSession = request.getSession();
	try{	
		setSysdate(request);
		//Date dt=CountNoOfPatientOfAnoRectangleDiseaseReportDATA.getSysDateAsDate(); 
		//WebUTIL.getSession(request).setAttribute(RegistrationConfig.SYSADATEOBJECT,dt);
		
		Map map=(HashMap)CountNoOfPatientOfAnoRectangleDiseaseReportDATA.getAllHospitalEssentials(getUserVO(request));
		WebUTIL.setMapInSession(map, request);
		
		Map mp2=(HashMap)CountNoOfPatientOfAnoRectangleDiseaseReportDATA.getMinYearForEssentialForReport(getUserVO(request),"1");
		WebUTIL.setMapInSession(mp2, request);
		String strSysMonthYear = WebUTIL.getCustomisedSysDate((Date) objSession.getAttribute(Config.SYSDATEOBJECT), "MMyyyy");
		String strMinYear = (String) mp2.remove(OpdConfig.REPORTS_MINIMUM_YEAR);
		if(strMinYear==null || strMinYear.trim().equals(""))	strMinYear = strSysMonthYear.substring(2,6);
		fb.setStrFromYear(strSysMonthYear.substring(2,6));
		fb.setStrToYear((Integer.parseInt(strSysMonthYear.substring(2,6))+1)+"");
		fb.setStrMinYear(strMinYear);
				
		List<Entry> lstYears = getLstYears(fb.getStrMinYear(), fb.getStrToYear(), request);
		mp2.put(OpdConfig.REPORTS_LIST_OF_MIN_YEARS, lstYears);
		WebUTIL.setMapInSession(mp2, request);
		
		//fb.setChoice(Config.CHOICE_TODAY);
		fb.setGraphOrText(Config.TEXT);	
		fb.setReportType(Config.RTF);
		
		String[] multipleHospitalArray = new String[1];
		multipleHospitalArray[0] = ControllerUTIL.getUserVO(request).getHospitalCode();
		fb.setMultipleHospitalCode(multipleHospitalArray);
		
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		//objStatus.add(Status.UNSUCESSFULL,"","Data Not Found Error");
	}
	catch(HisDataAccessException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_DA,"","Data Access Error");
	}
	catch(HisApplicationExecutionException e)
	{		
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE,"","Application Execution Error");
	}
	catch(HisException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR,"","Error");
	}
	catch(Exception e){
		e.printStackTrace();
	}
	finally
	{
		WebUTIL.setStatus(request,objStatus);
		System.out.println("   -----> objStatus in finally  : "+objStatus);
		System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
	}	
  }
	
	// Getting Year List for Duration
	public static List<Entry> getLstYears(String strFromYear_p, String strToYear_p, HttpServletRequest objRequest_p)
	{
		Status objStatus = new Status();
		List<Entry> lstYears = new ArrayList<Entry>();
		try
		{
			int nFromYear = Integer.parseInt(strFromYear_p) -1;
			int nToYear = Integer.parseInt(strToYear_p) -1;
			for(int i=nToYear;i>=nFromYear;i--)
			{
				Entry objEntry = new Entry();
				objEntry.setValue(Integer.toString(i));
				objEntry.setLabel(Integer.toString(i));
				lstYears.add(objEntry);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p, objStatus);
		}
		return lstYears;
	}
	
	
	
	
}
