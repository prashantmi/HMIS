package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRackShelfChangeDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.MrdRecordReturnDtlVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public class MrdRecordReturnDATA extends ControllerDATA
{
	public static List<MrdRecordRequestDtlVO> getRequestListForReturn(UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getRequestListForReturn(userVO);
	}
	
	public static List<MrdRecordIssueDtlVO> getReturnedMrdRecordListByRequestId(String requestId,String recordId, UserVO userVO)
	{
		MrdEssentialDelegate delegate= new MrdEssentialDelegate();
		return delegate.getReturnedMrdRecordListByRequestId(requestId,recordId,userVO);
	}
	
	public static void saveMrdRecordReturnDetail(List<MrdRecordReturnDtlVO> mrdRecordReturnDtlList,List<MrdRecordDtlVO> mrdRecordDtlVOList,String isRequestReturn,List<MrdRackShelfChangeDtlVO> mrdRackShelfChangeList,UserVO userVO)
	{
		MrdDelegate mrdDelegate=new MrdDelegate();
		mrdDelegate.saveMrdRecordReturnDetail(mrdRecordReturnDtlList,mrdRecordDtlVOList,isRequestReturn,mrdRackShelfChangeList,userVO); 
	}
	
	public static Map getArchivalDetail(String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getReturnEssentialForRecordArchived(recordType,mrdCode,userVO);
	}
	
	public static List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getShelfBasedOnRack(recordType,rackId,userVO);
	}
}
