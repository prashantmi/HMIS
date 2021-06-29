package mrd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdDelegate;
import mrd.transaction.delegate.MrdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

public class CertificateAcceptDATA extends ControllerDATA
{
	public static RecordDispatchDtlVO[] getRecordListToAcceptByRecordType(String recordType,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getRecordListToAcceptByRecordType(recordType,userVO);
	}
	
	public static Map getEssentialForAcceptRecordInMrd(String recordType,String mrdCode,String searchDate,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getEssentialForAcceptRecordInMrd(recordType, mrdCode,searchDate,userVO);
	}
	
	public static RackMstVO[] getRackBasedOnMrd(String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getRackBasedOnMrd(recordType,mrdCode,userVO);
	}
	
	public static List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getShelfBasedOnRack(recordType,rackId,userVO);
	}
	
	public static void saveRecordAccepted(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveRecordAccepted(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,userVO);
	}
	
	public static void saveRecordArchived(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveRecordArchived(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,userVO);
	}
	
	public static void saveRecordLost(List<RecordLostFoundDtlVO> lstLostRecord,UserVO userVO)
	{
		MrdDelegate  mrdDelegate= new MrdDelegate();
		mrdDelegate.saveRecordLost(lstLostRecord,userVO);
	}
	
	public static List<RecordTypeWiseEnclosureMstVO> getEnclosureDetail(String dispatchId,String recordType,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getEnclosureDetail(dispatchId,recordType,userVO);
	}
	
	public static Map getCheckListDetail(String dispatchId,String recordType,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getCheckListDetail(dispatchId,recordType,userVO);
	}
	
	//Added by Dheeraj on 05-Nov-2018
	public static List getStaffMembers(String recordType,String rackId,UserVO userVO)
	{
		MrdEssentialDelegate essDeleagte=new MrdEssentialDelegate();
		return essDeleagte.getShelfBasedOnRack(recordType,rackId,userVO);
	}
	
}
