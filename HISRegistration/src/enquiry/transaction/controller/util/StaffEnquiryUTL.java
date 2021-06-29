package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;
import enquiry.*;
import javax.servlet.http.HttpServletRequest;

import registration.RegistrationConfig;
import enquiry.transaction.controller.data.HospitalTelephoneEnquiryDATA;
import enquiry.transaction.controller.data.StaffEnquiryDATA;
import enquiry.transaction.controller.fb.StaffEnquiryFB;
import enquiry.vo.StaffEnquiryVO;

public class StaffEnquiryUTL extends ControllerUTIL{
	
	public static void getEssentials(StaffEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		Map essentialMap=null;
		UserVO _userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			
			_fb.setHospitalCode(_userVO.getHospitalCode());
			
			List hospitalList=StaffEnquiryDATA.getHospitalCombo();
		    WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
			essentialMap=StaffEnquiryDATA.getStaffEnquiryEssentials(_userVO);
			WebUTIL.setMapInSession(essentialMap,_rq);
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
		
	}
	
	public static void getStaffEssentialsOnHospitalChange(StaffEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		Map essentialMap=null;
		UserVO _userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			_userVO.setHospitalCode(_fb.getHospitalCode());
			
			List hospitalList= (List)_rq.getSession().getAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST);
			WebUTIL.refreshTransState(_rq);
			WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			
			essentialMap=StaffEnquiryDATA.getStaffEnquiryEssentials(_userVO);
			WebUTIL.setMapInSession(essentialMap,_rq);
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
		
	}
	
	public static void searchStaffDetail(StaffEnquiryFB _fb, HttpServletRequest request){
		
		Status objStatus=new Status();
		UserVO _userVO=getUserVO(request);
		StaffEnquiryVO staffEnquiryVO=new StaffEnquiryVO();
		StaffEnquiryVO[] staffEnquiryVOs=null;
		try{
			setSysdate(request);
			_userVO.setHospitalCode(_fb.getHospitalCode());
			HelperMethods.populate(staffEnquiryVO, _fb);
			staffEnquiryVOs=StaffEnquiryDATA.searchStaffDetail(staffEnquiryVO,_userVO);
			StaffEnquiryVO[] arrayStaffEnquiryVOs=new StaffEnquiryVO[staffEnquiryVOs.length];
			for(int i=0;i<staffEnquiryVOs.length;i++)
			{
				arrayStaffEnquiryVOs[i]=new StaffEnquiryVO();
				HelperMethods.populatetToNullOrEmpty(staffEnquiryVOs[i], arrayStaffEnquiryVOs[i]);
			}
			WebUTIL.setAttributeInSession(request, enquiryConfig.STAFF_ENQUIRY_STAFF_ENQUIRY_VO, staffEnquiryVOs);
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
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
}
