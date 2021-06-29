package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.HisHttpServletRequest;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.UserVO;
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
import enquiry.transaction.controller.data.HospitalWardEnquiryDATA;
import enquiry.transaction.controller.fb.HospitalWardEnquiryFB;
import enquiry.vo.HospitalWardEnquiryVO;

public class HospitalWardEnquiryUTIL extends ControllerUTIL {
	
	public static void getEssentials(HospitalWardEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		try{
			UserVO objUserVO = getUserVO(_rq);
			_fb.setHospitalCode(objUserVO.getHospitalCode());
			setSysdate(_rq);
			Map essentialMap=new HashMap();
			
			List hospitalList=HospitalDepartmentEnquiryDATA.getHospitalCombo();
		    WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
			essentialMap=HospitalWardEnquiryDATA.getWardEnquiryEssential(objUserVO);
			
			getWardEssentials(_fb, _rq, essentialMap);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.LIST);
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
	
	public static void onChangeHospital(HospitalWardEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		Map essentialMap=new HashMap();

		try{
			UserVO objUserVO = getUserVO(_rq);
			objUserVO.setHospitalCode(_fb.getHospitalCode());
			
			List hospitalList= (List)_rq.getSession().getAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST);
			WebUTIL.refreshTransState(_rq);
			WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			
			essentialMap=HospitalWardEnquiryDATA.getWardEnquiryEssential(objUserVO);
			
			getWardEssentials(_fb, _rq, essentialMap);
		}
		catch(HisRecordNotFoundException e){
			objStatus.add(Status.LIST);
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
			//_fb.setIsPageList("0");
			essentialMap.put(enquiryConfig.WARD_TYPE_MAP, new LinkedHashMap());
			WebUTIL.setMapInSession(essentialMap,_rq);
			WebUTIL.setStatus(_rq, objStatus);
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
			//_fb.setIsPageList("0");
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	public static void getWardEssentials(HospitalWardEnquiryFB _fb,HttpServletRequest _rq, Map essentialMap){
		Status objStatus=new Status();
		try{
		//setSysdate(_rq);
		//Map essentialMap=new HashMap();
		//essentialMap=HospitalWardEnquiryDATA.getWardEnquiryEssential(getUserVO(_rq));
		HospitalWardEnquiryVO wardEnquiryVO=null;
		
		List wardList=(List)essentialMap.get(enquiryConfig.DEPARTMENT_UNIT_WARD_LIST);
		//wardList=setMaxLength(wardList);
		Iterator itr=wardList.iterator();
		Map allWardMap=new LinkedHashMap();
		
		while(itr.hasNext())
		{
			wardEnquiryVO=(HospitalWardEnquiryVO)itr.next();
			String wardType=wardEnquiryVO.getWardType();
			//System.out.println("Ward Type :>>>>> "+ wardType);
			if(!allWardMap.containsKey(wardType))///if map does not contain key
			{
				allWardMap.put(wardType,new ArrayList());
			List list=(ArrayList)allWardMap.get(wardType);
			list.add(wardEnquiryVO);
			}
			else
			{
				List list=(ArrayList)allWardMap.get(wardType);
				list.add(wardEnquiryVO);
			}
		}
		
		Set mapKeySet= allWardMap.keySet();
		Iterator keyItr=mapKeySet.iterator();
		int maxRows=0; 
		
		/////finding maximum list size
		while(keyItr.hasNext())
		{
			String wardTypeKey=(String)keyItr.next();
			List wardTypeList=(ArrayList)allWardMap.get(wardTypeKey);
			int numberOfRow=wardTypeList.size();
			if(maxRows<numberOfRow)
				maxRows=numberOfRow;
			
			
		}
		//System.out.println("max number of rows"+maxRows);
		keyItr=mapKeySet.iterator();
		////////This makes all the list of equal size
		while(keyItr.hasNext())
		{
			String wardTypeKey=(String)keyItr.next();
			List wardTypeList=(ArrayList)allWardMap.get(wardTypeKey);
			int numberOfRow=wardTypeList.size();
			//System.out.println("earlier list sixw"+wardTypeList.size());
			if(maxRows>numberOfRow)
				for(int i=0;i<maxRows-numberOfRow;i++)
				{
					wardTypeList.add(new HospitalWardEnquiryVO());
				}
			//System.out.println("final list sixw"+wardTypeList.size());
		}
		
		//_fb.setMaximumRow(String.valueOf(maxRows));
		WebUTIL.setMapInSession(essentialMap,_rq);
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.WARD_TYPE_MAP, allWardMap);
		//System.out.println("all Ward type"+allWardMap.size());
		objStatus.add(Status.LIST);
		_fb.setIsPageList("1");
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

	public static boolean getWardDetail(HospitalWardEnquiryFB _fb,HttpServletRequest _rq,HttpServletResponse response){
		Status objStatus=new Status();
		HospitalWardEnquiryVO enquiryVO;
		List wardDetailList;
		String wardCode=_fb.getWardCode();
		boolean flag=false;
		try{
		
			UserVO objUserVO = getUserVO(_rq);
			objUserVO.setHospitalCode(_fb.getHospitalCode());
			wardDetailList=HospitalWardEnquiryDATA.getWardEnquiryDetail(wardCode,objUserVO);
			enquiryVO=(HospitalWardEnquiryVO)wardDetailList.get(0);
			_fb.setWard(enquiryVO.getWard());
			_fb.setWardCode(enquiryVO.getWardCode());
			_fb.setLocationBlock(enquiryVO.getLocationBlock());
			_fb.setLocationBuilding(enquiryVO.getLocationBuilding());
			_fb.setLocationFloor(enquiryVO.getLocationFloor());
			enquiryVO=(HospitalWardEnquiryVO)wardDetailList.get(0);
			String unitCode=enquiryVO.getDepartmentUnitCode();
			
			for(int i=1;i<wardDetailList.size();i++){
				enquiryVO=(HospitalWardEnquiryVO)wardDetailList.get(i);
				if(!enquiryVO.getDepartmentUnitCode().equals(unitCode)){
					flag=true;
					break;
				}
			}
			WebUTIL.setAttributeInSession(_rq,enquiryConfig.ENQUIRY_WARD_DETAIL_VO, wardDetailList);
			if(flag){
				objStatus.add(Status.TRANSINPROCESS);
				//_fb.setIsPageList("0");
			}
			else{
				enquiryVO=(HospitalWardEnquiryVO)wardDetailList.get(0);
				_fb.setWardCode(enquiryVO.getWardCode());
				_fb.setDepartmentUnitCode(enquiryVO.getDepartmentUnitCode());
				_fb.setDepartment(enquiryVO.getDepartment());
				_fb.setDepartmentUnit(enquiryVO.getDepartmentUnit());
				bedDetail(_fb, _rq, response);
				
			}
		}catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL);
			flag=true;
			_fb.setIsPageList("1");
			e.printStackTrace();
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
		return flag;
	}
	
	
	
	public static void bedDetail(HospitalWardEnquiryFB _fb,HttpServletRequest request,HttpServletResponse response) {
		Status objStatus=new Status();
		HospitalWardEnquiryVO enquiryVO;
		String deskType=(String)request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType!=null && deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK))
		{
			String sessionUnitCode=(String)request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
			String sessionWardCode=(String)request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE);;
			_fb.setDepartmentUnitCode(sessionUnitCode);
			_fb.setWardCode(sessionWardCode);
		}
		
