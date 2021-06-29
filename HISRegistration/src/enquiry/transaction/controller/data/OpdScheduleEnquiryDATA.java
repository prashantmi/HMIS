package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.InPatientEnquiryVO;

public class OpdScheduleEnquiryDATA extends ControllerDATA {
	
	public static Map getOpdScheduleEnquiryEssentials(UserVO _userVO){
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getOpdScheduleEnquiryEssentials(_userVO);
	}

	public static Map getUnitWorkingDetail(String departmentCode, UserVO _uservo) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getUnitWorkingDetail(departmentCode,_uservo);
	}

	public static Map getSpecialClinicWorkingDetail(String departmentUnitCode,
			UserVO _uservo) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.getSpecialClinicWorkingDetail(departmentUnitCode,_uservo);
	}
	
	
}
