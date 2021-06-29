package registration.transactions.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;

public class verificationDocumentDATA extends ControllerDATA {

	/**
	 * gets all verification document essentials
	 * @param _UserVO provides user  details
	 * @return map containing all verification document essentials 
	 */
	public static Map getAllVerificationDocEssentials(UserVO _UserVO)
	{
		Map mp= new HashMap();
		RegEssentialBO serviceBO = new RegEssentialBO();
		

		mp.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS, serviceBO.getVerificationDocuments(_UserVO));
	
		return mp;		
	}
}
