package enquiry.transaction.controller.util;

import ipd.IpdDATA;
import ipd.IpdFB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.HospitalDepartmentEnquiryDATA;
import enquiry.transaction.controller.fb.HospitalDepartmentEnquiryFB;
import enquiry.vo.HospitalDepartmentEnquiryVO;
import enquiry.vo.OpdScheduleEnquiryVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.UserVO;

public class HospitalDepartmentEnquiryUTIL extends ControllerUTIL {
	
	public static void getEssentials(HospitalDepartmentEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		try{
		//setSysdate(_rq);
		Map essentialMap=new HashMap();
		
		essentialMap=HospitalDepartmentEnquiryDATA.getDepartmentEnquiryEssential(getUserVO(_rq));
		List departmentList=(List)essentialMap.get(enquiryConfig.ALL_DEPARTMENT_WITH_DEPARTMENT_TYPE);
		List allDeptList=createDeptList(departmentList);
		Iterator itr=departmentList.iterator();
		Map allDepartmentMap=new LinkedHashMap();
		while(itr.hasNext())
		{
			Entry entryObj=(Entry)itr.next();
			String value=entryObj.getValue();
			String label=entryObj.getLabel();
			if(!allDepartmentMap.containsKey(value))///if map does not contain key
			{
				allDepartmentMap.put(value,new ArrayList());
			((ArrayList)allDepartmentMap.get(value)).add(label);
			}
			else
			{
				
				((ArrayList)allDepartmentMap.get(value)).add(label);
			}
			//System.out.println("value"+value);
			//System.out.println("label"+label);
			
		}
		
		Set mapKeySet= allDepartmentMap.keySet();
		Iterator keyItr=mapKeySet.iterator();
		int maxRows=0; 
		
		/////finding maximum list size
		while(keyItr.hasNext())
		{
			String departmentTypeKey=(String)keyItr.next();
			List departmentTypeList=(ArrayList)allDepartmentMap.get(departmentTypeKey);
			int numberOfRow=departmentTypeList.size();
			if(maxRows<numberOfRow)
				maxRows=numberOfRow;
			
			
		}
		//System.out.println("max number of rows"+maxRows);
		keyItr=mapKeySet.iterator();
		////////This makes all the list of equal size
		while(keyItr.hasNext())
		{
			String departmentTypeKey=(String)keyItr.next();
			List departmentTypeList=(ArrayList)allDepartmentMap.get(departmentTypeKey);
			int numberOfRow=departmentTypeList.size();
			//System.out.println("earlier list sixw"+departmentTypeList.size());
			if(maxRows>numberOfRow)
				for(int i=0;i<maxRows-numberOfRow;i++)
				{
					departmentTypeList.add("");
				}
			//System.out.println("final list sixw"+departmentTypeList.size());
		}
		
		//_fb.setMaximumRow(String.valueOf(maxRows));
		WebUTIL.setMapInSession(essentialMap,_rq);
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.DEPARTMENT_ANDDEPARTMENT_TYPE_MAP, allDepartmentMap);
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.DEPARTMENT_ANDDEPARTMENT_TYPE_MAP_VIEW, allDepartmentMap);
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.ALL_DEPT_LIST, allDeptList);
		//System.out.println("all department type"+allDepartmentMap.size());
		objStatus.add(Status.LIST);
		}catch(HisRecordNotFoundException e){
			
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	public static void getDepartmentDetail(HospitalDepartmentEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		HospitalDepartmentEnquiryVO[] enquiryVO;
		String departmentType=_fb.getDepartmentTypeCode();
		try{
		
		enquiryVO=HospitalDepartmentEnquiryDATA.getDepartmentEnquiryDetail(_fb.getDepartmentCode(),_fb.getDepartmentTypeCode(),getUserVO(_rq));
		
		HelperMethods.populate(_fb,enquiryVO[0]);
		_fb.setDepartmentTypeCode(departmentType);
		if(_fb.getLocationRoom()==null)
			_fb.setLocationRoom("");
		if(departmentType.equals(RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE)){
			List deptList=new ArrayList();
			for(int i=0;i<enquiryVO.length;i++){
				deptList.add(enquiryVO[i]);
			}
			createUnitTypeList(deptList,_rq);
		}
		else{
		WebUTIL.setAttributeInSession(_rq,enquiryConfig.ARRAY_DEPARTMENT_ENQUIRY_VO, enquiryVO);
		
		}
		objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e){
			objStatus.add(Status.LIST);
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	public static void getUnitDetail(HospitalDepartmentEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		HospitalDepartmentEnquiryVO[] enquiryVO; 
		Map essentialMap=new HashMap();
		String unitWorkingDays="";
		String[] temp;
		HospitalDepartmentEnquiryVO departmentUnitEnquiry;
		HospitalDepartmentEnquiryVO[] departmentUnitConsultantEnquiry;
		HospitalDepartmentEnquiryVO[] departmentUnitRoomEnquiry;
		HospitalDepartmentEnquiryVO[] departmentUnitWardEnquiry;
		//List departmentUnitConsultantList1=new ArrayList();
		//List departmentUnitConsultantList2=new ArrayList();
		List unitWorkingDetailList=null;
		try{
		
			essentialMap=HospitalDepartmentEnquiryDATA.getDepartmentUnitEnquiryDetail(_fb.getDepartmentUnitCode(),getUserVO(_rq));
		//HelperMethods.populate(_fb,enquiryVO[0]);
		//WebUTIL.setAttributeInSession(_rq,enquiryConfig.ARRAY_UNIT_DETAIL_DEPARTMENT_ENQUIRY_VO, enquiryVO);
			///////////setting msg for unavailable data//////
			departmentUnitEnquiry=(HospitalDepartmentEnquiryVO)essentialMap.get(enquiryConfig.DEPARTMENT_UNIT_ENQUIRY_DETAIL_VO);
			departmentUnitConsultantEnquiry=(HospitalDepartmentEnquiryVO[])essentialMap.get(enquiryConfig.DEPARTMENT_UNIT_CONSULTANT_ENQUIRY_DETAIL_VO);
			//departmentUnitRoomEnquiry=(HospitalDepartmentEnquiryVO[])essentialMap.get(enquiryConfig.DEPARTMENT_UNIT_ROOM_ENQUIRY_DETAIL_VO);
			departmentUnitWardEnquiry=(HospitalDepartmentEnquiryVO[])essentialMap.get(enquiryConfig.DEPARTMENT_UNIT_WARD_ENQUIRY_DETAIL_VO);
			unitWorkingDetailList=(List)essentialMap.get(enquiryConfig.DEPARTMENT_UNIT_WORKING_DAYS);
			
			unitWorkingDetailList=setWorkingDetail(unitWorkingDetailList);
			unitWorkingDetailList=setTimingDetail(unitWorkingDetailList);
			essentialMap.put(enquiryConfig.DEPARTMENT_UNIT_WORKING_DAYS, unitWorkingDetailList);
			if(departmentUnitEnquiry==null )
				departmentUnitEnquiry=new HospitalDepartmentEnquiryVO();
			if(departmentUnitConsultantEnquiry==null || departmentUnitConsultantEnquiry.length==0)
				departmentUnitConsultantEnquiry=new HospitalDepartmentEnquiryVO[1];
			/*if(departmentUnitRoomEnquiry==null || departmentUnitRoomEnquiry.length==0){
				departmentUnitRoomEnquiry=new HospitalDepartmentEnquiryVO[1];
				departmentUnitRoomEnquiry[0]=new HospitalDepartmentEnquiryVO();
			}	*/
			if(departmentUnitWardEnquiry==null || departmentUnitWardEnquiry.length==0)
				departmentUnitWardEnquiry=new HospitalDepartmentEnquiryVO[1];
			
			/*if(unitWorkingDays==null || unitWorkingDays.equals("#"))
			{
				_fb.setWorkingDays("Not Available");
				_fb.setTiming("Not Available");
			}else
			{
				temp=unitWorkingDays.split("#");
				_fb.setWorkingDays(temp[0]);
				_fb.setTiming(temp[1]);
			}*/
			
			//////////////////////////////////
			
			/*String room="";
			room=departmentUnitRoomEnquiry[0].getRoom()==null?"":departmentUnitRoomEnquiry[0].getRoom();
			for(int i=1;i<departmentUnitRoomEnquiry.length;i++){
				room=room+","+departmentUnitRoomEnquiry[i].getRoom();
			}
			_fb.setRoom(room);*/
			WebUTIL.setMapInSession(essentialMap,_rq);
			
			
			
		objStatus.add(Status.TRANSINPROCESS);
		objStatus.add(Status.RECORDFOUND);
		}catch(HisRecordNotFoundException e){
			
			essentialMap=e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap,_rq);
		objStatus.add(Status.TRANSINPROCESS);
		objStatus.add(Status.RECORDFOUND);
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	public static List setWorkingDetail(List <String> unitWorkingDetailList) {
		List list=new ArrayList();
		boolean flag=false;
		int m=0;
		
		/*for(int i=0;i<unitWorkingDetailList.size();i++){
			String room="";
			String unitDetail[]=unitWorkingDetailList.get(i).split("#");
			if(flag && i==m){
				continue;
			}	
			else{
			for(int j=i+1;j<unitWorkingDetailList.size();j++){
				String detail[]=unitWorkingDetailList.get(j).split("#");
				if(unitDetail[0].equals(detail[0]) && unitDetail[1].equals(detail[1]) && !unitDetail[2].equals(detail[2])){
					//unitWorkingDetailList.set(i,unitDetail[0]+"#"+unitDetail[1]+"#"+unitDetail[2]+","+detail[2]);
					room=room+", "+detail[2];
					flag=true;
					m=j;
					//break;
				}	
				else{
					flag=false;
					//room=unitDetail[2];
					//break;
				}
			}	
			if(room.equals("")){
				room=unitDetail[2];
			}else{
				room=unitDetail[2] +"," + room.replaceFirst("," , "");
			}
			
			list.add(unitDetail[0]+"#"+unitDetail[1]+"#"+room);
			}
		}*/
		
		Map map=new LinkedHashMap();
		String room="";
		String key="";
		for(int i=0;i<unitWorkingDetailList.size();i++){
			String unitDetail[]=unitWorkingDetailList.get(i).split("#");
			key=unitDetail[0]+"#"+unitDetail[1];
			room=(unitDetail.length>2?unitDetail[2]:"");
			if(map.containsKey(key)){
				room=(String)map.get(key);
				room+=" ,"+ unitDetail[2];
			}
			map.put(key, room);
		}
		
		Set keySet=map.keySet();
		Iterator itr=keySet.iterator();
		while (itr.hasNext()) {
			key=(String)itr.next();
			String value = (String) map.get(key);
			list.add(key+"#"+value);
		}
		
		return list;
	}
	
	public static List setTimingDetail(List <String> unitWorkingDetailList) {
		List list=new ArrayList();
		boolean flag=false;
		int m=0;
		
		/*for(int i=0;i<unitWorkingDetailList.size();i++){
			String time="";
			String unitDetail[]=unitWorkingDetailList.get(i).split("#");
			if(flag && i==m){
				continue;
			}	
			else{
				for(int j=i+1;j<unitWorkingDetailList.size();j++){
					String detail[]=unitWorkingDetailList.get(j).split("#");
					if(unitDetail[0].equals(detail[0]) && !unitDetail[1].equals(detail[1]) && unitDetail[2].equals(detail[2])){
						//unitWorkingDetailList.set(i,unitDetail[0]+"#"+unitDetail[1]+"#"+unitDetail[2]+","+detail[2]);
						time=time+", "+detail[1];
						flag=true;
						m=j;
						break;
					}	
					else{
						flag=false;
						//room=unitDetail[2];
						//break;
					}
				}	
				if(time.equals("")){
					time=unitDetail[1];
				}
				else
					time=unitDetail[1] +"," + time.replaceFirst(",", "");
				
				
				list.add(unitDetail[0]+"#"+time+"#"+unitDetail[2]);
			}
		}*/
		
		Map map=new LinkedHashMap();
		String timing="";
		String key="";
		for(int i=0;i<unitWorkingDetailList.size();i++){
			String unitDetail[]=unitWorkingDetailList.get(i).split("#");
			key=unitDetail[0]+"#"+(unitDetail.length>2?unitDetail[2]:"");
			timing=unitDetail[1];
			if(map.containsKey(key)){
				timing=(String)map.get(key);
				timing+=" ,"+ unitDetail[1];
			}
			map.put(key, timing);
		}
		
		Set keySet=map.keySet();
		Iterator itr=keySet.iterator();
		while (itr.hasNext()) {
			key=(String)itr.next();
			String value = (String) map.get(key);
			list.add(key.split("#")[0]+"#"+value+"#"+(key.split("#").length>1?key.split("#")[1]:""));
		}
		
		return list;
	}

public static void beddetail(HospitalDepartmentEnquiryFB _fb,HttpServletRequest request,HttpServletResponse response) {
	Status objStatus=new Status();
	IpdFB ipdFormBean=new IpdFB();
	UserVO userVO=getUserVO(request);
	ipdFormBean.setHospitalCode(userVO.getHospitalCode());
	ipdFormBean.setStrHospCode(userVO.getHospitalCode());
	ipdFormBean.setStrDeptUnitCode(_fb.getDepartmentUnitCode());
	ipdFormBean.setStrSeatId(userVO.getSeatId());
	ipdFormBean.setStrWardCode(_fb.getWardCode());
	
	
	String modPopUp=_fb.getWardCode()+"^0^0^"+_fb.getDepartmentUnitCode()+"^0";
	request.setAttribute("modPopUp",modPopUp);
	//ipdFormBean.setstr
	//ipdFormBean
	try{
	IpdDATA.beddetail(ipdFormBean, request, response);
	
	_fb.setBedProperty(ipdFormBean.getStrBedProperty());
	_fb.setStrWinResize(ipdFormBean.getStrWinResize());
	}catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		
		objStatus.add(Status.TRANSINPROCESS);
		objStatus.add(Status.RECORDFOUND);
	}
		
		//System.out.println("inside DATA-->beddetail");
		//IpdVO voObj=new IpdVO();
		/*HospitalDepartmentEnquiryVO enquiryVO=new HospitalDepartmentEnquiryVO();
		IpdBO BO=new IpdBO();
		try {
			HelperMethods.populate(enquiryVO, _fb);
		//voObj = (IpdVO)TransferObjectFactory.populateData("ipd.IpdVO",formBean);
		String tmp=request.getParameter("modPopUp");
		String imagepath= request.getParameter("imagepath");
		if(imagepath!=null)
			enquiryVO.setStrImagePath("/AHIMS");
		else
			enquiryVO.setStrImagePath("../..");
		String temp[]=tmp.replace('^','#').split("#");
		enquiryVO.setWardCode(temp[0]);
		enquiryVO.setRoomCode(temp[1]);
		enquiryVO.setStrBedTypeCode(temp[2]);
		enquiryVO.setStrDeptUnitCode(temp[3]);
		
		BO.setBedDetails(enquiryVO);
		
		*//***setting logic for default window size***//*
		
		if(request.getParameter("hmode").equals("BEDDETAILS")){
			enquiryVO.setStrBedChartMode("process_Admsn");
		}
		else
			enquiryVO.setStrBedChartMode("process_Other");
		
		*//*******************end**********************//*
		
		String strbed=HLPipd.getBedDetails(enquiryVO);
		_fb.setStrWinResize(enquiryVO.getStrWinResize());
		if(request.getParameter("hmode").equals("BEDDETAILS")){
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strbed+"^"+enquiryVO.getStrVacantBed());
		}
		
		_fb.setStrBedProperty(strbed);		
			_fb.setStrMsgType(enquiryVO.getStrMsgType());
				if(_fb.getStrMsgType().equals("1")) {
					_fb.setStrErrMsgString(enquiryVO.getStrErrMsgString());//error
				throw new Exception(_fb.getStrErrMsgString());
			}
		}
		catch(Exception e) {
			
		}
		finally {
			if(enquiryVO != null) enquiryVO = null;
			if(_fb != null) _fb = null;
		}*/
	}

	public static void getDeptByName(HospitalDepartmentEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		HttpSession session=_rq.getSession();
		try{
			String searchText=_fb.getSearchText();
			Map essentialMap=new HashMap();
			List deptList=(List)session.getAttribute(enquiryConfig.ALL_DEPARTMENT_WITH_DEPARTMENT_TYPE);
			List departmentList=getMatchedDeptList(searchText, deptList);
			Iterator itr=departmentList.iterator();
			Map allDepartmentMap=new LinkedHashMap();
			while(itr.hasNext())
			{
				Entry entryObj=(Entry)itr.next();
				String value=entryObj.getValue();
				String label=entryObj.getLabel();
				if(!allDepartmentMap.containsKey(value))///if map does not contain key
				{
					allDepartmentMap.put(value,new ArrayList());
				((ArrayList)allDepartmentMap.get(value)).add(label);
				}
				else
				{
					
					((ArrayList)allDepartmentMap.get(value)).add(label);
				}
				System.out.println("value"+value);
				System.out.println("label"+label);
				
			}
			
			Set mapKeySet= allDepartmentMap.keySet();
			Iterator keyItr=mapKeySet.iterator();
			int maxRows=0; 
			
			/////finding maximum list size
			while(keyItr.hasNext())
			{
				String departmentTypeKey=(String)keyItr.next();
				List departmentTypeList=(ArrayList)allDepartmentMap.get(departmentTypeKey);
				int numberOfRow=departmentTypeList.size();
				if(maxRows<numberOfRow)
					maxRows=numberOfRow;
				
				
			}
			System.out.println("max number of rows"+maxRows);
			keyItr=mapKeySet.iterator();
			////////This makes all the list of equal size
			while(keyItr.hasNext())
			{
				String departmentTypeKey=(String)keyItr.next();
				List departmentTypeList=(ArrayList)allDepartmentMap.get(departmentTypeKey);
				int numberOfRow=departmentTypeList.size();
				System.out.println("earlier list sixw"+departmentTypeList.size());
				if(maxRows>numberOfRow)
					for(int i=0;i<maxRows-numberOfRow;i++)
					{
						departmentTypeList.add("");
					}
				System.out.println("final list sixw"+departmentTypeList.size());
			}
			
			//_fb.setMaximumRow(String.valueOf(maxRows));
			WebUTIL.setMapInSession(essentialMap,_rq);
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.DEPARTMENT_ANDDEPARTMENT_TYPE_MAP_VIEW, allDepartmentMap);
			//System.out.println("all department type"+allDepartmentMap.size());
			if(allDepartmentMap.size()==0){
				objStatus.add(Status.LIST,"","No Department Found");
			}
			else
				objStatus.add(Status.LIST);
		}catch(HisRecordNotFoundException e){
			
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}
		finally{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}


	public static List createDeptList(List departmentList){
		List deptList=new ArrayList();
		Entry e=new Entry();
		for(int i=0;i<departmentList.size();i++){
			Entry entry=new Entry();
			String value=((Entry)departmentList.get(i)).getLabel();
			entry.setLabel(value.split("#")[1]);
			entry.setValue(value.split("#")[0]);
			deptList.add(entry);
		}
		return deptList;
	}
	
	public static List getMatchedDeptList(String searchText,List deptList){
		List matchedList=new ArrayList();
		for(int i=0;i<deptList.size();i++){
			Entry entry=(Entry)deptList.get(i);
			if(isMatches(entry.getLabel().split("#")[1], searchText)){
				matchedList.add(entry);
			}
		}
		return matchedList;
	}

	
	/**
	 * compare name with matcher if both are same or name contains substring of matcher
	 * @param name
	 * @param matcher
	 * @return true if name contains substring of matcher or it is same as matcher otherwise return false
	 */
	
	public static boolean isMatches(String name,String matcher){
		boolean flag=false;
		matcher=matcher.toLowerCase().trim();
		name=name.toLowerCase().trim();
		if(matcher.length()==1){
			if(name.startsWith(matcher)) return true;
			else
				return false;
		}
		String nameArray[]=name.split(" ");
		for(int i=0;i<nameArray.length;i++){
			if(nameArray[i].trim().equals(matcher))
				flag=true;
		}
		nameArray=name.split(matcher);
		if(nameArray.length>1 || nameArray.length==0)
			flag=true;
		
		return flag;
	}
	
	public static void createUnitTypeList(List deptList,HttpServletRequest _rq){
		List generalUnitList=new ArrayList();
		List specialUnitList=new ArrayList();
		HospitalDepartmentEnquiryVO departmentUnitEnquiry=new HospitalDepartmentEnquiryVO();
		for(int i=0;i<deptList.size();i++){
			departmentUnitEnquiry=(HospitalDepartmentEnquiryVO)deptList.get(i);
			if(departmentUnitEnquiry.getUnitType().equals(RegistrationConfig.UNIT_TYPE_GENERAL)){
				generalUnitList.add(departmentUnitEnquiry);
			}
			if(departmentUnitEnquiry.getUnitType().equals(RegistrationConfig.UNIT_TYPE_SPECIALITY)){
				specialUnitList.add(departmentUnitEnquiry);
			}
		}
		makeEqualSizeList(generalUnitList, specialUnitList)	;	
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.GENERAL_UNIT_LIST, generalUnitList);
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.SPECIAL_UNIT_LIST, specialUnitList);
		
		
	}

	public static void makeEqualSizeList(List generalUnitList,List specialUnitList){
		HospitalDepartmentEnquiryVO departmentUnitEnquiry=null;
		if(generalUnitList.size()>specialUnitList.size()){
			for(int i=specialUnitList.size();i<generalUnitList.size();i++){
				departmentUnitEnquiry=new HospitalDepartmentEnquiryVO();
				departmentUnitEnquiry.setUnitType("");
				specialUnitList.add(departmentUnitEnquiry);
			}	
		}
		else{
			for(int i=generalUnitList.size();i<specialUnitList.size();i++){
				departmentUnitEnquiry=new HospitalDepartmentEnquiryVO();
				departmentUnitEnquiry.setUnitType("");
				generalUnitList.add(departmentUnitEnquiry);
			}	
		}
	}
}
