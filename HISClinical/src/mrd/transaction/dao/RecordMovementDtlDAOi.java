package mrd.transaction.dao;

import hisglobal.vo.RecordMovementDtlVO;
import hisglobal.vo.UserVO;

public interface RecordMovementDtlDAOi 
{
	/** Inserting Data In Case of ONLINE Certificate Received
	 * @param recordMoveDtlVO
	 * @param userVO
	 */
	public void create(RecordMovementDtlVO recordMoveDtlVO,UserVO userVO);
	
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
	public void insertOfflineRecord(RecordMovementDtlVO recordMoveDtlVO,UserVO userVO);
	
}
