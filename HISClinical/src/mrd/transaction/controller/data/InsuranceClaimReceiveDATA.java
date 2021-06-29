package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.InsuranceDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class InsuranceClaimReceiveDATA extends ControllerDATA
{
	public static Map getEssenForInsuranceClaimReceive(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getEssenForInsuranceClaimReceive(userVO); 
	}
	
	public static List getPatInfoList(String patCrNo, String patAdmNo,String  _firstName,String  _middleName,String  _lastName,UserVO  userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getPatInfoList(patCrNo, patAdmNo, _firstName, _middleName, _lastName, userVO); 
	}
	
	public static void saveInsuranceDetail(InsuranceDetailVO insuranceDetailVO,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveInsuranceDetail(insuranceDetailVO, userVO); 
	}
}
