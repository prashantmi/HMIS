package medicalboard.masters.bo;

import hisglobal.vo.UserVO;

import java.util.Map;

public interface MedicalBoardMasterEssentialBOi {

	
	public Map getCertificateEssentials(UserVO userVO);	
	public Map getBoardEssentials(UserVO userVO);
	public Map getCertificateBoardEssentials(UserVO userVO);
	public Map getReferEssentials(UserVO userVO);
	
	public Map getInvestigationMappingMstEssentials(String certificateTypeId,UserVO _userVO);
	public Map getInvestigationModifyEssentials(String certificateTypeId,UserVO userVO);
	
}
