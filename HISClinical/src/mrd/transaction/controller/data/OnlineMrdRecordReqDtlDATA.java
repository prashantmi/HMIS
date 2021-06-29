package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public class OnlineMrdRecordReqDtlDATA extends ControllerDATA
{
	public static Map getEssentialForOnlineReq(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getEssentialForOnlineReq(userVO);
	}
	
	public static void saveOnlineRequestDetail(MrdRecordRequestDtlVO mrdRecordRequestVO,List<RequestRecordDtlVO> lstRequestRecordVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveOnlineRequestDetail(mrdRecordRequestVO,lstRequestRecordVO,userVO);
	}

	public static Map getMrdNPurposeBasedOnRecordType(String recordType, UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getMrdNPurposeBasedOnRecordType(recordType,userVO);
	}
	
	public static List<RequestRecordDtlVO> getPendingRecordRequestStatus(String requestId,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getPendingRecordRequestStatus(requestId,userVO);
	}
	
	public static void deleteRejectedRecordDetail(String requestId,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.deleteRejectedRecordDetail(requestId,userVO);
	}

	public static void saveExtendDays(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO) {
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveExtendDays(mrdRecordRequestVO,userVO);
		
	}

	public static MrdRecordRequestDtlVO getEssentials(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO) {
		MrdDelegate delegate=new MrdDelegate();
		return(delegate.getEssentials(mrdRecordRequestVO,userVO));
		
	}

	public static void updateSL_NO(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO) {
		MrdDelegate delegate=new MrdDelegate();
		delegate.updateSL_NO(mrdRecordRequestVO,userVO);
		
	}
	
}
