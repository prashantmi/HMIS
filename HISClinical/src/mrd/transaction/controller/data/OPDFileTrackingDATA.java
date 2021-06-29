package mrd.transaction.controller.data;

import java.util.List;

import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class OPDFileTrackingDATA extends ControllerDATA {
	
	public static List getMrdBasedOnRecordType(String recordType,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getMrdBasedOnRecordType(recordType,userVO);
	}

}
