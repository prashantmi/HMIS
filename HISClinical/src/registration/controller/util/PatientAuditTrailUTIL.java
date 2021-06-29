package registration.controller.util;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.GregorianCalendar;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;

import hisglobal.vo.AddressVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.transaction.controller.data.EmployeeDutyRosterDATA;
import dutyroster.transaction.controller.fb.EmployeeDutyRosterFB;
import dutyroster.transaction.controller.utl.EmployeeDutyRosterUTL;

import registration.RegistrationConfig;
import registration.controller.data.PatientAuditTrailDATA;
import registration.controller.fb.PatientAuditTrailFB;



public class PatientAuditTrailUTIL extends ControllerUTIL  
{

	
	// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst

	public static boolean getPatientAuditTrailEssentials(HttpServletRequest _request,PatientAuditTrailFB _fb)
	{
		Status objStatus = new Status();
		
		Map EssentialMap=new HashMap();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
		
			EssentialMap=PatientAuditTrailDATA.getPatientAuditTrailEssentials(_fb.getPatCrNo(),_userVO);			
			WebUTIL.setMapInSession(EssentialMap, _request);
			
		PatientDetailVO[] _patAuditDetailVO=(PatientDetailVO[])EssentialMap.get(RegistrationConfig.VO_S_OF_HRGT_PATIENT_AUDIT_DTL);
		PatientDetailVO _patDetailVO=(PatientDetailVO)EssentialMap.get(RegistrationConfig.VO_OF_HRGT_PATIENT_DTL);
		
		String seatIdForPatDtlVO="";
		String entryDateForPatDtlVO="";
		
		//saving the seatId & entry date of _patDetailVO to temproary variables to be set later to the last VO
		//of _patAuditDetailVO
if(_patDetailVO!=null ){
		seatIdForPatDtlVO=_patDetailVO.getSeatId();
		entryDateForPatDtlVO=_patDetailVO.getRegisterDate();
		}
		
		
		

  if(_patAuditDetailVO!=null && _patAuditDetailVO.length!=0)
	{
			
	  		PatientDetailVO _patDetailVONew=_patDetailVO;			
			PatientDetailVO[] _patAuditDetailVONew=_patAuditDetailVO;
			
			String seatIdFor1stAuditDtlVO="";
			String entryDateFor1stAuditDtlVO="";
			
			//getting seatId & entry date details for 1st VO
			
			seatIdFor1stAuditDtlVO=_patAuditDetailVO[0].getLastModifySeatId();
			entryDateFor1stAuditDtlVO=_patAuditDetailVO[0].getLastModifyDate();
			
	for(int i=0; i < _patAuditDetailVO.length;i++)
			{
				String requestByName="";
				String lastModifySeatId="";
				String lastModifyDate="";
				
				
			if(i < _patAuditDetailVO.length-1){	
					requestByName=_patAuditDetailVO[i+1].getRequestByName();
					lastModifySeatId=_patAuditDetailVO[i+1].getLastModifySeatId();
					lastModifyDate=_patAuditDetailVO[i+1].getLastModifyDate();				
				}

				
			//if the value is not last then set the
			//[request by name,seatId,entrydate] of the next one to the previous one
			if(i < _patAuditDetailVO.length-1){
				_patAuditDetailVONew[i].setRequestByName(requestByName);
				_patAuditDetailVONew[i].setLastModifySeatId(lastModifySeatId);
				_patAuditDetailVONew[i].setLastModifyDate(lastModifyDate);
			}	
				else//if the value is last one then put the seatid of seatIdForPatDtlVO & entryDateForPatDtlVO into the last one
			if(i == _patAuditDetailVO.length-1){	
				_patAuditDetailVONew[i].setRequestByName(requestByName);
				_patAuditDetailVONew[i].setLastModifySeatId(seatIdForPatDtlVO);
				_patAuditDetailVONew[i].setLastModifyDate(entryDateForPatDtlVO);
			   }	
			
			
			
			
			
				
			}  
	
	//now setting the seatId and entry date of 1st patAuditDtlVO to the PatientDtlVO	
	_patDetailVONew.setSeatId(seatIdFor1stAuditDtlVO);
	_patDetailVONew.setRegisterDate(entryDateFor1stAuditDtlVO);
	
	
	WebUTIL.setAttributeInSession(_request, RegistrationConfig.VO_S_OF_HRGT_PATIENT_AUDIT_DTL, _patAuditDetailVONew);
	WebUTIL.setAttributeInSession(_request, RegistrationConfig.VO_OF_HRGT_PATIENT_DTL, _patDetailVONew);
	
	}
		




		
}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}	
	


}//End of class
