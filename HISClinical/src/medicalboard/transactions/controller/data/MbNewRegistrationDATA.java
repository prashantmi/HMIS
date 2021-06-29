package medicalboard.transactions.controller.data;

import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;
import medicalboard.transactions.delegate.MbTransEssentialDelegate;
import medicalboard.transactions.delegate.MbTransactionDelegate;


public class MbNewRegistrationDATA {

	/**
	 * gets New Registration Essentials
	 * calls getNewPatientRegEssential from EssentialDelegate
	 * @param _UserVO Provides User details.
	 * @return map containing new patient registration essentials
	 */
		public static Map setMsNewRegistrationEssentials(UserVO _UserVO)
		{
			MbTransEssentialDelegate mEssentialDelegate=new MbTransEssentialDelegate();
			Map mp=mEssentialDelegate.setMsNewRegistrationEssentials(_UserVO);
			return mp;		
		}
		
		public static Map getCheckList(String certificateType,UserVO _UserVO)
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
		
		/**
		 * registers new patient 
		 * calls newPatRegistration from PatientDelegate
		 * @param _patVO Provides patient details.
		 * @param _episodeVO Provides patient's episode details.
		 * @param _userVO Provides User details.
		 * @return EpisodeVO[] array of episodeVO
		 */
		public static EpisodeVO registerNewPatient(PatientVO _patVO, EpisodeVO _episodeVO,List lstCheckList,MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO){
			MbTransactionDelegate mbDelegate=new MbTransactionDelegate();
			return mbDelegate.newPatRegistration(_episodeVO, _patVO,lstCheckList,mRequisitionVO, _userVO);
		}	
		
		
		
		
}
