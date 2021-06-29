package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;

public class CertificateLostFoundDATA extends ControllerDATA
{
	public static Map getEssentialForLostFoundDetail(String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialDelegate mrdEssDelegate=new MrdEssentialDelegate();
		return mrdEssDelegate.getEssentialForLostFoundDetail(recordType,mrdCode,userVO);
	}
	
	public static List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getShelfBasedOnRack(recordType,rackId,userVO);
	}
	
	public static void saveFoundDetailNArchivedInMrd(List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordLostFoundDtlVO> lstFoundRecordVO,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		MrdDelegate delegate=new MrdDelegate();
		delegate.saveFoundDetailNArchivedInMrd(lstEnclosure,lstMrdRecordVO,lstFoundRecordVO,lstCheckList,lstRecordEnclosureDtl,userVO);
	}
	
}
