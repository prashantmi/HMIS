package mortuary.masters.controller.hlp;

import java.util.Map;

import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.vo.ChamberMasterVO;
import hisglobal.vo.UserVO;

public class ChamberMasterDATA  
{
	
	public static Map getChamberEssentialDetails(String[]  _controlsArray, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.getChamberEssentialDetails(_controlsArray, _userVO);
	}
	
	
	public static boolean saveChamber(ChamberMasterVO _chamberMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.saveChamber(_chamberMstVO, _userVO);
		
	}
	
	public static ChamberMasterVO getChamberDetails(ChamberMasterVO _chamberMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.getChamberDetails(_chamberMstVO, _userVO);
	}

	public static boolean updateChamber(ChamberMasterVO _chamberMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.updateChamber(_chamberMstVO, _userVO);
	}

		
}
