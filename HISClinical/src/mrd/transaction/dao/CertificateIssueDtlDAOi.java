package mrd.transaction.dao;

import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.UserVO;

public interface CertificateIssueDtlDAOi
{
	/** Saving the Issue Certificate Detail
	 * @param certificateIssueDtlVO
	 * @param userVO
	 */
	public void create(CertificateIssueDtlVO certificateIssueDtlVO,UserVO userVO);
	
	/** Updating The Record Status of The Certificate 
	 * @param recordStatus
	 * @param recordId
	 * @param slNo
	 * @param userVO
	 */
	public void updateCertificateRecordStatus(String recordStatus,String recordId,String slNo,String recordType,UserVO userVO);
	
}
