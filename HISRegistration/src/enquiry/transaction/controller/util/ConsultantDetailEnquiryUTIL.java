package enquiry.transaction.controller.util;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import registration.RegistrationConfig;
import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.ConsultantDetailEnquiryDATA;
import enquiry.transaction.controller.data.DepartmentLocationEnquiryDATA;
import enquiry.transaction.controller.fb.ConsultantDetailEnquiryFB;
import enquiry.transaction.controller.fb.DepartmentLocationEnquiryFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ConsultantDetailVO;
import hisglobal.vo.DepartmentLocationEnquiryVO;
import hisglobal.vo.UserVO;

public class ConsultantDetailEnquiryUTIL extends ControllerUTIL {

	public static void getEssentials(ConsultantDetailEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		UserVO _userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			_fb.setNum_cur_state_id(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			_fb.setNum_cur_country_id(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap=ConsultantDetailEnquiryDATA.getConsultantEssential(_userVO);
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
		finally
		{
			WebUTIL.setStatus(_rq,objStatus);
		 System.out.println("objStatus in finally"+objStatus);		 
		 System.out.println("objStatus list"+objStatus.getStatusList());
		}	
		
	}
	
	public static void searchConsultantDetails(ConsultantDetailEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		UserVO userVO=getUserVO(_rq);
		Status objStatus=new Status();
		ConsultantDetailVO consultantDetailVO=new ConsultantDetailVO();
		ConsultantDetailVO[] consultantDetailVOs=null;
		try{
			setSysdate(_rq);
			HelperMethods.populate(consultantDetailVO, _fb);
			consultantDetailVOs=ConsultantDetailEnquiryDATA.searchConsultantDetails(consultantDetailVO,userVO);
			for(int i=0;i<consultantDetailVOs.length;i++){
				HelperMethods.populatetToNullOrEmpty(consultantDetailVOs[i], new ConsultantDetailVO());
			}
			_rq.setAttribute(enquiryConfig.CONSULTANT_DETAIL_VO_ARRAY,consultantDetailVOs);
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
		finally
		{
			WebUTIL.setStatus(_rq,objStatus);
		 System.out.println("objStatus in finally"+objStatus);		 
		 System.out.println("objStatus list"+objStatus.getStatusList());
		}
		
	}
	
}
