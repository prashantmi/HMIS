package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.OpdEnquiryVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.OpdEnquiryDATA;
import enquiry.transaction.controller.data.OpdScheduleEnquiryDATA;
import enquiry.transaction.controller.fb.OpdScheduleEnquiryFB;
import enquiry.vo.OpdScheduleEnquiryVO;

public class OpdScheduleEnquiryUTL extends ControllerUTIL{
	
	public static void getEssentials(OpdScheduleEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		List departmentVOList=null;
		List specialCliniclist=new ArrayList();
		UserVO _userVO=getUserVO(_rq);
		OpdScheduleEnquiryVO opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
		try{
			setSysdate(_rq);
			essentialMap=OpdScheduleEnquiryDATA.getOpdScheduleEnquiryEssentials(_userVO);
			departmentVOList=(ArrayList)essentialMap.get(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO);
			specialCliniclist=(ArrayList)essentialMap.get(enquiryConfig.OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO);
			List deptList=createDeptList(departmentVOList,specialCliniclist);
			//setting the DeptUnitIsonduty flag 
			
			for(int i=0;i<specialCliniclist.size();i++){
				opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)specialCliniclist.get(i);
				if(isDeptUnitOnDuty(opdScheduleEnquiryVO)){
					opdScheduleEnquiryVO.setDeptUnitIsonduty(enquiryConfig.IS_DEPT_UNIT_ON_DUTY_YES);
				}
				else{
					opdScheduleEnquiryVO.setDeptUnitIsonduty(enquiryConfig.IS_DEPT_UNIT_ON_DUTY_NO);
				}	
				specialCliniclist.set(i, opdScheduleEnquiryVO);
			}
			
			// making both list of equal size
			makeEqualSizeList(departmentVOList,specialCliniclist);
			/*Runtime rt=Runtime.getRuntime();
			System.out.println("free memory="+rt.freeMemory());
			*/
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO, departmentVOList);
			//System.out.println("free memory1="+rt.freeMemory());
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO, specialCliniclist);
			//System.out.println("free memory2="+rt.freeMemory());
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO_VIEW, departmentVOList);
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO_VIEW, specialCliniclist);
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_LIST, deptList);
			//System.out.println("free memory3="+rt.freeMemory());
			WebUTIL.setMapInSession(essentialMap,_rq);
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
		finally{
			WebUTIL.setStatus(_rq, objStatus);
			
		}
		//System.out.println("after all execution");
	}

	public static void getUnitWorkingDetail(OpdScheduleEnquiryFB fb,HttpServletRequest request) {
		HttpSession session= request.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		List opdScheduleEnqVOList=null;
		List roomList=new ArrayList();
		Map roomMap=new HashMap();
		UserVO _userVO=getUserVO(request);
		OpdScheduleEnquiryVO opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
		try{
			String departmentCode=fb.getDepartmentCode();
			//String departmentCode=request.getParameter("departmentCode");
			essentialMap=OpdScheduleEnquiryDATA.getUnitWorkingDetail(departmentCode,_userVO);
			opdScheduleEnqVOList=(ArrayList)essentialMap.get(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DETAIL_VO);
			Map unitWorkingDetailMap=(Map)essentialMap.get(enquiryConfig.OPD_SCHEDULE_ENQUIRY_UNIT_WORKING_DETAIL);
			Set keyset=unitWorkingDetailMap.keySet();
			Iterator itr=keyset.iterator();
			List unitWorkingDetailList=null;;
			while (itr.hasNext()) {
				String key=itr.next().toString();
				unitWorkingDetailList=(List)unitWorkingDetailMap.get(key);
				unitWorkingDetailList=HospitalDepartmentEnquiryUTIL.setWorkingDetail(unitWorkingDetailList);
				unitWorkingDetailList=HospitalDepartmentEnquiryUTIL.setTimingDetail(unitWorkingDetailList);
				unitWorkingDetailMap.put(key, unitWorkingDetailList);
			}
			
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_UNIT_WORKING_DETAIL,unitWorkingDetailMap);
			//roomList=(ArrayList)essentialMap.get(enquiryConfig.UNIT_ROOM_LIST);
			/*for(int i=0;i<roomList.size();i++){
				Entry entry=(Entry)roomList.get(i);
				if(roomMap.containsKey(entry.getValue())){
					String room=roomMap.get(entry.getValue())+","+entry.getLabel();
					roomMap.put(entry.getValue(), room);
				}
				else{
					String room=entry.getLabel();
					roomMap.put(entry.getValue(), room);
				}
			}
			for(int i=0;i<opdScheduleEnqVOList.size();i++){
				opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)opdScheduleEnqVOList.get(i);
				if(roomMap.get(opdScheduleEnquiryVO.getDepartmentUnitCode())!=null)
						opdScheduleEnquiryVO.setRoom(roomMap.get(opdScheduleEnquiryVO.getDepartmentUnitCode()).toString());
				opdScheduleEnqVOList.set(i, opdScheduleEnquiryVO);
			}*/
			
			//setting the DeptUnitIsonduty flag 
			
			for(int i=0;i<opdScheduleEnqVOList.size();i++){
				opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)opdScheduleEnqVOList.get(i);
				if(isDeptUnitOnDuty(opdScheduleEnquiryVO)){
					opdScheduleEnquiryVO.setDeptUnitIsonduty(enquiryConfig.IS_DEPT_UNIT_ON_DUTY_YES);
				}
				else{
					opdScheduleEnquiryVO.setDeptUnitIsonduty(enquiryConfig.IS_DEPT_UNIT_ON_DUTY_NO);
				}	
				opdScheduleEnqVOList.set(i, opdScheduleEnquiryVO);
			}
			fb.setHod(((OpdScheduleEnquiryVO)opdScheduleEnqVOList.get(0)).getHod());
			fb.setDepartment(((OpdScheduleEnquiryVO)opdScheduleEnqVOList.get(0)).getDepartment());
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DETAIL_VO, opdScheduleEnqVOList);				
			WebUTIL.setMapInSession(essentialMap,request);
			objStatus.add(Status.DONE);
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
	
	public static void getSpecialClinicWorkingDetail(OpdScheduleEnquiryFB fb,HttpServletRequest request) {
		HttpSession session= request.getSession();
		Status objStatus=new Status();
		Map essentialMap=null;
		List opdScheduleEnqVOList=null;
		List roomList=new ArrayList();
		Map roomMap=new HashMap();
		UserVO _userVO=getUserVO(request);
		OpdScheduleEnquiryVO opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
		try{
			String departmentUnitCode=fb.getDepartmentUnitCode();
			//String departmentCode=fb.getParameter("departmentCode");
			essentialMap=OpdScheduleEnquiryDATA.getSpecialClinicWorkingDetail(departmentUnitCode,_userVO);
			opdScheduleEnqVOList=(ArrayList)essentialMap.get(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DETAIL_VO);
			fb.setDepartment(((OpdScheduleEnquiryVO)opdScheduleEnqVOList.get(0)).getDepartment());
			Map unitWorkingDetailMap=(Map)essentialMap.get(enquiryConfig.OPD_SCHEDULE_ENQUIRY_UNIT_WORKING_DETAIL);
			Set keyset=unitWorkingDetailMap.keySet();
			Iterator itr=keyset.iterator();
			List unitWorkingDetailList=null;;
			while (itr.hasNext()) {
				String key=itr.next().toString();
				unitWorkingDetailList=(List)unitWorkingDetailMap.get(key);
				unitWorkingDetailList=HospitalDepartmentEnquiryUTIL.setWorkingDetail(unitWorkingDetailList);
				unitWorkingDetailList=HospitalDepartmentEnquiryUTIL.setTimingDetail(unitWorkingDetailList);
				unitWorkingDetailMap.put(key, unitWorkingDetailList);
			}
			
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_UNIT_WORKING_DETAIL,unitWorkingDetailMap);
			
			/*roomList=(ArrayList)essentialMap.get(enquiryConfig.UNIT_ROOM_LIST);
			for(int i=0;i<roomList.size();i++){
				Entry entry=(Entry)roomList.get(i);
				if(roomMap.containsKey(entry.getValue())){
					String room=roomMap.get(entry.getValue())+","+entry.getLabel();
					roomMap.put(entry.getValue(), room);
				}
				else{
					String room=entry.getLabel();
					roomMap.put(entry.getValue(), room);
				}
			}
			for(int i=0;i<opdScheduleEnqVOList.size();i++){
				opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)opdScheduleEnqVOList.get(i);
				if(roomMap.get(opdScheduleEnquiryVO.getDepartmentUnitCode())!=null)
					opdScheduleEnquiryVO.setRoom(roomMap.get(opdScheduleEnquiryVO.getDepartmentUnitCode()).toString());
				opdScheduleEnqVOList.set(i, opdScheduleEnquiryVO);
			}
			*/
			//setting the DeptUnitIsonduty flag 
			
			for(int i=0;i<opdScheduleEnqVOList.size();i++){
				opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)opdScheduleEnqVOList.get(i);
				if(isDeptUnitOnDuty(opdScheduleEnquiryVO)){
					opdScheduleEnquiryVO.setDeptUnitIsonduty(enquiryConfig.IS_DEPT_UNIT_ON_DUTY_YES);
				}
				else{
					opdScheduleEnquiryVO.setDeptUnitIsonduty(enquiryConfig.IS_DEPT_UNIT_ON_DUTY_NO);
				}	
				opdScheduleEnqVOList.set(i, opdScheduleEnquiryVO);
			}
			fb.setHod(((OpdScheduleEnquiryVO)opdScheduleEnqVOList.get(0)).getHod());
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DETAIL_VO, opdScheduleEnqVOList);				
			WebUTIL.setMapInSession(essentialMap,request);
			objStatus.add(Status.DONE);
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
	
	/**
	 * Checks whether the unit is working today or not
	 * @param opdScheduleEnquiryVO
	 * @return true if unit is working today otherwise false
	 */
	public static boolean isDeptUnitOnDuty(OpdScheduleEnquiryVO opdScheduleEnquiryVO){
		boolean flag=false;
		String today=opdScheduleEnquiryVO.getToday();
		if(!opdScheduleEnquiryVO.getUnitDays().equals("#")){
			String []days=opdScheduleEnquiryVO.getUnitDays().split("#")[0].split(",");
			for(int i=0;i<days.length;i++){
				if(days[i].trim().equals(today.trim())){
					flag=true;
					break;
				}	
			}
		}	
		return flag;
	}

	
	public static void getConsultant(OpdScheduleEnquiryFB fb,HttpServletRequest request) {
		//HttpSession session= request.getSession();
		Status objStatus=new Status();
		try{
			String departmentUnitCode=fb.getDepartmentUnitCode();
			OpdEnquiryVO[] opdEnquieryVOs=null;
			opdEnquieryVOs=OpdEnquiryDATA.getAllConsulatantDetailsForUnit(departmentUnitCode,getUserVO(request));
			if(opdEnquieryVOs!=null){
				for(int i=0;i<opdEnquieryVOs.length;i++){
					if(opdEnquieryVOs[i].getHgnum_is_hou().equals("1"))
						fb.setHou(opdEnquieryVOs[i].getEmployeeName());
						break;
				}
			}	
			WebUTIL.setAttributeInSession(request,enquiryConfig.ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO, opdEnquieryVOs);
			
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			WebUTIL.setAttributeInSession(request,enquiryConfig.ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO, null);
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
	
	public static List createDeptList(List opdScheduleVoList ,List specialCliniclist){
		List deptList=new ArrayList();
		OpdScheduleEnquiryVO opdSchedule=new OpdScheduleEnquiryVO();
		for(int i=0;i<opdScheduleVoList.size();i++){
			Entry entry=new Entry();
			opdSchedule=(OpdScheduleEnquiryVO)opdScheduleVoList.get(i);
			entry.setLabel(opdSchedule.getDepartment());
			entry.setValue(opdSchedule.getDepartmentCode());
			deptList.add(entry);
		}
		for(int i=0;i<specialCliniclist.size();i++){
			Entry entry=new Entry();
			opdSchedule=(OpdScheduleEnquiryVO)specialCliniclist.get(i);
			entry.setLabel(opdSchedule.getDepartmentUnit());
			entry.setValue(opdSchedule.getDepartmentUnitCode());
			deptList.add(entry);
		}
		return deptList;
	}
	

	public static void getDeptByName(OpdScheduleEnquiryFB fb,HttpServletRequest request) {
		HttpSession session= request.getSession();
		Status objStatus=new Status();
		List deptVOList=null;
		List specialClinicVOList=null;
		List deptList=new ArrayList();
		List specialClinicList=new ArrayList();
		UserVO _userVO=getUserVO(request);
		OpdScheduleEnquiryVO opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
		try{
			String searchText=fb.getSearchText();
			//String departmentCode=request.getParameter("departmentCode");
			deptVOList=(ArrayList)session.getAttribute(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO);
			specialClinicVOList=(ArrayList)session.getAttribute(enquiryConfig.OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO);
			for(int i=0;i<deptVOList.size();i++){
				opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)deptVOList.get(i);
				if(isMatches(opdScheduleEnquiryVO.getDepartment(),searchText)){
					deptList.add(opdScheduleEnquiryVO);
				}
			}
			if(deptList.size()==1){
				fb.setDepartmentCode(((OpdScheduleEnquiryVO)deptList.get(0)).getDepartmentCode());
				getUnitWorkingDetail(fb,request);
				return;
			}
			else{
				for(int i=0;i<specialClinicVOList.size();i++){
					opdScheduleEnquiryVO=(OpdScheduleEnquiryVO)specialClinicVOList.get(i);
					if(isMatches(opdScheduleEnquiryVO.getDepartmentUnit(),searchText)){
						specialClinicList.add(opdScheduleEnquiryVO);
					}
				}
			}	
			if(specialClinicList.size()==1){
				fb.setDepartmentUnitCode(((OpdScheduleEnquiryVO)specialClinicList.get(0)).getDepartmentUnitCode());
				getSpecialClinicWorkingDetail(fb,request);
				return;
			}
			else{
				makeEqualSizeList(deptList, specialClinicList);
				WebUTIL.setAttributeInSession(request, enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO_VIEW, deptList);
				WebUTIL.setAttributeInSession(request, enquiryConfig.OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO_VIEW, specialClinicList);
				
			}
			if(deptList.size()==0 && specialClinicList.size()==0){
				objStatus.add(Status.UNSUCESSFULL,"","No Record Found");
			}
			else{
				objStatus.add(Status.LIST);
			}
			
			
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
	
	public static boolean isMatches(String name,String matcher){
		boolean matches=false;
		matcher=matcher.toLowerCase().trim();
		name=name.toLowerCase();
		if(matcher.length()==1){
			if(name.startsWith(matcher)) return true;
			else return false;
		}
		
		String nameArray[]=name.split(" ");
		for(int i=0;i<nameArray.length;i++){
			if(nameArray[i].trim().equals(matcher))
				return true;
		}
		nameArray=name.split(matcher);
		if(nameArray.length>1 || nameArray.length==0) matches=true;
		
		return matches;
	}
	public static void makeEqualSizeList(List departmentVOList,List specialCliniclist){
		OpdScheduleEnquiryVO opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
		if(departmentVOList.size()>specialCliniclist.size()){
			for(int i=specialCliniclist.size();i<departmentVOList.size();i++){
				opdScheduleEnquiryVO.setDeptUnitIsonduty("0");
				opdScheduleEnquiryVO.setDepartmentUnit("");
				specialCliniclist.add(opdScheduleEnquiryVO);
			}	
		}
		else{
			for(int i=departmentVOList.size();i<specialCliniclist.size();i++){
				opdScheduleEnquiryVO.setDeptUnitIsonduty("0");
				opdScheduleEnquiryVO.setDepartment("");
				departmentVOList.add(opdScheduleEnquiryVO);
			}	
		}
	}
}
