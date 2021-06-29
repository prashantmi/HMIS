package mrd.transaction.dao;

import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.UserVO;

public interface RecordDispatchDtlDAOi 
{
	/** Inserting Data In Case of ONLINE Certificate Received
	 * @param recordMoveDtlVO
	 * @param userVO
	 */
	public void create(RecordDispatchDtlVO dispatchVO,UserVO userVO);
	
	/** Updating The Receive From & Record Status of The Certificate  
	 * @param receiveFrom
	 * @param recordStatus
	 * @param recordId
	 * @param slNo
	 * @param userVO
	 */
	public void updateCertificateRecordStatus(String receiveFrom,String recordStatus,String recordId,String slNo,String recordType,UserVO userVO);
	
	/** Inserting Data In Case of OFFLINE Certificate Received
	 * @param recordMoveDtlVO
	 * @param userVO
	 */
	public void insertOfflineRecord(RecordDispatchDtlVO recordMoveDtlVO,UserVO userVO);
	
	public String generateDispatchId(UserVO userVO,String recordType);
	
	public void updateRecipentDtl(RecordDispatchDtlVO dispatchVO,UserVO userVO);
	
	public void updateRejectRecord(RecordDispatchDtlVO dispatchVO,UserVO userVO);
	
	public RecordDispatchDtlVO[] getRecordListToAcceptByRecordType(String recordType,UserVO userVO);
	

	public void updateRecordStatus(String dispatchId,String status,UserVO userVO);
	
	public void updateRecordForCasesheetHandover(RecordDispatchDtlVO dispatchVO,UserVO userVO);
	
	public void updateApprovalDetail(RecordDispatchDtlVO dispatchVO,UserVO userVO);
	
	public RecordDispatchDtlVO[] getRecordListToAcceptForOPDFile(String recordType,String searchDate,UserVO userVO);
	
}
