package opd.master.controller.data;

import hisglobal.vo.DrugListItemMstVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;

public class DrugListItemMasterDATA 
{

	public static Map getDrugListItemMasterEssential( PatientDetailVO _patDetailVO,UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return(masterDelegate.getDrugListItemMasterEssential(_patDetailVO,userVO));
	}
	
	public static boolean saveDrugListItemMstDetail(List drugListItemMstVOList, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		boolean hasFlag=masterDelegate.saveDrugListItemMstDetail(drugListItemMstVOList, userVO);
		return hasFlag;
	}

	public static Map getDataForModifyDrugListItemMst(
			DrugListItemMstVO drugListItemMstVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return(masterDelegate.getDataForModifyDrugListItemMst(drugListItemMstVO,userVO));
	}

	public static void saveModifyDrugListItemMstDetail(List selectedDrugList,
			UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveModifyDrugListItemMstDetail(selectedDrugList, userVO);
		
	}

}
