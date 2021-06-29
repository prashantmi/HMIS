package enquiry.transaction.controller.data;

import java.util.Map;

import enquiry.bo.delegate.EnquiryDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.UserVO;

public class SearchDeceasedEnquiryDATA extends ControllerDATA
{
	public static Map getEssentialForDeceasedEnquiry(UserVO userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getEssentialForDeceasedEnquiry(userVO);
	}
	
	public static DeceasedDetailVO[] searchDeceased(String fName,String mName,String lName,String genderCode,String fromDate,String toDate,String chkUnknown,String chkUnclaimed,UserVO userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.searchDeceased(fName,mName,lName,genderCode,fromDate,toDate,chkUnknown,chkUnclaimed,userVO);
	}
	
	public static Map getSearchDeceasedDtlByDeceaseNo(String deceasedNo,UserVO userVO)
	{
		EnquiryDelegate delegate=new EnquiryDelegate();
		return delegate.getSearchDeceasedDtlByDeceaseNo(deceasedNo,userVO);
	}
}
