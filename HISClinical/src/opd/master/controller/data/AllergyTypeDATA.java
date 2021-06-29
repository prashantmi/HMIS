package opd.master.controller.data;

import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AllergyTypeVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public class AllergyTypeDATA extends ControllerDATA
{
	public static Map getTheValues(UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		Map mp = masterDelegate.getTheValues(_UserVO);
		return mp;
	}
	
	public static List getTableData(UserVO userVO,String TableId)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getTableData(userVO,TableId); 
	}
	
	public static boolean insertAllergyTypeDynamicMode(AllergyTypeVO _allergyTypeVO,UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.insertAllergyTypeDynamicMode(_allergyTypeVO,_userVO);
	}
	public static List getPrimaryKey(String tableName)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getPrimaryKey( tableName);
	}
	
	public static List getAllergySensistivity(UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getAllergySensistivity( _UserVO);
	}


}
