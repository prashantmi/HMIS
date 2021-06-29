package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordMovementVO;
import hisglobal.vo.UserVO;

public class OPDFileReturnDATA extends ControllerDATA {

	public static Map getListOfOpdFilesToReturn(String mrdCode,UserVO userVO) {
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getListOfOpdFilesToReturn(mrdCode,userVO);
	}

	public static void saveOPDFileReturnDetail(MrdRecordDtlVO[] mrdRecordVOForUpdate,MrdRecordMovementVO[] mrdRecordMovementVO, UserVO userVO) {
		MrdDelegate mrdDelegate=new MrdDelegate();
		mrdDelegate.saveOPDFileMovementDetail(mrdRecordVOForUpdate, mrdRecordMovementVO, userVO);
		
	}

}
