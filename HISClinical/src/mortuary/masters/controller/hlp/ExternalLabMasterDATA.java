package mortuary.masters.controller.hlp;


import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.vo.ExternalLabMasterVO;
import hisglobal.vo.UserVO;



public class ExternalLabMasterDATA  
{
		
	public static boolean saveExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.saveExternalLabDetails(_externalLabMstVO, _userVO);
		
	}
	
	public static ExternalLabMasterVO getExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.getExternalLabDetails(_externalLabMstVO, _userVO);
	}

	public static boolean updateExternalLabDetails(ExternalLabMasterVO _externalLabMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.updateExternalLabDetails(_externalLabMstVO, _userVO);
	}

		
}
