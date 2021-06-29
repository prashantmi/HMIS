package inpatient.masters.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.LaborRoomAreaMasterVO;
import hisglobal.vo.UserVO;
import inpatient.masters.delegate.MasterDelegate;

public class LaborRoomAreaMstDATA extends ControllerDATA {
	
	public static Map getLaborRoomAreaMstEssentails(UserVO _userVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return masterDelegate.getLaborRoomAreaMstEssentails(_userVO);
		
	}
	
	//Saving the Data
	public static void saveDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO,UserVO _userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveDetail(laborRoomAreaMstVO,_userVO);
	}
	public static LaborRoomAreaMasterVO getModifyDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO,UserVO _userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		return mstDelegate.getModifyDetail(laborRoomAreaMstVO,_userVO);
		
	}
	public static void saveModifyDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO,UserVO _userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveModifyDetail(laborRoomAreaMstVO,_userVO);
		
	}
}
