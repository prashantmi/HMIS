package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.GuestHouseEnquiryDATA;
import enquiry.transaction.controller.fb.GuestHouseEnquiryFB;

public class GuestHouseEnquiryUTL extends ControllerUTIL{
	
	public static void getGuestHouseList(GuestHouseEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		try{
			List guestHouseList=GuestHouseEnquiryDATA.getGuestHouseList(getUserVO(_rq));
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.GUEST_HOUSE_LIST, guestHouseList);
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

	public static void getGuestHouseBedDetail(GuestHouseEnquiryFB fb,HttpServletRequest request) {
		Status objStatus=new Status();
		//HospitalFacilityMasterVO hospitalFacilityVO=new HospitalFacilityMasterVO();
		try{
			
			String guestHouse=fb.getGuestHouse();
			List guestHouseVOList=GuestHouseEnquiryDATA.getGuestHouseBedDetail(guestHouse,getUserVO(request));
			WebUTIL.setAttributeInSession(request, enquiryConfig.GUEST_HOUSE_DETAIL_LIST, guestHouseVOList);
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

	
	
		
}

	


