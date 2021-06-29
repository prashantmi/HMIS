package opd.reports.controller.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.RegistrationConfig;
import registration.reports.report1.controller.data.CategoryWiseUserWiseRegistrationDATA;
import opd.reports.controller.data.OpdDeptUnitUserWiseStatDATA;
import opd.reports.controller.data.OpdPatientAttendedDATA;
import opd.reports.controller.fb.OPDPatientAttendedReportFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

public class OPDPatientAttendedReportUTIL extends ReportUTIL {
	/**
	 * @author Singaravelan
	 * Creation Date:- 09 Sep-2013 
	 * Modifying Date:- 
	 * Used For:-OPD Patient Attended Report
	 * Team Lead By:- 
	 * Module:- OPD (HIS Project)
	 * 
	 */
	
	/*
	 * This method sets the essential details
	 */
	public static void getEssentials(OPDPatientAttendedReportFB _fb, HttpServletRequest _request) {

		
		   Status objStatus= new Status();
		
	 try{
		 
		 setSysdate(_request);
		 _fb.setGraphOrText(Config.TEXT);	
		 _fb.setReportType(Config.RTF);
	            
	     UserVO userVO=ControllerUTIL.getUserVO(_request);
	     
	     List deptList = OpdPatientAttendedDATA.getAllDepartment(userVO);
	     WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,deptList);
	     List userList=OpdPatientAttendedDATA.getUser(getUserVO(_request));
	     WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIALBO_OPTION_USER,userList);
	     
	     //WebUTIL.setMapInSession(essentialMap, _request);
	     
	 }catch(HisRecordNotFoundException e)
		{
		   WebUTIL.setAttributeInSession(_request, RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT,new ArrayList());
		   WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,new ArrayList());
		   System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
	 
	 	  
	 catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		
	
		
	}

				
}
