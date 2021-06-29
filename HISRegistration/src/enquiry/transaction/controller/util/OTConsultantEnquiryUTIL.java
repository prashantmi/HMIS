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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.OTConsultantEnquiryDATA;
import enquiry.transaction.controller.fb.OTConsultantEnquiryFB;
import enquiry.vo.HospitalConsultantEnquiryVO;

public class OTConsultantEnquiryUTIL extends ControllerUTIL {

	public static void getEssentials(OTConsultantEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		HospitalConsultantEnquiryVO consultantVO=new HospitalConsultantEnquiryVO();
		HospitalConsultantEnquiryVO consultantVO2=new HospitalConsultantEnquiryVO();
		UserVO _userVO=getUserVO(_rq);
		try{
			//setSysdate(_rq);
			Map essentialMap=OTConsultantEnquiryDATA.getOTConsultantEnquiryEssential(_userVO);
			List otConsultantList=(ArrayList)essentialMap.get(enquiryConfig.OT_CONSULTANT_LIST);
			List workingDays=(ArrayList)essentialMap.get(enquiryConfig.OT_CONSULTANT_WORKING_DAYS);
			for(int i=0;i<workingDays.size();i++){
				consultantVO=(HospitalConsultantEnquiryVO)workingDays.get(i);
				for(int j=0;j<otConsultantList.size();j++){
					consultantVO2=(HospitalConsultantEnquiryVO)otConsultantList.get(j);
					if(consultantVO2.getEmpNo().equals(consultantVO.getEmpNo())){
						consultantVO2.setDay((consultantVO2.getDay()==null?" ":consultantVO2.getDay())+","+consultantVO.getDay());
					}
					otConsultantList.set(j, consultantVO2);
				}
			}
			
			for(int j=0;j<otConsultantList.size();j++){
				consultantVO2=(HospitalConsultantEnquiryVO)otConsultantList.get(j);
				consultantVO2.setDay(consultantVO2.getDay().replaceFirst(",", " "));
				otConsultantList.set(j, consultantVO2);
			}
			
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.OT_CONSULTANT_LIST_VIEW, otConsultantList);
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.OT_CONSULTANT_LIST, otConsultantList);
			objStatus.add(Status.LIST);
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

	public static void getConsultantDetail(OTConsultantEnquiryFB fb,HttpServletRequest request) {
		HttpSession session= request.getSession();
		Status objStatus=new Status();
		HospitalConsultantEnquiryVO consultantVO=null;
		List  consultantVOList=null;
		UserVO _userVO=getUserVO(request);
		try{
			String empNo=fb.getEmpNo();
			String days="";
			consultantVOList=OTConsultantEnquiryDATA.getOTConsultantDetail(empNo,_userVO);
			//WebUTIL.setAttributeInSession(request, enquiryConfig.OT_CONSULTANT_LIST, otConsultantList);
			fb.setConsultantName(((HospitalConsultantEnquiryVO)consultantVOList.get(0)).getConsultantName());
			for(int i=0;i<consultantVOList.size();i++){
				consultantVO=new HospitalConsultantEnquiryVO();
				consultantVO=(HospitalConsultantEnquiryVO)consultantVOList.get(i);
				days=days + consultantVO.getDay()+ ",";
			}
			days=days.substring(0, days.length()-1);
			fb.setDay(days);
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
			 WebUTIL.setStatus(request,objStatus);
			 System.out.println("objStatus in finally"+objStatus);		 
			 System.out.println("objStatus list"+objStatus.getStatusList());
		}	
		
	}
	
	public static void getConsultantByName(OTConsultantEnquiryFB fb,HttpServletRequest request) {
		HttpSession session= request.getSession();
		Status objStatus=new Status();
		HospitalConsultantEnquiryVO consultantVO=null;
		List  consultantVOList=null;
		List  consultantList=new ArrayList();
		//UserVO _userVO=getUserVO(request);
		try{
			String consultantName=fb.getConsultantName();
			consultantVOList=(List)session.getAttribute(enquiryConfig.OT_CONSULTANT_LIST);
			System.out.println("consultantVOList size: "+consultantVOList.size());
			for(int i=0;i<consultantVOList.size();i++){
				consultantVO=new HospitalConsultantEnquiryVO();
				consultantVO=(HospitalConsultantEnquiryVO)consultantVOList.get(i);
				System.out.println("getConsultantByName consultant name:"+consultantVO.getConsultantName());
				if(consultantVO.getConsultantName()!=null)
				{
				if(matches(consultantVO.getConsultantName(), consultantName))
						consultantList.add(consultantVO);
				}
				}
			
			WebUTIL.setAttributeInSession(request, enquiryConfig.OT_CONSULTANT_LIST_VIEW, consultantList);
			if(consultantList.size()<1){
				objStatus.add(Status.LIST,"","No Consultant Found");
			}
			else
				objStatus.add(Status.LIST);
			
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
			WebUTIL.setStatus(request,objStatus);
			System.out.println("objStatus in finally"+objStatus);		 
			System.out.println("objStatus list"+objStatus.getStatusList());
		}	
		
	}
	
	public static boolean matches(String name,String matcher){
		boolean flag=false;
		name=name.toLowerCase().trim();
		matcher=matcher.toLowerCase().trim();
		if(matcher.length()==1){
			if(name.substring(0,1).equals(matcher)) return true;
			else return false;
		}
			
		String nameArray[]=name.split(" ");
		
		for(int i=0;i<nameArray.length;i++){
			if(nameArray[i].trim().equals(matcher.trim()))
				flag=true;
		}
		nameArray=name.split(matcher);
		if(nameArray.length>1 || nameArray.length==0)
				flag=true;
				
		return flag;
	}
	
}
