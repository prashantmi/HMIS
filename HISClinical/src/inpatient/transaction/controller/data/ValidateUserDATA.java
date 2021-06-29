package inpatient.transaction.controller.data;

import java.util.List;

import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.delegate.DynamicDeskDelegate;
import hisglobal.vo.UserVO;

public class ValidateUserDATA extends ControllerDATA
{
	// Getting Desk User List
	public static List<UserVO> getUserList(UserVO _userVO,String _wardCode)
	{
		DynamicDeskDelegate delegate=new DynamicDeskDelegate();
		return delegate.getUserList(_userVO,_wardCode);
	}
}