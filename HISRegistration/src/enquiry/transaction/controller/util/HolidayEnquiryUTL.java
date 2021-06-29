package enquiry.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.HolidayEnquiryDATA;
import enquiry.transaction.controller.fb.HolidayEnquiryFB;

public class HolidayEnquiryUTL extends ControllerUTIL{
	
	public static void getHolidayList(HolidayEnquiryFB _fb, HttpServletRequest _rq){
		
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		try{
			setSysdate(_rq);
			Date currentDate =(Date)_rq.getSession().getAttribute(Config.SYSDATEOBJECT);
			Calendar calendar1 =new GregorianCalendar();	
			calendar1.setTime(currentDate);
			String year=String.valueOf(calendar1.get(Calendar.YEAR));
			if(!_fb.getYear().equals("")){
				year=_fb.getYear();
			}
			else{
				_fb.setYear(year);
			}
			essentialMap=HolidayEnquiryDATA.getHolidayList(year, getUserVO(_rq));
			WebUTIL.setMapInSession(essentialMap, _rq);
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

	/*public static void getFacilityDetail(HolidayEnquiryFB fb,HttpServletRequest request) {
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		HospitalFacilityMasterVO hospitalFacilityVO=new HospitalFacilityMasterVO();
		try{
			
			String facilityId=fb.getFacilityId();
			List hospitalFacilityMasterVOList=(ArrayList)session.getAttribute(enquiryConfig.HOSPITAL_FACILITY_MST_VO_LIST);
			for(int i=0;i<hospitalFacilityMasterVOList.size();i++){
				hospitalFacilityVO=(HospitalFacilityMasterVO)hospitalFacilityMasterVOList.get(i);
				if(facilityId.equals(hospitalFacilityVO.getFacilityId())){
					break;
				}	
			}
			String desc[]=hospitalFacilityVO.getFacilityDesc().split("#");
			fb.setDesc(desc);
			fb.setDescLen(String.valueOf(desc.length));
			WebUTIL.setAttributeInSession(request, enquiryConfig.HOSPITAL_FACILITY_MST_VO, hospitalFacilityVO);
			objStatus.add(Status.LIST);
			objStatus.add(Status.DONE);
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
	}*/

	
	
		
}

	


