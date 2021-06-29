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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.reports.controller.data.DiagnosisWiseOpdAndIpdReportDATA;
import opd.reports.controller.fb.DiagnosisWiseOpdAndIpdReportFB;
import opd.transaction.controller.data.OpdDiagnosisDATA;
import registration.RegistrationConfig;

import com.sun.tools.jdi.LinkedHashMap;

public class DiagnosisWiseOpdAndIpdReportUTIL extends ReportUTIL
{
	public static String getAllHospitalCode(DiagnosisWiseOpdAndIpdReportFB fb,HttpServletRequest request)
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
	
	
	public static void getEssential(DiagnosisWiseOpdAndIpdReportFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	try{	
		setSysdate(request);
		//Date dt=DiagnosisWiseOpdAndIpdReportDATA.getSysDateAsDate(); 
		//WebUTIL.getSession(request).setAttribute(RegistrationConfig.SYSADATEOBJECT,dt);
		
		Map map=(HashMap)DiagnosisWiseOpdAndIpdReportDATA.getAllHospitalEssentials(getUserVO(request));
		WebUTIL.setMapInSession(map, request);
		
		Map essentialMap = OpdDiagnosisDATA.getHospitalDiagnosisEssential(getUserVO(request), "0", "0");	//Getting data for Hospital diagnosis
		WebUTIL.setMapInSession(essentialMap, request);
		/*Map mp=DiagnosisWiseOpdAndIpdReportDATA.getAllConsultantForReportEssentials(ControllerUTIL.getUserVO(request));
		WebUTIL.setMapInSession(mp, request);*/
		
		fb.setChoice(Config.CHOICE_TODAY);
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
	finally
	{
		WebUTIL.setStatus(request,objStatus);
		System.out.println("   -----> objStatus in finally  : "+objStatus);
		System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
	}	
  }
	
	public static void getAllConsultant(DiagnosisWiseOpdAndIpdReportFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		UserVO userVO = new UserVO();
		String hosCode ="";
        StringBuffer sb=null;
	try{	
		fb.setAllHospitalCode(getAllHospitalCode(fb,request));

		
		userVO.setHospitalCode(fb.getAllHospitalCode());
		Map mp=DiagnosisWiseOpdAndIpdReportDATA.getAllConsultantForReportEssentials(userVO);
		WebUTIL.setMapInSession(mp, request);
		
		/*fb.setChoice(Config.CHOICE_TODAY);
		fb.setGraphOrText(Config.TEXT);	
		fb.setReportType(Config.RTF);
		
		String[] multipleHospitalArray = new String[1];
		multipleHospitalArray[0] = ControllerUTIL.getUserVO(request).getHospitalCode();
		fb.setMultipleHospitalCode(multipleHospitalArray);*/
		
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
	finally
	{
		WebUTIL.setStatus(request,objStatus);
		System.out.println("   -----> objStatus in finally  : "+objStatus);
		System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
	}	
  }
	
	
	
	
}
