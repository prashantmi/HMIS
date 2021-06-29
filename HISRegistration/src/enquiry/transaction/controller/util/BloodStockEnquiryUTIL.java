
package enquiry.transaction.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;

import enquiry.enquiryConfig;
import enquiry.transaction.controller.data.BloodStockEnquiryDATA;
import enquiry.transaction.controller.fb.BloodStockEnquiryFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.BloodStockEnquiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class BloodStockEnquiryUTIL extends ControllerUTIL{
	
	public static void getBloodStockEnquiry(BloodStockEnquiryFB _fb, HttpServletRequest _rq){
		HttpSession session= _rq.getSession();
		Status objStatus=new Status();
		Map BloodStockEnquiryMap=new LinkedHashMap();
		
		BloodStockEnquiryVO[] bloodStockEnquiry=null;
		UserVO _userVO=getUserVO(_rq);
		try{
			setSysdate(_rq);
			String componentName="";
			bloodStockEnquiry=BloodStockEnquiryDATA.getBloodStockEnquiry(_userVO,_fb.getChoice());
			for(int i=0;i<bloodStockEnquiry.length;i++)
			{
				List bloodStockEnquiryList=new ArrayList();
				if(bloodStockEnquiry[i].getComponentName()==null)
					bloodStockEnquiry[i].setComponentName("");
				
				componentName=bloodStockEnquiry[i].getComponentName();
				if(!BloodStockEnquiryMap.containsKey(componentName))
				{
					BloodStockEnquiryMap.put(componentName, bloodStockEnquiryList);
				}
				if(componentName.equals(bloodStockEnquiry[i].getComponentName()))
				{
					bloodStockEnquiryList=(ArrayList)BloodStockEnquiryMap.get(componentName);
					bloodStockEnquiryList.add( bloodStockEnquiry[i]);
					
					BloodStockEnquiryMap.put(componentName, bloodStockEnquiryList);
				}
			}
			
			WebUTIL.setAttributeInSession(_rq, enquiryConfig.ALL_COMPONENT_BLOOD_STOCK_MAP, BloodStockEnquiryMap);
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
