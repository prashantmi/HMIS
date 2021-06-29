package enquiry.transaction.controller.data;

import java.util.Map;


import enquiry.bo.delegate.EnquiryDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;

import hisglobal.vo.UserVO;

public class PatientEnquiryDATA extends ControllerDATA {
	
	public static Map getPatientEnquiryEssentials(UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getPatientEnquiryEssentials(_userVO);
	}
	
	public static CommonEnquiryVO[] searchPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.searchPatientDetails(_patEnquiryVO,_addEnquiryVO,_episodeEnquiryVO,_userVO);
	}
}
