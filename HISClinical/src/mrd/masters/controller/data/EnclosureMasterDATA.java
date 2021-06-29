package mrd.masters.controller.data;

import hisglobal.vo.EnclosureMasterVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import mrd.masters.delegate.MrdMasterDelegate;

public class EnclosureMasterDATA 
{
	public static boolean saveEnclosureMstDetail(EnclosureMasterVO enclosureMstVO , UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveEnclosureMstDetail(enclosureMstVO, _UserVO);
		return hasFlag;
	}
	
	public static Map getDataForModifyEnclosureMst(EnclosureMasterVO enclosureMstVO , UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return(masterDelegate.getDataForModifyEnclosureMst(enclosureMstVO, _UserVO));
	}
	
	public static boolean saveModEnclosureMaster(EnclosureMasterVO enclosureMstVO , UserVO _UserVO)
	{
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		boolean hasFlag=masterDelegate.saveModEnclosureMaster(enclosureMstVO, _UserVO);
		return hasFlag;
	}
}
