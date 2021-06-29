package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

//import enquiry.bo.delegate.EnquiryDelegate;
//import enquiry.vo.StaffEnquiryVO;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

public class SummonAssignmentDetailDATA extends ControllerDATA
{
	public static Map getEssenForSummonAssignDtl(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getEssenForSummonAssignDtl(userVO); 
	}
	
	public static List searchEmpDetail(String _fName,String _mName,String _lName,UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.searchEmpDetail(_fName,_mName,_lName,userVO); 
	}
	
	public static void saveSummonAssigmentDetail(SummonDetailVO summonDetailVO,UserVO userVO)
	{
		MrdDelegate delegate= new MrdDelegate();
		delegate.saveSummonAssigmentDetail(summonDetailVO,userVO); 
	}
	
	/*public static StaffEnquiryVO[] searchStaffDetail(StaffEnquiryVO staffEnquiryVO, UserVO _uservo) {
		EnquiryDelegate enqDelegate=new EnquiryDelegate();
		return enqDelegate.searchStaffDetail(staffEnquiryVO,_uservo);
	}*/
	
	
}
