package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.InsuranceDetailVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;

public class InsuranceClaimRecordEntryDATA extends ControllerDATA
{
	public static Map getEssenForInsuranceClaimRecordEntry(UserVO userVO)
	{
//		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return null;//delegate.getEssenForInsuranceClaimRecordEntry(userVO); 
	} 
	
	public static void saveInsuranceClaimRecordEntry(InsuranceDetailVO insuranceDetailVO, UserVO userVO){
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveInsuranceClaimRecordEntry(insuranceDetailVO, userVO);
	}
}
