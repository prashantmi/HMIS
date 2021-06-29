package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.HospitalTelephoneEnquiryDATA;
import enquiry.transaction.controller.fb.HospitalTelephoneEnquiryFB;
import enquiry.vo.HospitalTelephoneEnquiryVO;


public class HospitalTelephoneEnquiryUTL extends ControllerUTIL{
	
	public static void getEssentials(HospitalTelephoneEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		Map essentialMap=null;
		try{
			setSysdate(_rq);
			UserVO objUserVO = getUserVO(_rq);
			List hospitalList=HospitalTelephoneEnquiryDATA.getHospitalCombo();
		    WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
			essentialMap=HospitalTelephoneEnquiryDATA.getTelephoneEnquiryEssentials(objUserVO);
			//List departmentTelephoneList=(ArrayList)essentialMap.get(enquiryConfig.TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO);
			List employeeTelephoneList=(ArrayList)essentialMap.get(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW);
			
			
			//essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO_VIEW, departmentTelephoneList);
			essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW, employeeTelephoneList);
			essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO, employeeTelephoneList);
			
			WebUTIL.setMapInSession(essentialMap,_rq);
			_fb.setHospitalCode(objUserVO.getHospitalCode());
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
	
	public static void getTelephoneEssentialsOnHospitalChange(HospitalTelephoneEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		Map essentialMap=null;
		try{
			setSysdate(_rq);
			UserVO _userVO=getUserVO(_rq);
			_userVO.setHospitalCode(_fb.getHospitalCode());
			
			List hospitalList= (List)_rq.getSession().getAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST);
			WebUTIL.refreshTransState(_rq);
			WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			
			essentialMap=HospitalTelephoneEnquiryDATA.getTelephoneEnquiryEssentials(_userVO);
			//List departmentTelephoneList=(ArrayList)essentialMap.get(enquiryConfig.TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO);
			List employeeTelephoneList=(ArrayList)essentialMap.get(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW);
			if(employeeTelephoneList.size()<1){
		    	objStatus.add(objStatus.ERROR_AE,"No Detail Found","");
		    	WebUTIL.setStatus(_rq, objStatus);
		    }
			
			//essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO_VIEW, departmentTelephoneList);
			essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW, employeeTelephoneList);
			essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO, employeeTelephoneList);
			
			WebUTIL.setMapInSession(essentialMap,_rq);
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

	public static void getTelePhoneDetailByEmp(HospitalTelephoneEnquiryFB fb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		HospitalTelephoneEnquiryVO telephoneEnquiryVO=new HospitalTelephoneEnquiryVO();
		try{
			//setSysdate(request);
			String employeeName=fb.getEmployeeName();
			List employeeTelephoneList=(ArrayList)session.getAttribute(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO);
			List employeeTelephoneNewList=new ArrayList();;
			if(employeeTelephoneList!=null)			
			for(int i=0;i<employeeTelephoneList.size();i++){
				telephoneEnquiryVO=(HospitalTelephoneEnquiryVO)employeeTelephoneList.get(i);
				if(isMatches(telephoneEnquiryVO.getEmployeeName(),employeeName))
					employeeTelephoneNewList.add(telephoneEnquiryVO);
			}
			if(employeeTelephoneNewList.size()==0){
				WebUTIL.setAttributeInSession(request, enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW, null);
				throw new HisRecordNotFoundException("No Record Found");
			}
			WebUTIL.setAttributeInSession(request, enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW, employeeTelephoneNewList);
			fb.setIsFinalCancelReqd("0");
			objStatus.add(Status.TRANSINPROCESS);
		}
			
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.TRANSINPROCESS,e.getMessage(),"");
		
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
	
	/*public static void getTelePhoneDetailByDeptName(HospitalTelephoneEnquiryFB fb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		HospitalTelephoneEnquiryVO telephoneEnquiryVO=new HospitalTelephoneEnquiryVO();
		try{
			//setSysdate(request);
			String department=fb.getEmployeeName();
			List departmentTelephoneList=(ArrayList)session.getAttribute(enquiryConfig.TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO);
			List departmentTelephoneNewList=new ArrayList();;
			if(departmentTelephoneList!=null && departmentTelephoneList.size()>0)
			for(int i=0;i<departmentTelephoneList.size();i++){
				telephoneEnquiryVO=(HospitalTelephoneEnquiryVO)departmentTelephoneList.get(i);
				if(isMatches(telephoneEnquiryVO.getDepartment(),department))
					departmentTelephoneNewList.add(telephoneEnquiryVO);
			}
			
			if(departmentTelephoneNewList.size()==0){
				WebUTIL.setAttributeInSession(request, enquiryConfig.TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO_VIEW, null);
				throw new HisRecordNotFoundException("No Record Found");
			}
			WebUTIL.setAttributeInSession(request, enquiryConfig.TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO_VIEW, departmentTelephoneNewList);
			objStatus.add(Status.DONE);
		}
		
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.DONE,e.getMessage(),"");
			
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
		
	}*/
	
	public static boolean isMatches(String name,String matcher){
		boolean flag=false;
		if(name!=null){
			name=name.trim().toLowerCase();
			matcher=matcher.trim().toLowerCase();
			if(name.equals(matcher)){
				return true;
			}
			if(matcher.length()==1){
				if(name.startsWith(matcher)) return true;
				else return false;
			}
			String nameArray[]=name.split(" ");
			
			for(int i=0;i<nameArray.length;i++){
				if(nameArray[i].trim().equals(matcher.trim()))
					flag=true;
			}
			nameArray=name.split(matcher);
			if(nameArray.length>1)
					flag=true;
			
		}
		return flag;
	}
		
}

	


