package medicalboard.transactions.controller.data;

import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.MedicalBoardVisitDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;


public class MbCandidateAcceptanceDATA {

	public static List getCertificateTypeList(UserVO userVO,String date)
		{
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getCertificateTypeList(userVO, date);
				
		}

	public static List getCandidateList(
			String certificateTypeID, UserVO userVO,String examDate) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getCandidateList(certificateTypeID,userVO,examDate);
	}
	
	public static Map getCandidateAcceptanceEssential(
			MedicalBoardRequisitionVO requisitionVO, UserVO userVO,String examDate) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getCandidateAcceptanceEssential(requisitionVO,userVO,examDate);
	}

	public static void saveCandidateAcceptance(
			List checklistVOList,
			MedicalBoardRequisitionVO requisitionVO, EpisodeVO episodeVO,
			PatientVO patientVO, MedicalBoardVisitDtlVO visitDetailVO, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		mEssentialDelegate.saveCandidateAcceptance(checklistVOList,requisitionVO,episodeVO,patientVO,visitDetailVO,userVO);
		
	}	
	public static List getPrevCandidateList(
			String certificateTypeID, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getPrevCandidateList(certificateTypeID,userVO);
	}
		
}
