package medicalboard.transactions.controller.data;

import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardBillingVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import medicalboard.transactions.delegate.MbTransEssentialDelegate;


public class MedicalBoardRequisitionDATA {

		public static List getPatientListForRequisition(UserVO _UserVO)
		{
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getPatientListForRequisition(_UserVO);
				
		}
		
		public static Map getCertificateTypeForRequisition(UserVO _UserVO)
		{
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getCertificateTypeForRequisition(_UserVO);
			
		}
		
		public static Map getCheckList(String certificateType, UserVO _UserVO)
		{
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			Map mp=mEssentialDelegate.getCheckList(certificateType,_UserVO);
			return mp;		
		}
		
		public static MbOrganizationMstVO getOrganizationDetail(String orgId,UserVO _UserVO)
		{
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return(mEssentialDelegate.getOrganizationDetail(orgId,_UserVO));
					
		}
		
		public static void saveMedicalBoardRequisition(
				List lstCheckList,
				MedicalBoardRequisitionVO requisitionVO, MedicalBoardBillingVO billingVO, UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			mEssentialDelegate.saveMedicalBoardRequisition(lstCheckList,requisitionVO,billingVO,userVO);
		}

		public static Map getEssentialForRequisition(MbCertificateTypeMstVO certificateTypeMstVO, String patCrNo, UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getEssentialForRequisition(certificateTypeMstVO,patCrNo,userVO);
				
		}

		public static List getScheduleList(
				MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getScheduleListForRequisition(certificateTypeMstVO,userVO);
		}

		public static List getCIDNoList(
				MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getCIDNoListForRequisition(certificateTypeMstVO,userVO);
		}

		public static PatientVO getPatientForRequisitionByCrNo(String patCrNo,
				UserVO userVO) {
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			return mEssentialDelegate.getPatientForRequisitionByCrNo(patCrNo,userVO);
		}	
		
		public static void savePatientImage(PatientImageDtlVO patimageVO,
				UserVO userVO) {
			MbTransEssentialDelegate  mEssentialDelegate= new MbTransEssentialDelegate();
			mEssentialDelegate.savePatientImage(patimageVO,userVO);
			
		}
		public static String  getMaxCRNOPatientImage(String patCRNO,
				UserVO userVO) {
			MbTransEssentialDelegate  mEssentialDelegate= new MbTransEssentialDelegate();
			return mEssentialDelegate.getMaxCRNOPatientImage(patCRNO,userVO);
			
		}
		
		
}
