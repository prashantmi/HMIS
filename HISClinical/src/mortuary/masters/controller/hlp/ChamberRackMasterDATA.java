package mortuary.masters.controller.hlp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.UserVO;
import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;
import mortuary.masters.delegate.MortuaryEssentialDelegate;
import mortuary.masters.delegate.MortuaryMasterDelegate;


public class ChamberRackMasterDATA extends ControllerUTIL
{
	
	public static String getChamberRackEssentials(String  _chamberId, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.getChamberRackEssentials(_chamberId, _userVO);
	}
	
	
	public static boolean saveChamberRack(ChamberRackMasterVO[] _chamberMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.saveChamberRack(_chamberMstVO, _userVO);
		
	}
	
	public static ChamberRackMasterVO getChamberRackDetails(ChamberRackMasterVO _chamberMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.getChamberRackDetails(_chamberMstVO, _userVO);
	}

	public static boolean updateChamberRack(ChamberRackMasterVO _chamberMstVO, UserVO _userVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return masterDelegate.updateChamberRack(_chamberMstVO, _userVO);
	}

		
}
