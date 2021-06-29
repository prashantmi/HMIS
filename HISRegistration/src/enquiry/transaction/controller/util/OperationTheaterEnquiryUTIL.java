package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
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

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.HospitalWardEnquiryDATA;
import enquiry.transaction.controller.data.OperationTheaterEnquiryDATA;
import enquiry.transaction.controller.fb.OperationTheaterEnquiryFB;
import enquiry.vo.OperationTheaterEnquiryVO;

public class OperationTheaterEnquiryUTIL extends ControllerUTIL {
	
	public static void getEssentials(OperationTheaterEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		try{
		setSysdate(_rq);
		Map essentialMap=new HashMap();
		OperationTheaterEnquiryVO operationTheaterEnqVO=null;
		essentialMap=OperationTheaterEnquiryDATA.getOperationTheaterEnquiryEssential(getUserVO(_rq));
		List operationTheaterEnqVOList=(List)essentialMap.get(enquiryConfig.OPERATION_THEATER_ENQUIRY_VO);
		Iterator itr=operationTheaterEnqVOList.iterator();
		Map allTheaterMap=new LinkedHashMap();
		while(itr.hasNext())
		{
			operationTheaterEnqVO=(OperationTheaterEnquiryVO)itr.next();
			String operationType=operationTheaterEnqVO.getOperationType();
			
			if(!allTheaterMap.containsKey(operationType))///if map does not contain key
			{
				allTheaterMap.put(operationType,new ArrayList());
			List list=(ArrayList)allTheaterMap.get(operationType);
			list.add(operationTheaterEnqVO);
			}
			else
			{
				List list=(ArrayList)allTheaterMap.get(operationType);
				list.add(operationTheaterEnqVO);
			}
		}
		
		Set mapKeySet= allTheaterMap.keySet();
		Iterator keyItr=mapKeySet.iterator();
		int maxRows=0; 
		
		/////finding maximum list size
		while(keyItr.hasNext())
		{
			String operationTypeKey=(String)keyItr.next();
			List operationTypeList=(ArrayList)allTheaterMap.get(operationTypeKey);
			int numberOfRow=operationTypeList.size();
			if(maxRows<numberOfRow)
				maxRows=numberOfRow;
			
			
		}
		System.out.println("max number of rows"+maxRows);
		keyItr=mapKeySet.iterator();
		////////This makes all the list of equal size
		while(keyItr.hasNext())
		{
			String operationTypeKey=(String)keyItr.next();
			List operationTypeList=(ArrayList)allTheaterMap.get(operationTypeKey);
			int numberOfRow=operationTypeList.size();
			System.out.println("earlier list sixw"+operationTypeList.size());
			if(maxRows>numberOfRow)
				for(int i=0;i<maxRows-numberOfRow;i++)
				{
					operationTypeList.add(new OperationTheaterEnquiryVO());
				}
			System.out.println("final list sixw"+operationTypeList.size());
		}
		
		//_fb.setMaximumRow(String.valueOf(maxRows));
		WebUTIL.setMapInSession(essentialMap,_rq);
		WebUTIL.setAttributeInSession(_rq, enquiryConfig.OPERATION_TYPE_MAP, allTheaterMap);
		
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
	
	public static void getOperationTheaterDetail(OperationTheaterEnquiryFB _fb,HttpServletRequest _rq){
		Status objStatus=new Status();
		try{
			Map essentialMap=new HashMap();
			OperationTheaterEnquiryVO operationTheaterEnqVO=null;
			essentialMap=OperationTheaterEnquiryDATA.getOperationTheaterEnquiryEssential(getUserVO(_rq));
			List operationTheaterEnqVOList=(List)essentialMap.get(enquiryConfig.OPERATION_THEATER_ENQUIRY_VO);
			String theaterCode=_fb.getTheaterCode();
			for(int i=0;i<operationTheaterEnqVOList.size();i++)
			{
				operationTheaterEnqVO=(OperationTheaterEnquiryVO)operationTheaterEnqVOList.get(i);
				if(operationTheaterEnqVO.getTheaterCode().equals(theaterCode)){
					break;
				}
			}
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.OPERATION_THEATER_ENQUIRY_VO, operationTheaterEnqVO);
			
			objStatus.add(Status.TRANSINPROCESS);
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
	

}
