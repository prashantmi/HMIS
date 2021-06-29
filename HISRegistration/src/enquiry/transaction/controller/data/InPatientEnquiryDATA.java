package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;


import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.InPatientEnquiryVO;

public class InPatientEnquiryDATA extends ControllerDATA {
	
	public static Map getPatientEnquiryEssentials(UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getPatientEnquiryEssentials(_userVO);
	}
	
	public static CommonEnquiryVO[] searchInPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.searchInPatientDetails(_patEnquiryVO,_addEnquiryVO,_episodeEnquiryVO,_userVO);
	}
	
	public static Map getInPatientDetail(String patientCRNo,UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getPatientDetail(patientCRNo,_userVO);
	}
	
	public static List getDepartmentUnit(String _departmentCode,UserVO _userVO){
		EnquiryDelegate essDelegate=new EnquiryDelegate();
		return essDelegate.getUnitBasedOnDept(_departmentCode,_userVO);
	}
	
	public static List getDepartmentWard(String _departmentCode,UserVO _userVO){
		EnquiryDelegate essDelegate=new EnquiryDelegate();
		return essDelegate.getWardBasedOndept(_departmentCode,_userVO);	}
	
}
