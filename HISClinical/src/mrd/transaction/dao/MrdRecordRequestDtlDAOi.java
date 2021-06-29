package mrd.transaction.dao;

import java.util.List;

import hisglobal.vo.MrdIcdDtlVO;
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.MrdRecordReturnDtlVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public interface MrdRecordRequestDtlDAOi 
{
	//Offline
	public void create(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,UserVO userVO);
	//icd index
	public void icdIndexDtlInsert(MrdIcdDtlVO mrdIcdVo,UserVO userVO,String mode);
	public void getIcdIndexDtl(MrdIcdDtlVO mrdIcdVo,UserVO userVO);
	
	//Online
	public void onlineInsert(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,UserVO userVO);
	
	public String generateRequestId(UserVO userVO);
	
	public void update(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,UserVO userVO);
	
	public void updateRejectDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,UserVO userVO);
	
	public void updateRequestStatus(MrdRecordIssueDtlVO mrdRecordIssueDtlVO,UserVO userVO);
	
	public List<MrdRecordRequestDtlVO> selectAllRecordByRequestStatus(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,UserVO userVO);
	
	public List<MrdRecordRequestDtlVO> selectRecordRequestForIssue(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,UserVO userVO);
	
	public List<MrdRecordRequestDtlVO> getRequestListForReturn(UserVO userVO);
	
	public List<MrdRecordRequestDtlVO> getPendingRequestListing(UserVO userVO);
	
	public void deleteRejectedRecordDetail(String requestId,UserVO userVO);

	public MrdRecordRequestDtlVO getLoginUserRequestByDetails(UserVO userVO);

	public void updateRequestStatus(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);
	
	public void updateRecordRequestStatus(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);

	public void updateForDays(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,
			UserVO userVO);

	public void setStatusIssueAgain(MrdRecordIssueDtlVO mrdRecordIssueDtlVO,
			UserVO userVO);

	public void updateExtension_SLNo(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);

	public void updateExtensionDetails(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);

	public void updateExtensionDetailsRejected(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);



	public void updateRequestStatusForReturn(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO,
			MrdRecordReturnDtlVO mrdRecordReturnDtlVO, UserVO userVO);
	
	
}
