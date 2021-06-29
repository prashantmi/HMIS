package mrd.transaction.controller.utl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.transaction.controller.data.EmployeePopUpDATA;
import mrd.transaction.controller.fb.EmployeePopUpFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.StaffDetailVO;
import hisglobal.vo.UserVO;

public class EmployeePopUpUTIL extends ControllerUTIL {

	
	public static void getEssentials(EmployeePopUpFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		Map essentialMap=null;
		UserVO _userVO=getUserVO(_rq);
		String deptname = _rq.getParameter("deptname");
		_fb.setDeptname(deptname);
		System.out.println("nehscbshbsd"+ deptname);
		try{
			setSysdate(_rq);
			essentialMap=EmployeePopUpDATA.getEmployeePopUpEssentials(_userVO);
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
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
		
	}
	public static void searchEmployeeDetail(EmployeePopUpFB _fb,HttpServletRequest request) {
		Status objStatus=new Status();
		UserVO _userVO=getUserVO(request);
		StaffDetailVO staffEnquiryVO=new StaffDetailVO();
		StaffDetailVO[] staffEnquiryVOs=null;
		try{
			setSysdate(request);
			HelperMethods.populate(staffEnquiryVO, _fb);
			staffEnquiryVOs=EmployeePopUpDATA.searchStaffDetail(staffEnquiryVO,_userVO);
			StaffDetailVO[] arrayStaffEnquiryVOs=new StaffDetailVO[staffEnquiryVOs.length];
			for(int i=0;i<staffEnquiryVOs.length;i++)
			{
				arrayStaffEnquiryVOs[i]=new StaffDetailVO();
				HelperMethods.populatetToNullOrEmpty(staffEnquiryVOs[i], arrayStaffEnquiryVOs[i]);
				HelperMethods.setNullToEmpty(staffEnquiryVOs[i]);
			}
			WebUTIL.setAttributeInSession(request, MrdConfig.EMPLOYEE_ENQUIRY_VO, staffEnquiryVOs);
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
