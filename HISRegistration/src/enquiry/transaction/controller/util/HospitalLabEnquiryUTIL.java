package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.HospitalLabEnquiryDATA;
import enquiry.transaction.controller.fb.HospitalLabEnquiryFB;
import enquiry.vo.HospitalLabEnquiryVO;

public class HospitalLabEnquiryUTIL extends ControllerUTIL {
	
	public static void getEssentials(HospitalLabEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		try{
		//setSysdate(_rq);
		
		UserVO objUserVO = getUserVO(_rq);
		_fb.setHospitalCode(objUserVO.getHospitalCode());
		
		List hospitalList=HospitalLabEnquiryDATA.getHospitalCombo();
	    WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
	    
	    List laboratoryList=HospitalLabEnquiryDATA.getLabEnquiryEssential(objUserVO);
	    getLabEssentials(_fb, _rq, laboratoryList);			
		
		//List laboratoryList=HospitalLabEnquiryDATA.getLabEnquiryEssential(getUserVO(_rq));
		//setMultipleColumn(laboratoryList, _rq, 3);
		//WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_LABORATORY_LIST, laboratoryList);
		
		objStatus.add(Status.LIST);
		_fb.setIsHospitalComboShown("1");
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

	public static void onChangeHospital(HospitalLabEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		try{
		//setSysdate(_rq);
			UserVO objUserVO = getUserVO(_rq);
			//objUserVO.setHospitalCode(_fb.getHospitalCode());
			
			List hospitalList= (List)_rq.getSession().getAttribute(RegistrationConfig.HOSPITAL_COMBO_LIST);
			WebUTIL.refreshTransState(_rq);
			WebUTIL.setAttributeInSession(_rq, RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		    
		    List laboratoryList=HospitalLabEnquiryDATA.getLabEnquiryEssential(objUserVO);
		    getLabEssentials(_fb, _rq, laboratoryList);
		    if(laboratoryList.size()<1){
		    	objStatus.add(objStatus.ERROR_AE,"No Laboratory Detail Found","");
		    	WebUTIL.setStatus(_rq, objStatus);
		    }
		    //setMultipleColumn(laboratoryList, _rq, 3);
		//WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_LABORATORY_LIST, laboratoryList);
		
		objStatus.add(Status.LIST);
		_fb.setIsHospitalComboShown("0");
		}catch(HisRecordNotFoundException e){
			
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,e.getMessage() ,"");
			WebUTIL.setStatus(_rq, objStatus);
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_DA,"Data Access Error" ,"");
		}catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
		}catch(Exception e){
			e.printStackTrace();
			objStatus.add(objStatus.ERROR_AE,"Application Execution Error" ,"");
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	public static void getLabEssentials(HospitalLabEnquiryFB _fb,HttpServletRequest _rq, List laboratoryList){
		Status objStatus=new Status();
		try{
		//setSysdate(_rq);
		
		//List laboratoryList=HospitalLabEnquiryDATA.getLabEnquiryEssential(getUserVO(_rq));
		setMultipleColumn(laboratoryList, _rq, 3);
		//WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_LABORATORY_LIST, laboratoryList);
		
		objStatus.add(Status.LIST);
		_fb.setIsHospitalComboShown("0");
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

	public static void getLabTests(HospitalLabEnquiryFB fb,	HttpServletRequest request) {
		Status objStatus=new Status();
		try{
			String labCode=fb.getLabCode();
			HospitalLabEnquiryVO labEnquiryVO=new HospitalLabEnquiryVO();
			List labTestList=HospitalLabEnquiryDATA.getLabTestList(labCode,getUserVO(request));
			labEnquiryVO=(HospitalLabEnquiryVO)labTestList.get(0);
			labEnquiryVO.setLabType(fb.getLabType());
			HelperMethods.populate(fb, labEnquiryVO);
			for(int i=0;i<labTestList.size();i++){
				labEnquiryVO=(HospitalLabEnquiryVO)labTestList.get(i);
				labEnquiryVO.setTestDay(setDays(labEnquiryVO));
				labTestList.set(i, labEnquiryVO);
			}
			WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_LAB_TEST_LIST, labTestList);
			WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_LAB_TEST_LIST_VIEW, labTestList);
			
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	/**
	 * Create multiple list of equal size from one list. 
	 * @param labList
	 * @param _rq
	 * @param noOfColumns -- decide how many list has to be created
	 */
	public static void setMultipleColumn(List labList,HttpServletRequest _rq,int noOfColumns){
		
		List labList1=null;
		Map laboratoryMap=new LinkedHashMap();
		
		if(labList!=null && labList.size()>0){
			int len=labList.size();
			len=len/noOfColumns;
			int j,k=0,count=0;
			int noOfRows=len;
			int noOfItemLeft=0;
			if(labList.size()%noOfColumns!=0){
				noOfItemLeft=labList.size()-len*noOfColumns;
			}
			
			for(int i=0;i<noOfColumns;i++){
				labList1=new ArrayList();
				if(noOfItemLeft>0)
					len=len+1;
				for(j=k;j<len;j++){
					labList1.add(labList.get(j));
					count++;
				}
				laboratoryMap.put(i,labList1);
				k=len;
				len=len+noOfRows;
				noOfItemLeft--;
			}
			
			/*if(labList.size()%noOfColumns!=0){
				j=0;
				for(int i=count;i<labList.size();i++){
					labList1=(List)laboratoryMap.get(j);
					labList1.add(labList.get(i));
					laboratoryMap.put(j++, labList1);
				}
			}*/
			
			Set mapKeySet= laboratoryMap.keySet();
			Iterator keyItr=mapKeySet.iterator();
			int maxRows=0; 
			
			/////finding maximum list size
			while(keyItr.hasNext())
			{
				int key=(Integer)keyItr.next();
				List list=(ArrayList)laboratoryMap.get(key);
				int numberOfRow=list.size();
				if(maxRows<numberOfRow)
					maxRows=numberOfRow;
				
				
			}
			keyItr=mapKeySet.iterator();
			////////This makes all the list of equal size
			while(keyItr.hasNext())
			{
				int key=(Integer)keyItr.next();
				List list=(ArrayList)laboratoryMap.get(key);
				int numberOfRow=list.size();
				if(maxRows>numberOfRow)
					for(int i=0;i<maxRows-numberOfRow;i++)
					{
						list.add(new HospitalLabEnquiryVO());
					}
			}
		}
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.HOSPITAL_LABORATORY_MAP, laboratoryMap);
	}

	public static void getTestByName(HospitalLabEnquiryFB fb,	HttpServletRequest request) {
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		List testNameList=new ArrayList();
		try{
			//String labCode=fb.getLabCode();
			String testName=fb.getTestName();
			HospitalLabEnquiryVO labEnquiryVO=new HospitalLabEnquiryVO();
			List labTestList=(ArrayList)session.getAttribute(enquiryConfig.HOSPITAL_LAB_TEST_LIST);
			for(int i=0;i<labTestList.size();i++){
				labEnquiryVO=(HospitalLabEnquiryVO)labTestList.get(i);
				if(isMatches(labEnquiryVO.getTestName(), testName)){
					testNameList.add(labEnquiryVO);
				}
			}
			
			
			if(testNameList.size()>0)
				objStatus.add(Status.TRANSINPROCESS);
			else{
				objStatus.add(Status.TRANSINPROCESS,"","No Test Found");
				testNameList.add(new HospitalLabEnquiryVO());
			}	
			WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_LAB_TEST_LIST_VIEW, testNameList);
			
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
	
	/**
	 * compare name with matcher if both are same or name contains substring of matcher
	 * @param name
	 * @param matcher
	 * @return true if name contains substring of matcher or it is same as matcher otherwise return false
	 */
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
	
	/**
	 * Decode days of lab 
	 * @param labEnquiryVO
	 * @return a concat string of form "sun,mon..."
	 */
	public static String setDays(HospitalLabEnquiryVO labEnquiryVO){
		String dayString="";
		String weekDay[]={"Sun","Mon","Tue","Wed","Thr","Fri","Sat"};
		char day[]=labEnquiryVO.getTestDay().toCharArray();
		for(int i=0;i<day.length;i++){
			if(day[i]=='1'){ // if Lab is working on that day. 1-working  0-not working
				dayString=dayString+", "+ weekDay[i];
			}
		}
		if(dayString.length()>1)
			dayString=dayString.substring(1);
		return dayString;
	}
	
}
