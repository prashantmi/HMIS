
package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DrugDoseMstVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class DrugDoseMasterDATA extends ControllerDATA
{
	public static Map getDrugDoseMasterEssentails(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getDrugDoseMasterEssentails(_userVO);
		
	}
	
	public static DrugDoseMstVO getItemName(String itemID,UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		DrugDoseMstVO _drugDoseMstVO = masterDelegate.getDrugItemName(itemID,_UserVO);
		return _drugDoseMstVO;
	}
	public static void saveDetail(DrugDoseMstVO drugDoseMstVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveDetail(drugDoseMstVO,_userVO);
		
	}
	
	public static DrugDoseMstVO  getModifyDetail(DrugDoseMstVO drugDoseMstVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		return opdDelegate.getModifyDetail(drugDoseMstVO,_userVO);
		
	}
	
	public static void saveModifyDetail(DrugDoseMstVO drugDoseMstVO,UserVO _userVO)
	{
		OpdMasterDelegate opdDelegate= new OpdMasterDelegate();
		opdDelegate.saveModifyDetail(drugDoseMstVO,_userVO);
		
	}
}
