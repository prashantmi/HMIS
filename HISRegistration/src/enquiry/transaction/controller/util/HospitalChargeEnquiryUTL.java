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
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.HospitalChargeEnquiryDATA;
import enquiry.transaction.controller.fb.HospitalChargeEnquiryFB;
import enquiry.vo.HospitalChargeEnquiryVO;

public class HospitalChargeEnquiryUTL extends ControllerUTIL{
	
	public static void getAllHospitalTarrifList(HospitalChargeEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		try{
			setSysdate(_rq);
			UserVO objUserVO = getUserVO(_rq);
			String groupId=_fb.getGroupId();
			if(groupId.equals("-1")) groupId="%";
			
			List hospitalList=HospitalChargeEnquiryDATA.getHospitalCombo();
		    WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
			Map essentialMap=(HashMap)HospitalChargeEnquiryDATA.getAllHospitalTarrifList(groupId, objUserVO);
			List tariffList=(ArrayList)essentialMap.get(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST);
			setMultipleColumn(tariffList, _rq);
			WebUTIL.setMapInSession(essentialMap, _rq);
			_fb.setHospitalCode(objUserVO.getHospitalCode());
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
		
	}
	
	public static void getSelectedHospitalTarrifList(HospitalChargeEnquiryFB _fb, HttpServletRequest _rq){
		Status objStatus=new Status();
		HttpSession session=_rq.getSession();
		List tarrifListNew=null;
		UserVO objUserVO= null;
		HospitalChargeEnquiryVO hospitalChargeVO=new HospitalChargeEnquiryVO();
		try{
			objUserVO = getUserVO(_rq);
			objUserVO.setHospitalCode(_fb.getHospitalCode());
			String groupId=_fb.getGroupId();
			if(groupId.equals("-1")) groupId="%";
			Map essentialMap=(HashMap)HospitalChargeEnquiryDATA.getAllHospitalTarrifList(groupId, objUserVO);
			List tarrifList=(ArrayList)essentialMap.get(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST);
			WebUTIL.setMapInSession(essentialMap, _rq);
			
			String tariffName=_fb.getTariffName().trim();
			if(tariffName!=null && !tariffName.equals(""))
				searchTariffByName(_fb, _rq);
			else
				getTariffByGroupId(_fb, _rq);
			
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

	public static void getChargeDetailByTariff(HospitalChargeEnquiryFB fb,HttpServletRequest request) {
		Status objStatus=new Status();
		HospitalChargeEnquiryVO hospitalChargeVO=new HospitalChargeEnquiryVO();
		HospitalChargeEnquiryVO chargeVO=null;
		Map chargeDetailMap=new HashMap();
		Map essentialMap=new HashMap();
		List chargeVoList=null;
		try{
			String tariffId=fb.getTariffId();
			UserVO objUserVO = getUserVO(request);
			objUserVO.setHospitalCode(fb.getHospitalCode());
			essentialMap=HospitalChargeEnquiryDATA.getChargeDetailByTariff(tariffId, objUserVO);
			List chargeEnquiryVoList=(ArrayList)essentialMap.get(enquiryConfig.HOSPITAL_CHARGE_DETAIL_VO_LIST);
			List chargeTypeList=(ArrayList)essentialMap.get(enquiryConfig.HOSPITAL_CHARGE_TYPE_LIST);
			
			//creating list of patient Category and putting 
			//them in map with key as patient category
			System.out.println("chargeEnquiryVoList......"+chargeEnquiryVoList.size());
			for(int i=0;i<chargeEnquiryVoList.size();i++){
				hospitalChargeVO=(HospitalChargeEnquiryVO)chargeEnquiryVoList.get(i);
				
				if(!chargeDetailMap.containsKey(hospitalChargeVO.getPatCat())){
					chargeVoList=new ArrayList();
					chargeVO=new HospitalChargeEnquiryVO();
					chargeVO.setChargeTypeId(hospitalChargeVO.getChargeTypeId());
					chargeVO.setChargeType(hospitalChargeVO.getChargeType());
					chargeVO.setIpdChargeTypeId(hospitalChargeVO.getIpdChargeTypeId());
					chargeVO.setCharge(hospitalChargeVO.getCharge());
					chargeVoList.add(chargeVO);
					chargeDetailMap.put(hospitalChargeVO.getPatCat(), chargeVoList);
				}
				else{
				//	System.out.println("get chargeDetailMap"+chargeDetailMap.get(hospitalChargeVO.getPatCat().toString()));
					chargeVoList=(ArrayList)chargeDetailMap.get(hospitalChargeVO.getPatCat().toString());
					chargeVO=new HospitalChargeEnquiryVO();
					/*for(int j=0;j<chargeVoList.size();j++){
						chargeVO=(HospitalChargeEnquiryVO)chargeVoList.get(j);
						if(chargeVO.getChargeTypeId().equals(hospitalChargeVO.getChargeTypeId())){
							chargeVO.setCharge(hospitalChargeVO.getCharge());
							chargeVoList.set(j,chargeVO);
						}
						else{*/
							chargeVO.setChargeTypeId(hospitalChargeVO.getChargeTypeId());
							chargeVO.setChargeType(hospitalChargeVO.getChargeType());
							chargeVO.setIpdChargeTypeId(hospitalChargeVO.getIpdChargeTypeId());
							chargeVO.setCharge(hospitalChargeVO.getCharge());
							chargeVoList.add(chargeVO);
//						}
							
					//}
					chargeDetailMap.put(hospitalChargeVO.getPatCat(), chargeVoList);
				}
				
			}
			Set keySet=chargeDetailMap.keySet();
			String [] key=new String[keySet.size()];
			Iterator keyIterator=keySet.iterator();
			int i=0;
			while(keyIterator.hasNext()){
				key [i++]=(String)keyIterator.next();
			}
			for(i=0;i<chargeDetailMap.size();i++){
				List chargeList= (ArrayList)chargeDetailMap.get(key[i]);
				if(chargeList.size()<chargeTypeList.size()){
					for(int j=chargeList.size();j<chargeTypeList.size();j++){
						chargeList.add(new HospitalChargeEnquiryVO ());
					}
					chargeDetailMap.put(key[i], chargeList);
				}
				
			}
			WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_CHARGE_DETAIL_MAP, chargeDetailMap);
			WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_CHARGE_TYPE_LIST, chargeTypeList);
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

	public static void getTariffByGroupId(HospitalChargeEnquiryFB fb, HttpServletRequest request) {
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		List tarrifListNew=null;
		HospitalChargeEnquiryVO hospitalChargeVO=new HospitalChargeEnquiryVO();
		try{
			String groupId=fb.getGroupId().trim();
			List tarrifList=(ArrayList)session.getAttribute(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST);
			if(groupId.equals("-1")){
				tarrifListNew=tarrifList;
			}
			else{
				tarrifListNew=new ArrayList();
				for(int i=0;i<tarrifList.size();i++){
					hospitalChargeVO=(HospitalChargeEnquiryVO)tarrifList.get(i);
					if(hospitalChargeVO.getGroupId().trim().equals(groupId)){
						tarrifListNew.add(hospitalChargeVO);
					}
					
				}
			}
			
			//WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW, tarrifListNew);
			if(tarrifListNew==null || tarrifListNew.size()==0){
				setMultipleColumn(tarrifListNew, request);
				objStatus.add(Status.LIST,"","No Tariff Found");
			}
			else
				setMultipleColumn(tarrifListNew, request);
				objStatus.add(Status.LIST);
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
	
	public static void searchTariffByName(HospitalChargeEnquiryFB fb, HttpServletRequest request) {
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		List tarrifListNew=null;
		HospitalChargeEnquiryVO hospitalChargeVO=new HospitalChargeEnquiryVO();
		try{
			String tariffName=fb.getTariffName().trim();
			List tarrifList=(ArrayList)session.getAttribute(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST);
			tarrifListNew=new ArrayList();
			for(int i=0;i<tarrifList.size();i++){
				hospitalChargeVO=(HospitalChargeEnquiryVO)tarrifList.get(i);
				if(isMatches(hospitalChargeVO.getTariffName(),tariffName)){
					tarrifListNew.add(hospitalChargeVO);
				}
			}
			
			//WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW, tarrifListNew);
			if(tarrifListNew==null || tarrifListNew.size()==0){
				setMultipleColumn(tarrifListNew, request);
				objStatus.add(Status.LIST,"","No Tariff Found");
			}
			else
				setMultipleColumn(tarrifListNew, request);
				objStatus.add(Status.LIST);
		}
		catch(HisException e){
			
			objStatus.add(Status.ERROR,e.getMessage(),"");
			
		}
		finally{
			WebUTIL.setStatus(request, objStatus);
		}
		
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
	
	/**
	 * Creates two list from One List to display in two columns
	 * @param tariffList
	 * @param _rq
	 */
	public static void setMultipleColumn(List tariffList,HttpServletRequest _rq){
		List tariffList1=null;
		List tariffList2=null;
		if(tariffList!=null && tariffList.size()>0){
			int len=tariffList.size();
			len=tariffList.size()/enquiryConfig.RECORD_PER_PAGE;
			tariffList1=new ArrayList();
			tariffList2=new ArrayList();
			int numRow=0;
			int i=0;
			if(tariffList.size()<=enquiryConfig.RECORD_PER_PAGE){
				for( i=numRow;i< tariffList.size();i++){
					tariffList1.add(tariffList.get(i));
				}
			}
			else{
				for(int k=1;k<=len;k++){
					
					if(k%2!=0){
						for( i=numRow;i< tariffList.size() & i<(k*enquiryConfig.RECORD_PER_PAGE);i++){
							tariffList1.add(tariffList.get(i));
						}
					}
					else{
						for(int j=numRow;j< tariffList.size() & j<(k*enquiryConfig.RECORD_PER_PAGE);j++){
							tariffList2.add(tariffList.get(j));
						}
					}
					numRow=k*enquiryConfig.RECORD_PER_PAGE;
				}
				
				if(tariffList.size()%enquiryConfig.RECORD_PER_PAGE!=0){
					for(int l=len*20;l<tariffList.size();l++){
						tariffList2.add(tariffList.get(l));
					}
				}
			}
			//making both list of equal size
			if(tariffList1.size()>tariffList2.size()){
				for(int l=0;l<tariffList1.size();l++){
					HospitalChargeEnquiryVO chargeVo=new HospitalChargeEnquiryVO();
					chargeVo.setTariffName("");
					tariffList2.add(chargeVo);
				}
			}
		}
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW1, tariffList1);
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW2, tariffList2);
	}
			
	/*public static void setMultipleColumn(List tariffList,HttpServletRequest _rq){
		List tariffList1=null;
		List tariffList2=null;
		if(tariffList!=null && tariffList.size()>0){
			int len=tariffList.size();
			tariffList1=new ArrayList();
			tariffList2=new ArrayList();
			for(int i=0;i<tariffList.size();){
				tariffList1.add(tariffList.get(i));
				i+=2;
			}
			for(int i=1;i<tariffList.size();){
				tariffList2.add(tariffList.get(i));
				i+=2;
			}
			
			if(tariffList1.size()>tariffList2.size()){
				HospitalChargeEnquiryVO chargeVo=new HospitalChargeEnquiryVO();
				chargeVo.setTariffName("");
				tariffList2.add(chargeVo);
			}
			else{
				HospitalChargeEnquiryVO chargeVo=new HospitalChargeEnquiryVO();
				chargeVo.setTariffName("");
				tariffList1.add(chargeVo);
			}
		}
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW1, tariffList1);
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW2, tariffList2);
	}*/
	
}

	


