package mrd.transaction.controller.data;

import java.util.Map;

import mrd.transaction.bo.MrdEssentialBO;
import mrd.vo.DupRecPrintReqVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class DuplicateRecordPrintReqDATA extends ControllerDATA {

	public static Map getEssentialData(UserVO userVO)
	{
		DupRecPrintReqVO dupRecPrintReqVO=new DupRecPrintReqVO();
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		return serviceBO.getEssential(dupRecPrintReqVO,userVO);
	}
	
	public static void saveDuplicateRecordPrintReqDtl(mrd.vo.DupRecPrintReqVO dupRecPrintReqVO, UserVO UserVO)
	{
		MrdEssentialBO serviceBO=new MrdEssentialBO();	
		serviceBO.saveDuplicateRecordPrintReqDtl(dupRecPrintReqVO,UserVO);
	}
	
}
