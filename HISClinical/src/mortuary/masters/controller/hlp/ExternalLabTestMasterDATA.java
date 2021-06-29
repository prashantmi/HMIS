package mortuary.masters.controller.hlp;

import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.vo.ExternalLabTestMasterVO;
import hisglobal.vo.UserVO;



public class ExternalLabTestMasterDATA  
{
		
	public static boolean saveExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.saveExternalLabTestDetails(_externalLabTestMstVO, _userVO);
		
	}
	
	public static ExternalLabTestMasterVO getExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.getExternalLabTestDetails(_externalLabTestMstVO, _userVO);
	}

	public static boolean updateExternalLabTestDetails(ExternalLabTestMasterVO _externalLabTestMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.updateExternalLabTestDetails(_externalLabTestMstVO, _userVO);
	}

		
}
