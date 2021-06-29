package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.UserVO;

public class RecordFoundInMrdDATA extends ControllerDATA
{
	public static MrdRecordLostFoundDtlVO[] getLostRecordInMrdList(UserVO userVO)
	{
		MrdEssentialDelegate essDelegate=new MrdEssentialDelegate();
		return essDelegate.getLostRecordInMrdList(userVO);
	}
	
	public static Map getFoundEssentialDtl(String selRecordId,String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getFoundEssentialDtl(selRecordId,recordType,mrdCode,userVO);
	}
	
	public static List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getShelfBasedOnRack(recordType,rackId,userVO);
	}
	
	public static void saveRecordFoundNArchivalDetail(MrdRecordLostFoundDtlVO mrdFoundVO,MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		MrdDelegate delegte=new MrdDelegate();
		delegte.saveRecordFoundNArchivalDetail(mrdFoundVO,mrdRecordDtlVO,userVO);
	}
}
