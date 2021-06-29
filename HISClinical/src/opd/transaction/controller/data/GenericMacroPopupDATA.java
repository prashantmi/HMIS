package opd.transaction.controller.data;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

public class GenericMacroPopupDATA extends ControllerDATA
{
	// Getting Macros Process Id Wise
	public static List<Entry> getMacrosByProcessId(String _processId, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getMacrosListByProcessId(_processId, _userVO);
	}

	// Getting Macros Process Id & Unit Wise 
	public static List<Entry> getUnitMacrosByProcessId(String _processId, String _unitCode, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getUnitMacrosByProcessId(_processId, _unitCode, _userVO);
	}
}