		//List wardDetailList;
		String wardCode=_fb.getWardCode();
		//enquiryVO=(HospitalWardEnquiryVO)wardDetailList.get(0);
		IpdFB ipdFormBean=new IpdFB();
		try{
			UserVO userVO=getUserVO(request);
			userVO.setHospitalCode(_fb.getHospitalCode());
			ipdFormBean.setHospitalCode(userVO.getHospitalCode());
			ipdFormBean.setStrHospCode(userVO.getHospitalCode());
			ipdFormBean.setStrDeptUnitCode(_fb.getDepartmentUnitCode());
			ipdFormBean.setStrSeatId(userVO.getSeatId());
			ipdFormBean.setStrWardCode(wardCode);
			
			String modPopUp=_fb.getWardCode()+"^ ^ ^"+_fb.getDepartmentUnitCode()+"^";
			HisHttpServletRequest hisRequest=(HisHttpServletRequest)request;
			hisRequest.setHisParameter("modPopUp",modPopUp);
			request.setAttribute("modPopUp",modPopUp);
			//ipdFormBean.setstr
			//ipdFormBean
			IpdDATA.beddetail(ipdFormBean, hisRequest, response);
			_fb.setBedProperty(ipdFormBean.getStrBedProperty());
			//_fb.setStrWinResize(ipdFormBean.getStrWinResize());
			objStatus.add(Status.DONE);
		
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}
		finally{
			WebUTIL.setStatus(request, objStatus);
		}
	
	}

	public static void getbedDetail(HospitalWardEnquiryFB fb,HttpServletRequest request, HttpServletResponse response) {
		HttpSession session =request.getSession();
		Status objStatus=new Status();
		HospitalWardEnquiryVO enquiryVO=null;
		try{
			
			List wardDetailList=(ArrayList)session.getAttribute(enquiryConfig.ENQUIRY_WARD_DETAIL_VO);
			List wardDetailListView=new ArrayList();
			String departmentUnitCode=fb.getDepartmentUnitCode();
			String wardCode=fb.getWardCode();
			Iterator iterator =wardDetailList.iterator();
			while(iterator.hasNext()){
				enquiryVO=new HospitalWardEnquiryVO();
				enquiryVO=(HospitalWardEnquiryVO)iterator.next();
				if(enquiryVO.getWardCode().equals(wardCode) && enquiryVO.getDepartmentUnitCode().equals(departmentUnitCode)){
					wardDetailListView.add(enquiryVO);
				}
			}
			
			WebUTIL.setAttributeInSession(request,enquiryConfig.ENQUIRY_WARD_DETAIL_VO_VIEW, wardDetailListView);
			objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e){
			objStatus.add(Status.LIST);
			fb.setIsPageList("1");
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
			WebUTIL.setStatus(request, objStatus);
		}
	}


	public static List setMaxLength(List wardList){
		HospitalWardEnquiryVO wardEnquiryVO=null;
		List list=new ArrayList();
		Iterator itr=wardList.iterator();
		int maxLength=0;
		while(itr.hasNext())
		{
			wardEnquiryVO=(HospitalWardEnquiryVO)itr.next();
			int wardlength=wardEnquiryVO.getWard().length();
			
			if(maxLength<wardlength)
				maxLength=wardlength;
		}
		itr=wardList.iterator();
		while(itr.hasNext())
		{
			wardEnquiryVO=(HospitalWardEnquiryVO)itr.next();
			int wardlength=wardEnquiryVO.getWard().length();
			int lengthToAdd=maxLength-wardlength;
			for(int i=0;i<lengthToAdd;i++){
				wardEnquiryVO.setWard(wardEnquiryVO.getWard()+" ");
			}
			list.add(wardEnquiryVO);
		}
		return list;
	}


}
