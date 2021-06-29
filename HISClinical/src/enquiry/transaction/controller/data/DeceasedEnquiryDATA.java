package enquiry.transaction.controller.data;

import java.util.List;
import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.UserVO;

public class DeceasedEnquiryDATA extends ControllerDATA
{
	public static List<DeceasedDetailVO> getAllDeceasedListInMortuary(UserVO userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getAllDeceasedListInMortuary(userVO);
	}
	
	public static Map getDeceasedDtlByDeceaseNo(String deceasedNo,UserVO userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getDeceasedDtlByDeceaseNo(deceasedNo,userVO);
	}
	
}
