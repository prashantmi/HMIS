package registration.transactions.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.transactions.controller.data.verificationDocumentDATA;

public class verificationDocumentUTIL extends ControllerUTIL {

	/**
	 * sets all verification Document Essentials
	 * @param _request -HttpServletRequest
	 */
	
	public static void setAllVerificationDocEssentials(HttpServletRequest _request,String _processName){	
		System.out.println("set fucntion util........................");
		Status objStatus=new Status();
		try{
		Map mp=verificationDocumentDATA.getAllVerificationDocEssentials(getUserVO(_request));
		System.out.println("MAP:::"+mp);
		if(_processName==null||_processName.equals(""))
			_processName="verificationDocumentACTION";
		WebUTIL.setMapInSession(mp,_request,_processName);
		objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");			
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());	
		}		
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		 System.out.println("objStatus in finally"+objStatus);		 
		 System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}
	
	/**public static UserVO getUserVO(HttpServletRequest request){
		//UserVO userVO = (UserVO) request.getSession().getAttribute(RegistrationConfig.USERVO);
		//<<hardCoded>>>>>>
		UserVO userVO = new UserVO();
		userVO.setSeatId("10001");
		return userVO;		
	}*/
	
}//end of class
