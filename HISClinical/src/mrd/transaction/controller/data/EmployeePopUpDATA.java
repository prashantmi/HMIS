package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.StaffDetailVO;
import hisglobal.vo.UserVO;

public class EmployeePopUpDATA extends ControllerDATA {

	public static StaffDetailVO[] searchStaffDetail(StaffDetailVO staffEnquiryVO, UserVO _uservo) {
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.searchStaffDetail(staffEnquiryVO,_uservo);
	}

	public static Map getEmployeePopUpEssentials(UserVO _uservo) {
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getEmployeePopUpEssentials(_uservo);
	}

}
