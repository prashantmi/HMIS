package enquiry.transaction.controller.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.DepartmentLocationEnquiryDATA;
import enquiry.transaction.controller.data.PatientEnquiryDATA;
import enquiry.transaction.controller.fb.DepartmentLocationEnquiryFB;
import enquiry.transaction.controller.fb.PatientEnquiryFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DepartmentLocationEnquiryVO;
import hisglobal.vo.UserVO;

public class DepartmentLocationEnquiryUTIL extends ControllerUTIL {
	public static void getEssentials(DepartmentLocationEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		UserVO _userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			essentialMap=DepartmentLocationEnquiryDATA.getDepartmentLocationEnquiryEssentials(_userVO);
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
	
	public static void searchDepartmentLocation(DepartmentLocationEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		DepartmentLocationEnquiryVO departmentLocationEnquiryVO=null;
		UserVO _userVO=getUserVO(_rq);
		
		try{
			setSysdate(_rq);
			String selectedDepartment=_fb.getGnum_dept_code();
			departmentLocationEnquiryVO=DepartmentLocationEnquiryDATA.searchDepartmentLocationEnquiry(_fb.getGnum_dept_code(),getUserVO(_rq));
			HelperMethods.populate(_fb,departmentLocationEnquiryVO);
			_fb.setGnum_dept_code(selectedDepartment);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
			
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
