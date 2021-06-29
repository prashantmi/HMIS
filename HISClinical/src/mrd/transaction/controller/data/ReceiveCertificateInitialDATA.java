package mrd.transaction.controller.data;

import java.util.List;

import mrd.transaction.delegate.MrdEssentialDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class ReceiveCertificateInitialDATA extends ControllerDATA
{
	public static List getRecordTypeBasedOnMrd(String mrdCode,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getRecordTypeBasedOnMrd(mrdCode,userVO);
	}
	
	public static List getMrdList(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getMrdList(userVO);
	}
}
