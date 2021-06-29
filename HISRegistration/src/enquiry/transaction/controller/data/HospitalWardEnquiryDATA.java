package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;
import enquiry.vo.HospitalDepartmentEnquiryVO;

public class HospitalWardEnquiryDATA extends ControllerDATA {
	
	public static Map getWardEnquiryEssential(UserVO _userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getWardEnquiryEssential(_userVO);
	}
	
	/*public static HospitalDepartmentEnquiryVO[] getDepartmentEnquiryDetail(String _deptCode,String _deptTypeCode,UserVO _userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getDepartmentEnquiryDetail(_deptCode,_deptTypeCode,_userVO);
	}
	
	public static Map getDepartmentUnitEnquiryDetail(String _deptUnitCode,UserVO _userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getDepartmentUnitEnquiryDetail(_deptUnitCode,_userVO);
	}*/

	public static List getWardEnquiryDetail(String wardCode,UserVO _userVO) {
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getWardEnquiryDetail(wardCode,_userVO);
	}

}
