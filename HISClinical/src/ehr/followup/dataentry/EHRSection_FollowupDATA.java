package ehr.followup.dataentry;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import ehr.followup.vo.EHRSection_FollowupVO;

public class EHRSection_FollowupDATA extends  ControllerDATA{

	public static Map getEssentials(EHRSection_FollowupVO followVO,
			UserVO userVO) {
		EHRSection_FollowupBO serviceBO = new EHRSection_FollowupBO();
		return serviceBO.getEssentials(followVO, userVO);
	}

	

	public static void saveEhrFollowupdetails(EHRSection_FollowupVO followVO,
			UserVO userVO, String deskType) {
		EHRSection_FollowupBO serviceBO = new EHRSection_FollowupBO();
		 serviceBO.saveEhrFollowupdetails(followVO, userVO, deskType);
		
	}
	
	/**Added by Vasu on 18.Dec.2018 for Discharge FollowUP*/
	public static Map getDischargeStatusListNProfileStatus(PatientDetailVO patientVO,UserVO userVO)
	{
		EHRSection_FollowupBO serviceBO = new EHRSection_FollowupBO();
		return serviceBO.getDischargeStatusListNProfileStatus(patientVO, userVO);
	}
	
	public static EHRSection_FollowupVO getDischargeRemarks(String admNo,UserVO userVO)
	{
		EHRSection_FollowupBO serviceBO = new EHRSection_FollowupBO();
		return serviceBO.getDischargeRemarks(admNo,userVO); 
	}


public static boolean getPatientStatus(String admNo,UserVO userVO)
	{
	    EHRSection_FollowupBO serviceBO = new EHRSection_FollowupBO();
		return serviceBO.getPatientStatus(admNo,userVO); 
	}

   //Added by Vasu on 03.Jan.2019
   public static void saveDischargeFollowUpDetails(EHRSection_FollowupVO followVO, UserVO userVO, String deskType) 
    {
	   EHRSection_FollowupBO serviceBO = new EHRSection_FollowupBO();
	   serviceBO.saveDischargeFollowUpDetails(followVO, userVO, deskType);
	
    }
   
   public static EHRSection_FollowupVO getDischargeFollowUpDetails(EHRSection_FollowupVO followVO, UserVO userVO) 
   {
	   EHRSection_FollowupBO serviceBO = new EHRSection_FollowupBO();
	   //System.out.println("finall3");
	   serviceBO.getDischargeFollowUpDetails(followVO, userVO);
	   return followVO;
   }

}
