package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.InPatientEnquiryVO;
import enquiry.vo.StaffEnquiryVO;

public class StaffEnquiryDATA extends ControllerDATA {
	
	public static Map getStaffEnquiryEssentials(UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getStaffEnquiryEssentials(_userVO);
	}
	public static StaffEnquiryVO[] searchStaffDetail(StaffEnquiryVO staffEnquiryVO,UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.searchStaffDetail(staffEnquiryVO,_userVO);
	}

}
