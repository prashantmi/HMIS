package medicalboard.transactions.controller.data;

import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MedicalBoardExternalReferVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.MedicalBoardVisitDtlVO;
import hisglobal.vo.Test;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;


public class MedicalExamInitiationDATA {

	public static List getCertificateTypeList(UserVO userVO)
	{
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getCertificateTypeListForMedicalExam(userVO);
			
	}

	public static Map getCandidateList(
			String certificateTypeID, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getCandidateListForMedicalExam(certificateTypeID,userVO);
	}

	public static Map getReferMappingList(
			String certificateTypeID, EpisodeRefDtlVO episodeRefDtlVO, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getReferMappingList(certificateTypeID,episodeRefDtlVO,userVO);
	}

	public static void saveCandidateReferDetail(EpisodeRefDtlVO[] episodeRefVO,
			EpisodeVO[] episodeVO, MedicalBoardVisitDtlVO[] visitDetailVO, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		mEssentialDelegate.saveCandidateReferDetail(episodeRefVO,episodeVO,visitDetailVO,userVO);
		
	}

	public static List getMBCandidateRefDetail(String patCrNo,
			UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getMBCandidateRefDetail(patCrNo,userVO);
	}

	public static Map getMBInvestigationEssential(MedicalBoardRequisitionVO[] requisitionVOArray, String certificateTypeID,
			UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getMBInvestigationEssential(requisitionVOArray,certificateTypeID,userVO);
	}

	public static void raiseCandidateInvestigation(
			List patientDtlVOList, EpisodeVO[] episodeVO,
			List<Test> testVOList, MedicalBoardVisitDtlVO[] visitDetailVO,
			String sysdate, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		mEssentialDelegate.raiseCandidateInvestigation(patientDtlVOList,episodeVO,testVOList,visitDetailVO,sysdate,userVO);
		
	}

	public static List getMBCandidateInvDetail(
			EpisodeVO episodeVO, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getMBCandidateInvDetail(episodeVO,userVO);
	}

	public static void saveExternalRefer(MedicalBoardExternalReferVO[] externalReferVO, UserVO userVO) {
		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		mEssentialDelegate.saveExternalRefer(externalReferVO,userVO);
		
	}

	public static List<MedicalBoardExternalReferVO> getMBCandidateExtRefDetail(	String reqID, UserVO userVO) {

		MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
		return mEssentialDelegate.getMBCandidateExtRefDetail(reqID,userVO);
	
	}	
		
		
}
