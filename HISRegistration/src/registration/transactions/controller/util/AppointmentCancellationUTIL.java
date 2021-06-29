package registration.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;
import registration.config.RegistrationConfig;
import registration.transactions.controller.data.AppointmentCancellationDATA;
import registration.transactions.controller.data.PrimaryCategoryChangeDATA;
import vo.appointment.AppointmentVO;
import vo.registration.DirectChageDetailVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.PrimaryCategoryChangeVO;
import vo.registration.RegCardPrintVO;
import  registration.transactions.controller.actionsupport.AppointmentCancellationSUP;
import vo.registration.RenewalConfigVO; 


public class AppointmentCancellationUTIL extends ControllerUTIL{

	public static void setAllSpecialCRNoWithAppointment(AppointmentCancellationSUP _aptsup ,HttpServletRequest _request,HttpServletResponse _response)
	{
		
		
		  Status objStatus=new Status();
		  Map mp=new HashMap();
	      HttpSession ses=WebUTIL.getSession(_request);
		 
		try{
			 
			 UserVO userVO =getUserVO(_request);
			 setSysdate(_request);
				 
			 //checking request is from which desk
			 
			 ////this value is set in SpecialClinicRegDeskUTIL for special clinic
			 String deskType=(String)_request.getAttribute(RegistrationConfig.REGISTRATION_DESK_TYPE);
			 String episodeVisitType;
			 if(deskType==null)
				 deskType="";
			 if(deskType.equalsIgnoreCase(RegistrationConfig.REGISTRATION_DESK_TYPE_SPECIAL))
				 episodeVisitType=RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL;
			 else
				 episodeVisitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
			 
			 PatientVO[] patvo =AppointmentCancellationDATA.getCRNoWithAppointment(userVO,episodeVisitType);
 			
 			  
 			  WebUTIL.setMapInSession(mp,_request,"AppointmentCancellationAction");
 			
 			  
		WebUTIL.setAttributeInSession(_request,RegistrationConfig.ALL_PATIENT_VO_LIST,patvo);
		WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,patvo);
		objStatus.add(Status.LIST,"","");	
		   }
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
		WebUTIL.setStatus(_request,objStatus);			
		 
		}
	}
	
	
	public static void searchSpecialCRNoWithAppointment(HttpServletRequest _request,AppointmentCancellationSUP _fb){
		Status objStatus=new Status();
		 PatientVO patientVO[]=null;
		 PatientVO _patVO=new PatientVO();

		try{
			 UserVO userVO =getUserVO(_request);
			 setSysdate(_request);			 
			 
			 
			 if(_patVO.getSearchId().equalsIgnoreCase("1"))
					 _patVO.setSearchId("HAPNUM_APT_NO");
			 else if(_patVO.getSearchId().equalsIgnoreCase("2"))
					 _patVO.setSearchId("HAPSTR_PAT_FIRST_NAME");
			 else if(_patVO.getSearchId().equalsIgnoreCase("3"))
					 _patVO.setSearchId("HAPSTR_MOBILE_NO");	
				
				 
			 patientVO=AppointmentCancellationDATA.searchdetailsWithAppointment(_patVO,userVO);		
		
		WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,patientVO);
		objStatus.add(Status.LIST,"","");	
		   }
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
		WebUTIL.setStatus(_request,objStatus);			
		 
		}
	}
	
	public static void setVoArrEssentials(HttpServletRequest _request,AppointmentCancellationSUP _fb)
	{
		Status objStatus=new Status();
		HttpSession _ses=_request.getSession();
		PatientVO[] _voArr=((PatientVO[])_ses.getAttribute(RegistrationConfig.ALL_PATIENT_VO_LIST));
		PatientVO[] _newVoArr={};
		List<PatientVO> lstPatVo=new ArrayList<PatientVO>();
		try{
			for(PatientVO _voTemp:_voArr){			
				if((_fb.getSearchId().equalsIgnoreCase("1")&&_voTemp.getPatAptNo().contains(_fb.getSearchValue()))||
						(_fb.getSearchId().equalsIgnoreCase("2")&&_voTemp.getPatFirstName().toUpperCase().contains(_fb.getSearchValue().toUpperCase()))||
						(_fb.getSearchId().equalsIgnoreCase("3")&&_voTemp.getPatAddContactNo().contains(_fb.getSearchValue())))						
					   lstPatVo.add(_voTemp);
				
			}	
			_newVoArr=new PatientVO[lstPatVo.size()];
			if(lstPatVo.size()>0){
				for (int i = 0; i < lstPatVo.size(); i++){
					_newVoArr[i] = (PatientVO) lstPatVo.get(i);
				}
			}	
			else{
				WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,null);
				WebUTIL.setAttributeInSession(_request,RegistrationConfig.ALL_PATIENT_VO_LIST,_voArr);
				throw new HisRecordNotFoundException("Patient Appointment Details Not Found");
			}
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.ALL_PATIENT_VO_LIST,_voArr);
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,_newVoArr);
			objStatus.add(Status.LIST,"","");
		}
		catch (HisRegistrationTimingExpiredException e) {
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			WebUTIL.removeFromStatus(_request,Status.NEW);
			e.printStackTrace();
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			objStatus.add(Status.LIST,"","");
			e.printStackTrace();
		}
		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}
		
		finally
		{
		WebUTIL.setStatus(_request,objStatus);			
		 
		}
		
	}	
	
public static boolean saveAppointmentCancellation(AppointmentCancellationSUP _fb,HttpServletRequest objRequest_p)
	{
	UserVO objUserVO =getUserVO(objRequest_p);
	Status objStatus=new Status();
	AppointmentVO _objAptVo=new AppointmentVO();
	HttpSession ses=WebUTIL.getSession(objRequest_p);	
	boolean aptStatus=false;
	
	try{
		
		
		_objAptVo.setPatAptNo(_fb.getPatAptNo());
		_objAptVo.setRemarks(_fb.getRemarks());
		
		
		aptStatus = AppointmentCancellationDATA.saveaptcancellationdtl(_objAptVo,objUserVO);
		
			if (aptStatus == false) 
			{
				_fb.setStrNormalMsg("No Appointment  Exist..!");
			} 
			else 
			{
				_fb.setStrNormalMsg("Data Saved Successfully");
				objStatus.add(Status.DONE,"Appointment cancelled Successfully" ,"");
			}
			//objStatus.add(Status.DONE,"Appointment cancelled Successfully" ,"");
		//objStatus.add(Status.DONE,"Appointment cancelled  for "+_fb.getPatAptNo(),"");
	} 
	catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		System.out.println(e.getMessage());
		//objStatus.add(Status.ERROR,"",e.getMessage());
		_fb.setErrorMessage(e.getMessage());
	}
	catch(HisDataAccessException e)
	{
		e.printStackTrace();
		//objStatus.add(Status.ERROR_DA,"","Record Not Found");	
		_fb.setErrorMessage("Record Not Found");
	}
	catch(HisApplicationExecutionException e)
	{		
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE,"","Transaction Failed");
	}
	catch(HisException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR,"","Transaction Failed");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE,"","Transaction Failed");
	}
	finally
	{
		WebUTIL.setStatus(objRequest_p,objStatus);
	}
	return aptStatus;
}
}



