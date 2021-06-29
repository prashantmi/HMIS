package medicalboard.transactions.controller.data;

import hisglobal.vo.MedicalBoardRequisitionChangeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;

public class PostMedicalEntryDATA
{

	public static Map getMBPostEntryEssential(UserVO userVO)
	{
		MbTransEssentialDelegate mEssentialDelegate = new MbTransEssentialDelegate();
		return mEssentialDelegate.getMBPostEntryEssential(userVO);
	}

	public static List getCandidateList(String certificateTypeID, String boardNo, String fromDate, String toDate, UserVO userVO)
	{
		MbTransEssentialDelegate mEssentialDelegate = new MbTransEssentialDelegate();
		return mEssentialDelegate.getCandidateListForPostEntry(certificateTypeID, boardNo, fromDate, toDate, userVO);
	}

	public static Map getCandidateDetailForPostEntry(MedicalBoardRequisitionVO requisitionVO, UserVO userVO)
	{
		MbTransEssentialDelegate mEssentialDelegate = new MbTransEssentialDelegate();
		return mEssentialDelegate.getCandidateDetailForPostEntry(requisitionVO, userVO);
	}

	public static void savePostEntryDetail(MedicalBoardRequisitionVO requisitionVO, MedicalBoardRequisitionChangeVO reqChangeVO,
			String isMedicalPerformed, List checklistVOList, List verificationVOList, UserVO userVO)
	{
		MbTransEssentialDelegate mEssentialDelegate = new MbTransEssentialDelegate();
		mEssentialDelegate.savePostEntryDetail(requisitionVO, reqChangeVO, isMedicalPerformed, checklistVOList, verificationVOList, userVO);

	}

	public static String getScanFlag(MedicalBoardRequisitionVO requisitionVO, UserVO userVO)
	{
		MbTransEssentialDelegate mEssentialDelegate = new MbTransEssentialDelegate();
		String isDocPresent = mEssentialDelegate.getScanFlag(requisitionVO, userVO);
		return (isDocPresent);
	}

	public static String getLastCertificateNo(String strCertTypeId, UserVO userVO) 
	{
		MbTransEssentialDelegate mEssentialDelegate = new MbTransEssentialDelegate();
		return mEssentialDelegate.getLastCertificateNo(strCertTypeId, userVO);
	}

	public static List getBoardList(String certificateTypeID, UserVO userVO)
	{
		MbTransEssentialDelegate mEssentialDelegate = new MbTransEssentialDelegate();
		return mEssentialDelegate.getBoardListForPostEntry(certificateTypeID, userVO);
	}

}
