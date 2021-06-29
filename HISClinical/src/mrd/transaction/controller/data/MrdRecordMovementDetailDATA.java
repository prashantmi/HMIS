package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRackShelfChangeDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.UserVO;

public class MrdRecordMovementDetailDATA extends ControllerDATA
{
	public static Map getEssentialForRecordMovement(String recordType,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getEssentialForRecordMovement(recordType, userVO);
	}
	public static List getAllRecordTypeList(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getRecordType(userVO);
	}
	
	/*public static List getMrdBasedOnRecordTypeList(String recordType,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getMrdBasedOnRecordType(recordType, userVO);
	}*/
	
	public static RackMstVO[] getRackBasedOnMrdList(String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getRackBasedOnMrd(recordType, mrdCode, userVO);
	}
	
	public static List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getShelfBasedOnRack(recordType, rackId, userVO);
	}
	
	public static MrdRecordDtlVO[] getMrdRecordBasedOnShelfList(String recordType,String shelfId,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		return delegate.getMrdRecordBasedOnShelfList(recordType,shelfId,userVO);
	}
	
	public static void saveRecordMovementDetail(List<MrdRackShelfChangeDtlVO> lstMrdRackShelfChangeVO,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveRecordMovementDetail(lstMrdRackShelfChangeVO,userVO);
	}
	
	
}
