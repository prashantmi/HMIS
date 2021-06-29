package medicalboard.transactions.controller.data;

import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;
import medicalboard.transactions.delegate.MbTransactionDelegate;

public class CertificationVerificationDATA 
{
	public static Map getMBCertVerificationEssential(UserVO userVO) 
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getMBCertVerificationEssential(userVO);
	}
	
	public static List getCandidateList(
			String certificateTypeID, String boardNo, String fromDate, String toDate, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getCandidateListForCertVerification(certificateTypeID,boardNo,fromDate,toDate,userVO);
	}
	
	public static Map getMBBoardMemberOpinionDetail(MedicalBoardRequisitionVO requisitionVO,UserVO userVO) 
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getMBBoardMemberOpinionDetail(requisitionVO,userVO);
	}
	
	public static void saveCertVerificationDetail(MedicalBoardRequisitionVO requisitionVO,UserVO userVO)
	{
		MbTransactionDelegate mbDelegate=new MbTransactionDelegate();
		mbDelegate.saveCertVerificationDetail(requisitionVO,userVO);
	}

}
