package registration.transactions.controller.data;

import hisglobal.presentation.ControllerDATA;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hisglobal.vo.UserVO;
import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.transactions.controller.actionsupport.AppointmentCancellationSUP;
import vo.appointment.AppointmentVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;

public class AppointmentCancellationDATA extends ControllerDATA
{
	public static PatientVO[] getCRNoWithAppointment(UserVO _UserVO,String episodeVisitType){	
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		return  essentialBO.getCRNoWithAppointment(_UserVO,episodeVisitType);
	    		
	}
	
	public static PatientVO[] searchdetailsWithAppointment(PatientVO _patVO,UserVO _UserVO){	
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		return  essentialBO.searchSpecialCRNoWithAppointment(_patVO,_UserVO);
	    		
	}	
	public static  boolean saveaptcancellationdtl (AppointmentVO _aptVo,UserVO _UserVO)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();			
		return  essentialBO.saveAptCancellationDtl(_aptVo, _UserVO);
	}
}
