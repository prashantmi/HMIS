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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.HospitalConsultantEnquiryDATA;
import enquiry.transaction.controller.data.HospitalDepartmentEnquiryDATA;
import enquiry.transaction.controller.fb.HospitalConsultantEnquiryFB;
import enquiry.vo.HospitalConsultantEnquiryVO;
import enquiry.vo.HospitalDepartmentEnquiryVO;

public class HospitalConsultantEnquiryUTIL extends ControllerUTIL {

	public static void getEssentials(HospitalConsultantEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		try{
			UserVO objUserVO = getUserVO(_rq);
			_fb.setHospitalCode(objUserVO.getHospitalCode());
			setSysdate(_rq);
			Map essentialMap=new HashMap();
			
			List hospitalList=HospitalDepartmentEnquiryDATA.getHospitalCombo();
		    WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
		    String processType=RegistrationConfig.UNIT_CONSULTANT_DESIGNATION_MAPPING_PROCESSID;
			essentialMap=HospitalConsultantEnquiryDATA.getConsultantEnquiryEssential(processType,objUserVO);
			
			getConsultantEssentials(_fb, _rq, essentialMap);
		}
		catch(HisRecordNotFoundException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
			WebUTIL.setStatus(_rq, objStatus);
			//_fb.setIsPageList("0");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
			WebUTIL.setStatus(_rq, objStatus);
			//_fb.setIsPageList("0");
		}
	}
	
