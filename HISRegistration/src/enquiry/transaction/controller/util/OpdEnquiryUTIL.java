package enquiry.transaction.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import registration.RegistrationConfig;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.OpdEnquiryDATA;
import enquiry.transaction.controller.fb.OpdEnquiryFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.OpdEnquiryVO;
import hisglobal.vo.UserVO;

public class OpdEnquiryUTIL extends ControllerUTIL {
	
	
	public static void getEssentials(OpdEnquiryFB _fb,HttpServletRequest  _rq){
		
		Status objStatus=new Status();
		UserVO _userVO=null;
		List departmentList=null;
		List unitList=new ArrayList();
		try{
			setSysdate(_rq);
			_userVO=getUserVO(_rq);
			
			List hospitalList=OpdEnquiryDATA.getHospitalCombo();
		    WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
			departmentList=OpdEnquiryDATA.getEssentials(_userVO);
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT, departmentList);
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT, unitList);
			_fb.setHospitalCode(_userVO.getHospitalCode());
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
	
	public static void getDepartmentEssentials(OpdEnquiryFB _fb,HttpServletRequest  _rq){
		
		Status objStatus=new Status();
		UserVO _userVO=null;
		List departmentList=null;
		List unitList=new ArrayList();
		try{
			//setSysdate(_rq);
			_userVO=getUserVO(_rq);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
			
			List hospitalList= (List)_rq.getSession().getAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST);
			WebUTIL.refreshTransState(_rq);
			WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			
			departmentList=OpdEnquiryDATA.getEssentials(_userVO);
			
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT, departmentList);
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT, unitList);
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
			
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT, null);
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT, null);
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		
	}
	
	public static void getDepartmentUnit(OpdEnquiryFB _fb,HttpServletRequest  _rq){
		
		
		Status objStatus=new Status();
		UserVO _userVO=null;
		List unitList=null;
		try{
			setSysdate(_rq);
			_userVO=getUserVO(_rq);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
			if(_fb.getGnum_dept_code().equals("-1"))
			{
				unitList=new ArrayList();
			}
			else
			{
			unitList=OpdEnquiryDATA.getDepartmentUnit(_fb.getGnum_dept_code(),_userVO);
			}
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT, unitList);
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
	
	public static void getSearchDetail(OpdEnquiryFB _fb,HttpServletRequest  _rq){
		Status objStatus=new Status();
		OpdEnquiryVO opdEnquiryVO=new OpdEnquiryVO();
		OpdEnquiryVO[] opdEnquieryVOs=null;
		try{
			UserVO _userVO = getUserVO(_rq);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
			HelperMethods.populate(opdEnquiryVO, _fb);
			opdEnquieryVOs=OpdEnquiryDATA.getSearchDetail(opdEnquiryVO,_userVO);
			WebUTIL.setAttributeInSession(_rq,enquiryConfig.OPD_ENQUIERY_VO_ARRAY, opdEnquieryVOs);
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
		}
	}
	
	public static void getAllConsulatantDetailsForUnit(OpdEnquiryFB _fb,HttpServletRequest  _rq){
		Status objStatus=new Status();
		
		OpdEnquiryVO[] opdEnquieryVOs=null;
		try{
			UserVO _userVO = getUserVO(_rq);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
			opdEnquieryVOs=OpdEnquiryDATA.getAllConsulatantDetailsForUnit(_fb.getHgnum_deptunitcode(),_userVO);
			WebUTIL.setAttributeInSession(_rq,enquiryConfig.ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO, opdEnquieryVOs);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			WebUTIL.setAttributeInSession(_rq,enquiryConfig.ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO, null);
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
		}
	}
	
	

}
