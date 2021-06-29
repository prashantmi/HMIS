package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordMovementVO;
import hisglobal.vo.UserVO;

public class OPDFileMovementDATA extends ControllerDATA {

	public static Map getListOfOpdFilesToMove(String _mrdCode,UserVO _userVO) {
		MrdDelegate mrdDelegate=new MrdDelegate();
		return mrdDelegate.getListOfOpdFilesToMove(_mrdCode,_userVO);
	}

	public static void saveOPDFileMovementDetail(MrdRecordDtlVO[] _mrdRecordVO,MrdRecordMovementVO[] _movementVO, UserVO _userVO) {
		MrdDelegate mrdDelegate=new MrdDelegate();
		mrdDelegate.saveOPDFileMovementDetail(_mrdRecordVO,_movementVO,_userVO);
		
	}

}
