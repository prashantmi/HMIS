
package enquiry.transaction.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.BloodDonorDATA;
import enquiry.transaction.controller.fb.BloodDonorFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.AddressEnqiryVO;
import enquiry.vo.BloodDonorEnquriyVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class BloodDonorUTIL extends ControllerUTIL{
	
	public static void getBloodDonorGroups(BloodDonorFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		List bloodGroup=new ArrayList();
		
		BloodDonorEnquriyVO[] bloodDonorGroups=null;
		UserVO _userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			
			bloodGroup=BloodDonorDATA.getBloodDonorGroups(_userVO);
			
			
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.ALL_BLOOD_GROUPS_LIST, bloodGroup);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
		
	}
	
	public static void getBloodDonorEnquiryDetails(BloodDonorFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		Map BloodStockEnquiryMap=new LinkedHashMap();
		
		BloodDonorEnquriyVO _bloodDonorVO=new BloodDonorEnquriyVO();
		
	/*if(_fb.getEmergencyCall()==null || _fb.getEmergencyCall().equals("") )
			_fb.setEmergencyCall("("+enquiryConfig.EMERGENCY_CALL_NO+","+enquiryConfig.EMERGENCY_CALL_YES+")");
		else
	if(_fb.getEmergencyCall().equals("1") )
				_fb.setEmergencyCall("("+enquiryConfig.EMERGENCY_CALL_YES+")");
	*/			
		   
if(_fb.getEmergencyCall()==null || _fb.getEmergencyCall().equals("") )
		_bloodDonorVO.setDonationEmergencycode("("+enquiryConfig.EMERGENCY_CALL_NO+","+enquiryConfig.EMERGENCY_CALL_YES+")");
	else
if(_fb.getEmergencyCall().equals("1"))
			_bloodDonorVO.setDonationEmergencycode("("+enquiryConfig.EMERGENCY_CALL_YES+")");
	
	
	    _bloodDonorVO.setBloodGroupCode(_fb.getBloodGroup());
		
		BloodDonorEnquriyVO[] bloodDonorsEnquiry=null;
		UserVO _userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			
			bloodDonorsEnquiry=BloodDonorDATA.getBloodDonorEnquiryDetails(_userVO,_bloodDonorVO);
			
			//if(_fb.getEmergencyCall().equals("1") && _fb.getEmergencyCall()!=null )
			//	_fb.setEmergencyCall(enquiryConfig.EMERGENCY_CALL_YES);
			
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.VOLUNTARY_BLOOD_DONORS, bloodDonorsEnquiry);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		
		}
		catch(HisApplicationExecutionException e){		
			
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
		
	}

}
