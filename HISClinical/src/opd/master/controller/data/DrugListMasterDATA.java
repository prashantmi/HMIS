package opd.master.controller.data;

import java.util.Map;

import hisglobal.vo.DrugListMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdMasterDelegate;

public class DrugListMasterDATA 
{
	public static boolean saveDrugListMstDetail(DrugListMasterVO drugListMasterVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveDrugListMstDetail(drugListMasterVO, userVO);
		return hasFlag;
	}
	
	public static Map getDataForModifyDrugListMst(
			DrugListMasterVO drugListMasterVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return(masterDelegate.getDataForModifyDrugListMst(drugListMasterVO, userVO));
	}
	
	public static boolean saveModDrugListMstDetail(DrugListMasterVO drugListMasterVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveModDrugListMstDetail(drugListMasterVO, userVO);
		return hasFlag;
	}
}
