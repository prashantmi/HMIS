package mrd.masters.dao;

import java.util.List;

import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.UserVO;

public interface RecordTypeCheckListDAOi 
{
	public RecordTypeCheckListMstVO[] getCheckListForMedNFitCertificate(String checkListMode,String recordType,UserVO userVO);
	
	public List getAllAddedCheckLstForRecordType(String recordType, String checkListMode,UserVO _userVO);
	
	public List getCheckListNotMapped(RecordTypeCheckListMstVO recordTypeCheckListVO,UserVO userVO);
	
	public void update(RecordTypeCheckListMstVO recordTypeCheckListVO ,UserVO userVO);
	
	public void creat(RecordTypeCheckListMstVO recordTypeCheckListVO ,UserVO userVO);
}
