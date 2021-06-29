
package inpatient.masters.controller.data;

import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
/*
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
*/
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.LaborRoomMasterVO;
import hisglobal.vo.UserVO;


public class LaborRoomMasterDATA extends ControllerDATA
{
	public static Map<String,Object> getLaborRoomMasterEssentails(UserVO _userVO)
	{
		MasterDelegate masDelegate= new MasterDelegate();
		return masDelegate.getLaborRoomMasterEssentails(_userVO);
	}

	public static boolean saveDetail(LaborRoomMasterVO laborRoomMasterVO,UserVO _userVO)
	{
		boolean flag=false;
		MasterDelegate masDelegate= new MasterDelegate();
		flag=masDelegate.saveDetail(laborRoomMasterVO,_userVO);
		return flag;
		
	}
	
	public static LaborRoomMasterVO getModifyDetail(LaborRoomMasterVO laborRoomMasterVO,UserVO _userVO)
	{
		MasterDelegate masDelegate= new MasterDelegate();
		return masDelegate.getModifyDetail(laborRoomMasterVO,_userVO);
		
	}
	
	public static void saveModifyDetail(LaborRoomMasterVO laborRoomMasterVO,UserVO _userVO)
	{
		MasterDelegate masDelegate= new MasterDelegate();
		masDelegate.saveModifyDetail(laborRoomMasterVO,_userVO);
		
	}
	

}
