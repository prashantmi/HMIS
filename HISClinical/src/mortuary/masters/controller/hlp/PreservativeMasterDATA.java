package mortuary.masters.controller.hlp;

import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.vo.PreservativeMasterVO;
import hisglobal.vo.UserVO;


public class PreservativeMasterDATA  
{
		
	public static boolean savePreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.savePreservativeDetails(_preservativeMasterVO, _userVO);
		
	}
	
	public static PreservativeMasterVO getPreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.getPreservativeDetails(_preservativeMasterVO, _userVO);
	}

	public static boolean updatePreservativeDetails(PreservativeMasterVO _preservativeMasterVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.updatePreservativeDetails(_preservativeMasterVO, _userVO);
	}

		
}
