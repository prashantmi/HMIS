package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class InsuranceEnquiryDATA extends ControllerDATA
{
	public static Map getEssenForInsuranceEnquiry(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getEssenForInsuranceEnquiry(userVO); 
	}

	public static List searchInsuranceDtl(String patFirstName,
			String patMiddleName, String patLastName, String companyId,
			String patCrNo, String policyNo, UserVO userVO) {
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.searchInsuranceDtl(patFirstName,patMiddleName,patLastName,companyId,patCrNo,policyNo,userVO); 
	} 
}
