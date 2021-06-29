package mrd.transaction.dao;

import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.UserVO;

public interface MRDRecordDtlDAOi 
{
	/** Inserting Data
	 * @param mrdRecordDtlVO
	 * @param userVO
	 */
	public void create(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);
	
	public String generateMrdRecordId(UserVO userVO,String recordType);
	
	public void insertRecordAcceptInMrd(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);
	
	public MrdRecordDtlVO[] getRecordListToArchivedByRecordType(String recordType,String mrdCode,UserVO userVO);
	
	public MrdRecordDtlVO[] getRecordListToArchivedByAdmNo(UserVO userVO,String admNo);//added by swati on date:10-may-2019
	
	public MrdRecordDtlVO[] getRecordListToArchivedByCrNo(UserVO userVO,String admNo,String crno);//added by swati on date:10-may-2019
	
	public void updateRecordArchivalDetail(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);
	
	public void updateRecordStatus(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);
	
	public void updateReturnRecordArchived(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);
	
	public void updateReturnRecordInMrd(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);
	
	public MrdRecordDtlVO[] getMrdRecordBasedOnShelfList(String recordType,String shelfId,UserVO userVO);
	
	public void updateRecordMovementDetail(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);

	public MrdRecordDtlVO[] getListOfOpdFilesToMove(String _mrdCode, UserVO _userVO);
	
	public void updateRecordStatusAndIssueFlag(MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);
	
	public MrdRecordDtlVO[] getListOfOpdFilesToReturn(String _mrdCode, UserVO _userVO);

	public void destroyRecordDetail(MrdRecordDtlVO mrdRecordDtlVO, UserVO userVO);

	public void saveExtendDays(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO);

	public MrdRecordRequestDtlVO getEssentials(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO);

	public void updateSL_NO(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO);
	
}
