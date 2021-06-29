package medicalboard.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;


public class MedicalBoardRequisitionChangeDATA {

		public static List getPatientMbRequisitionsByCrNo(
				String patCrNo, UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getPatientMbRequisitionsByCrNo(patCrNo,userVO);
		}

		public static void saveMedicalBoardRequisitionChange(
				List requisitionVOList,
				List requisitionChangeVOList,
				UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			mEssentialDelegate.saveMedicalBoardRequisitionChange(requisitionVOList,requisitionChangeVOList,userVO);
			
		}

		public static List getOrganisationsList(
				UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getOrganisationName(userVO);
		}

		public static List getMbRequisitionsByOrg(String orgId, String orgName, UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getMbRequisitionsByOrg(orgId,orgName,userVO);
		}	
		
		
}
