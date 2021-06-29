package mrd.masters.controller.data;

import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.masters.delegate.MrdMasterDelegate;

public class RecordTypeWiseEnclosureDATA  
{
	
	public static void saveEnclosureRecord(List<RecordTypeWiseEnclosureMstVO> lstEnclosure, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		masterDelegate.saveEnclosureRecord(lstEnclosure, _UserVO);
	}
	
	public static RecordTypeWiseEnclosureMstVO fetchEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return (masterDelegate.fetchEnclosureRecord(_RecordTypeWiseEnclosureMstVO, _UserVO));
	}
	
	public static String getRecordTypeName(String recordTypeId,UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		String recordTypeName  = masterDelegate.getRecordTypeName(recordTypeId,_UserVO);
		return recordTypeName;
	}
	
	public static boolean modifyEnclosureRecord(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVO, UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag = masterDelegate.modifyEnclosureRecord(_RecordTypeWiseEnclosureMstVO, _UserVO);
		return hasFlag;
	}
	
	public static Map getEssentials(UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEssentialsForRecordTypeWiseEnclosure(userVO);
	}
	public static Map getRackShelfDetail(String rackId, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getRackShelfDetail(rackId,userVO);
	}
	
	public static Map getEssentialForEnclosureMapping(String recordTypeId,UserVO userVO)		
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEssentialForEnclosureMapping(recordTypeId,userVO);
	}
	
	public static List getEnclosureRecordListNotMapped(String recordTypeId,UserVO userVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEnclosureRecordListNotMapped(recordTypeId,userVO);
	}
}
