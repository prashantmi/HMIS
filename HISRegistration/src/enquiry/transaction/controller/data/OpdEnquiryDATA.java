package enquiry.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;

import enquiry.bo.delegate.EnquiryDelegate;

public class OpdEnquiryDATA extends ControllerDATA {
	
	public static List getEssentials(UserVO _userVO){
		EssentialDelegate essDelegate=new EssentialDelegate();
		return essDelegate.getAllDepartments(_userVO);
	}

	public static List getDepartmentUnit(String _departmentCode,UserVO _userVO){
		EssentialDelegate essDelegate=new EssentialDelegate();
		return essDelegate.getUnitBasedOnDept(_departmentCode,_userVO);
	}
	
	public static OpdEnquiryVO[] getSearchDetail(OpdEnquiryVO _opdEnquiryVO,UserVO _userVO){
		EnquiryDelegate essDelegate=new EnquiryDelegate();
		return essDelegate.getOpdEnquirySearchDetail(_opdEnquiryVO,_userVO);
	}
	
	public static OpdEnquiryVO[] getAllConsulatantDetailsForUnit(String _deptUnitCode,UserVO _userVO){
		EnquiryDelegate essDelegate=new EnquiryDelegate();
		return essDelegate.getAllConsulatantDetailsForUnit(_deptUnitCode,_userVO);
	}

}
