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
import javax.servlet.http.HttpServletRequest;
import enquiry.transaction.controller.data.FreePatCatEnquiryDATA;
import enquiry.transaction.controller.fb.FreePatientListEnquiryFB;
import enquiry.*;

public class FreePatCatUTL extends ControllerUTIL{
	
	public static void getEssentials(FreePatientListEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		List freePatCat=new ArrayList();
		UserVO userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			freePatCat=FreePatCatEnquiryDATA.getFreePatientCat(userVO);
			System.out.println(freePatCat);
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.ENQUIRY_FREE_PATIENT_CAT_LIST, freePatCat);
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
	
	
	
}
