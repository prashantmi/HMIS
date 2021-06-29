package opd.master.dao;

import hisglobal.vo.AttendantReasonMasterVO;
import hisglobal.vo.UserVO;

public interface AttendantReasonMasterDAOi
{
	// Saving Attendant Reason Record 
	public void create(AttendantReasonMasterVO _attReasonVO, UserVO _userVO);

	// Checking Duplicate Reason
	public boolean checkDuplicate(AttendantReasonMasterVO _attReasonVO, UserVO _userVO);

	// Getting Detail of Attendant Reason Record
	public AttendantReasonMasterVO get(AttendantReasonMasterVO _attReasonVO, UserVO _userVO);

	// Updating the Attendant Reason
	public void update(AttendantReasonMasterVO _attReasonVO, UserVO _userVO);

	// Saving Updated Attendant Reason
	public void createUpdate(AttendantReasonMasterVO _attReasonVO, UserVO _userVO);

	// Checking Duplicate Reason for Modification
	public boolean checkDuplicateforModify(AttendantReasonMasterVO _attReasonVO, UserVO _userVO);
}