	public static void onChangeHospital(HospitalConsultantEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		try{
			UserVO objUserVO = getUserVO(_rq);
			objUserVO.setHospitalCode(_fb.getHospitalCode());
			setSysdate(_rq);
			Map essentialMap=new HashMap();
			
			List hospitalList= (List)_rq.getSession().getAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST);
			WebUTIL.refreshTransState(_rq);
			WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
		    String processType=RegistrationConfig.UNIT_CONSULTANT_DESIGNATION_MAPPING_PROCESSID;
			essentialMap=HospitalConsultantEnquiryDATA.getConsultantEnquiryEssential(processType,objUserVO);
			
			getConsultantEssentials(_fb, _rq, essentialMap);
		}
		catch(HisRecordNotFoundException e){
			e.printStackTrace();
			//objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
			objStatus.add(objStatus.CODE_UNSUCESSFULL,"No Consultant Detail Found");
			WebUTIL.setStatus(_rq, objStatus);
			//_fb.setIsPageList("0");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
			WebUTIL.setStatus(_rq, objStatus);
			//_fb.setIsPageList("0");
		}
	}
	
	public static void getConsultantEssentials(HospitalConsultantEnquiryFB _fb, HttpServletRequest _rq,Map essentialMap){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		//Map essentialMap=null;
		HospitalConsultantEnquiryVO consultantVO[]=null;
		List  <HospitalConsultantEnquiryVO> consultantVOList=null;
		//UserVO _userVO=getUserVO(_rq);
		try{
			/*setSysdate(_rq);
			String processType=RegistrationConfig.UNIT_CONSULTANT_DESIGNATION_MAPPING_PROCESSID;
			essentialMap=HospitalConsultantEnquiryDATA.getConsultantEnquiryEssential(processType,_userVO);*/
			consultantVOList=(ArrayList)essentialMap.get(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY);
			
			List <HospitalConsultantEnquiryVO> filteredConsultantList=new ArrayList<HospitalConsultantEnquiryVO>();
			Iterator itr=consultantVOList.listIterator();
			boolean flag=true;
			while (itr.hasNext()) {
				HospitalConsultantEnquiryVO consultantEnquiryVO = (HospitalConsultantEnquiryVO) itr.next();
				for(int i=0;i<filteredConsultantList.size();i++){
					if(filteredConsultantList.get(i).getEmpNo().equals(consultantEnquiryVO.getEmpNo())
							&& filteredConsultantList.get(i).getDepartmentCode().equals(consultantEnquiryVO.getDepartmentCode())){
						flag=false;
						break;
					}
					else{
						flag=true;
					}
				}
				if(flag) filteredConsultantList.add(consultantEnquiryVO);
			}
			essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW, filteredConsultantList);
			essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY, filteredConsultantList);
			WebUTIL.setMapInSession(essentialMap,_rq);
			objStatus.add(Status.LIST);
			_fb.setIsPageList("1");
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

	public static void getConsultantDetails(HospitalConsultantEnquiryFB fb,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		UserVO _userVO=getUserVO(request);
		HospitalConsultantEnquiryVO []consultantVO=null;
		Map consultantVOMap=new HashMap();
		List key=new ArrayList();
		
		try{
			
			//String empNo=(String)request.getParameter("empNo");
			_userVO.setHospitalCode(fb.getHospitalCode());
			String empNo=fb.getEmpNo();
			
			essentialMap=HospitalConsultantEnquiryDATA.getConsultantEnquiryDetailByEmpNo(empNo,_userVO);
			consultantVO=(HospitalConsultantEnquiryVO[])essentialMap.get(enquiryConfig.CONSULTANT_DETAIL_VO_ARRAY);
			for(int i=0;i<consultantVO.length;i++){
				if(!consultantVOMap.containsKey(consultantVO[i].getDepartmentCode())){
					List consultantVOList=new ArrayList();
					consultantVOList.add(consultantVO[i]);
					consultantVOMap.put(consultantVO[i].getDepartmentCode(),consultantVOList);
					key.add(consultantVO[i].getDepartmentCode());
				}
				else{
					List consultantVOList=(ArrayList)consultantVOMap.get(consultantVO[i].getDepartmentCode());
					consultantVOList.add(consultantVO[i]);
					consultantVOMap.put(consultantVO[i].getDepartmentCode(),consultantVOList);
				}
			}
			WebUTIL.setMapInSession(essentialMap,request);
			WebUTIL.setAttributeInSession(request, enquiryConfig.CONSULTANT_ENQUIRY_MAP, consultantVOMap);
			WebUTIL.setAttributeInSession(request,  enquiryConfig.CONSULTANT_ENQUIRY_MAP_KEY, key);
			fb.setDesignation(HospitalConsultantEnquiryUTIL.getDesignation(empNo,request));
			fb.setIsFinalCancelReqd("0");
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

	public static void searchConsultantDetailsByName(HospitalConsultantEnquiryFB fb, HttpServletRequest request){
		HttpSession session=request.getSession();
		HospitalConsultantEnquiryVO consultantVO=null;
		List <HospitalConsultantEnquiryVO> consultantVoOldList=null;
		List consultantVOList=new ArrayList();
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		String consultantName=fb.getConsultantName();
		try{
					
			UserVO _userVO=getUserVO(request);
			_userVO.setHospitalCode(fb.getHospitalCode());
			consultantVoOldList=(ArrayList)session.getAttribute(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY);
			
			if(!consultantName.equals("") && consultantVoOldList!=null){
				for(int i=0;i<consultantVoOldList.size();i++){
					consultantVO=new HospitalConsultantEnquiryVO();
					consultantVO=(HospitalConsultantEnquiryVO)consultantVoOldList.get(i);
					if(new HospitalConsultantEnquiryUTIL().matches(consultantVO.getConsultantName(),consultantName)){
						consultantVOList.add(consultantVO);
					}	
				}
				if(consultantVOList.size()==0 || consultantVOList==null){
					essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW, null);
					throw new HisRecordNotFoundException("No Consultant Found");
				}
				essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW, consultantVOList);
			}
			else{
				essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW, consultantVoOldList);
			}
			if(consultantVoOldList==null){
				essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW, null);
				throw new HisRecordNotFoundException("No Consultant Found");
			}
			
			/*
			String consultantName=fb.getConsultantName();
			String array[]=consultantName.split(" ");
			consultantName="";
			for(int i=0;i<array.length;i++)
				consultantName=consultantName+array[i].trim();
			consultantName="%"+consultantName.trim()+"%";
			String departmentCode=request.getParameter("departmentCode");
			if(departmentCode==null)
				departmentCode="";
			departmentCode=departmentCode+"%";
			consultantVO.setConsultantName(consultantName);
			consultantVO.setDepartmentCode(departmentCode);
			essentialMap=HospitalDepartmentEnquiryDATA.getConsultantEnquiryDetailByName(consultantVO,_userVO);*/
			WebUTIL.setMapInSession(essentialMap,request);
			objStatus.add(Status.LIST);
		}
		catch(HisRecordNotFoundException e){
			WebUTIL.setMapInSession(essentialMap,request);
			objStatus.add(Status.LIST,e.getMessage(),"");
			
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

	public static void getConsultantEnquiryUnitDetail(HospitalConsultantEnquiryFB fb, HttpServletRequest request) {
		HttpSession session=request.getSession();
		Status objStatus=new Status();
		HospitalConsultantEnquiryVO consultantVO[]=null;
		HospitalDepartmentEnquiryVO [] hospitalDeptEnquiryVO=null;
		String unitRoom="";
		String unitDays="";
		List unitWorkingDetailList=null;
		try{
			
			UserVO _userVO=getUserVO(request);
			_userVO.setHospitalCode(fb.getHospitalCode());
			//String departmentUnitCode=request.getParameter("departmentUnitCode");
			String departmentUnitCode=fb.getDepartmentUnitCode();
			
			// getConsultantEnquiryUnitDetail will return string in days#timing#room1,room2 format
			
			Map essentailMap=HospitalConsultantEnquiryDATA.getConsultantEnquiryUnitDetail(departmentUnitCode,_userVO);
			//WebUTIL.setAttributeInSession(request, enquiryConfig.UNIT_WORKING_DAYS, essentailMap);
			hospitalDeptEnquiryVO=(HospitalDepartmentEnquiryVO[])essentailMap.get(enquiryConfig.UNIT_ROOM_LIST);
			//unitDays=(String)essentailMap.get(enquiryConfig.UNIT_WORKING_DAYS);
			unitWorkingDetailList=(List)essentailMap.get(enquiryConfig.UNIT_WORKING_DAYS);
			unitWorkingDetailList=HospitalDepartmentEnquiryUTIL.setWorkingDetail(unitWorkingDetailList);
			unitWorkingDetailList=HospitalDepartmentEnquiryUTIL.setTimingDetail(unitWorkingDetailList);
			
			/*if(hospitalDeptEnquiryVO!=null)
			for(int i=0;i<hospitalDeptEnquiryVO.length;i++){
				if(hospitalDeptEnquiryVO[i].getRoom()!=null) 
					unitRoom=unitRoom+", "+hospitalDeptEnquiryVO[i].getRoom();
			}
			if(!unitRoom.equals("")) unitRoom=unitRoom.replaceFirst("," , "");
			String array[]=unitDays.split("#");
			if(array.length>1){
				fb.setWorkingDays(array[0]);
				fb.setWorkingTimings(array[1]);
			}	
			fb.setUnitWorkingRoom(unitRoom);*/
			
			consultantVO=(HospitalConsultantEnquiryVO[])session.getAttribute(enquiryConfig.CONSULTANT_DETAIL_VO_ARRAY);
			for(int i=0;i<consultantVO.length;i++){
				if(consultantVO[i].getDepartmentUnitCode().equals(departmentUnitCode)){
					fb.setDepartment(consultantVO[i].getDepartment());
					fb.setDepartmentUnit(consultantVO[i].getDepartmentUnit());
					break;
				}
			}
			
			WebUTIL.setAttributeInSession(request, enquiryConfig.UNIT_WORKING_DAYS, unitWorkingDetailList);
			fb.setDepartmentUnitCode(departmentUnitCode);
			fb.setDepartmentCode(departmentUnitCode.substring(0,3));
			objStatus.add(Status.DONE);
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
	
	public static String getDesignation(String empNo,HttpServletRequest request) {
		HttpSession session=request.getSession();
		List <HospitalConsultantEnquiryVO> consultantVOList=null;
		HospitalConsultantEnquiryVO consultantVO=null;
		HospitalConsultantEnquiryVO consultantVODetail[]=null;
		String designation=null;
		Status objStatus=new Status();
		try{
			consultantVOList=(ArrayList<HospitalConsultantEnquiryVO>)session.getAttribute(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY);
			consultantVODetail=(HospitalConsultantEnquiryVO[])session.getAttribute(enquiryConfig.CONSULTANT_DETAIL_VO_ARRAY);
			for(int i=0;i<consultantVOList.size();i++){
				consultantVO=new HospitalConsultantEnquiryVO();
				consultantVO=consultantVOList.get(i);
				if(consultantVO.getEmpNo().equals(empNo)){
					designation=consultantVO.getDesignation();
					break;
				}	
			}
			for(int i=0;i<consultantVODetail.length;i++){
				if(consultantVODetail[i].getEmpNo().equals(empNo)){
					consultantVODetail[i].setDesignation(designation);
				}	
			}
			
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		catch(Exception e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		return designation;
	}
	
	public boolean matches(String name,String matcher){
		boolean flag=false;
		name=name.toLowerCase().trim();
		matcher=matcher.toLowerCase().trim();
		if(matcher.length()==1){
			if(name.substring(0,1).equals(matcher)) return true;
			else return false;
		}
		if(name.equals(matcher)){
			return true;
		}	
		String nameArray[]=name.split(" ");
		
		for(int i=0;i<nameArray.length;i++){
			if(nameArray[i].trim().equals(matcher.trim()))
				flag=true;
		}
		nameArray=name.split(matcher);
		if(nameArray.length>1)
				flag=true;
				
		return flag;
	}
	
	
	public static void searchConsultantDetailsByDept(HospitalConsultantEnquiryFB fb, HttpServletRequest request){
		HttpSession session=request.getSession();
		HospitalConsultantEnquiryVO consultantVO=null;
		List consultantVOList=new ArrayList();
		List <HospitalConsultantEnquiryVO> consultantVoOldList=null;
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		String consultantName=fb.getConsultantName();
		try{
			
			UserVO _userVO=getUserVO(request);
			_userVO.setHospitalCode(fb.getHospitalCode());
			/*String array[]=consultantName.split(" ");
			consultantName="%";
			consultantName="";
			for(int i=0;i<array.length;i++)
				consultantName=consultantName+array[i].trim();
			consultantName="%"+consultantName.trim()+"%";
			String departmentCode=request.getParameter("departmentCode");
			if(departmentCode==null)
				departmentCode="";
			departmentCode=departmentCode+"%";
			consultantVO.setConsultantName(consultantName);
			consultantVO.setDepartmentCode(departmentCode);
			essentialMap=HospitalDepartmentEnquiryDATA.getConsultantEnquiryDetailByName(consultantVO,_userVO);*/
			
			//String departmentCode=request.getParameter("departmentCode");
			String departmentCode=fb.getDepartmentCode();
			
			consultantVoOldList=(ArrayList<HospitalConsultantEnquiryVO>)session.getAttribute(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY);
			
			for(int i=0;i<consultantVoOldList.size();i++){
				consultantVO=new HospitalConsultantEnquiryVO();
				consultantVO=consultantVoOldList.get(i);
				if(consultantVO.getDepartmentCode().equals(departmentCode)){
					consultantVOList.add(consultantVO);
				}	
			}
			
			essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW, consultantVOList);
			
			WebUTIL.setMapInSession(essentialMap,request);
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
}
