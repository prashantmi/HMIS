package medicalboard.masters.delegate;

import java.util.Map;
import medicalboard.masters.bo.MedicalBoardMasterEssentialBO;
import medicalboard.masters.bo.MedicalBoardMasterEssentialBOi;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;



public class MbMasterEssentialDelegate extends Delegate{

	public MbMasterEssentialDelegate() {
		super(new MedicalBoardMasterEssentialBO());
		// TODO Auto-generated constructor stub
	}

	
	
	public Map getCertificateEssentials(UserVO _userVO){
		MedicalBoardMasterEssentialBOi serviceBO = (MedicalBoardMasterEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCertificateEssentials(_userVO));
	}
	
	
	/// Board Type Master ///
	public Map getBoardEssentials(UserVO _userVO){
		MedicalBoardMasterEssentialBOi serviceBO = (MedicalBoardMasterEssentialBOi)super.getServiceProvider();
		return (serviceBO.getBoardEssentials(_userVO));
	}
	
	//Certificate Wise Board Mapping 
	
	public Map getCertificateBoardEssentials(UserVO _userVO){
		MedicalBoardMasterEssentialBOi serviceBO = (MedicalBoardMasterEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCertificateBoardEssentials(_userVO));
	}
	
	// refer Mapping 
	
	public Map getReferEssentials(UserVO _userVO){
		MedicalBoardMasterEssentialBOi serviceBO = (MedicalBoardMasterEssentialBOi)super.getServiceProvider();
		return (serviceBO.getReferEssentials(_userVO));
	}
	
	// investigation mapping
	
	public Map getInvestigationMappingMstEssentials(String certificateTypeId,UserVO _userVO){
		MedicalBoardMasterEssentialBOi serviceBO = (MedicalBoardMasterEssentialBOi)super.getServiceProvider();
		return (serviceBO.getInvestigationMappingMstEssentials(certificateTypeId,_userVO));
	}
	
	public Map getInvestigationModifyEssentials(String certificateTypeId,UserVO _userVO){
		MedicalBoardMasterEssentialBOi serviceBO = (MedicalBoardMasterEssentialBOi)super.getServiceProvider();
		return (serviceBO.getInvestigationModifyEssentials(certificateTypeId,_userVO));
	}
	
	
	
}
