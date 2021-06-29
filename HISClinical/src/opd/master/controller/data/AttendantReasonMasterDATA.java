package opd.master.controller.data;

import hisglobal.vo.UserVO;

import hisglobal.vo.AttendantReasonMasterVO;

import opd.bo.delegate.OpdMasterDelegate;

public class AttendantReasonMasterDATA
{
	// Saving Addendant Reason Record
	public static void saveRecord(AttendantReasonMasterVO _attReasonVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveAttendantReason(_attReasonVO, _UserVO);
	}

	// Getting Addendant Reason Record
	public static AttendantReasonMasterVO getRecord(AttendantReasonMasterVO _attReasonVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getAttendantReason(_attReasonVO, _userVO);
	}

	// Modifying Addendant Reason Record
	public static void updateAttendantReason(AttendantReasonMasterVO _attReasonVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.updateAttendantReason(_attReasonVO, _UserVO);
	}
}
