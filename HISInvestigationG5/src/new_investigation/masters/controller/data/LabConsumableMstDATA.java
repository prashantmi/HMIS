package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.LabConsumableMstVO;
import new_investigation.vo.TestNewMasterVO;

public class LabConsumableMstDATA {

	public static void saveDetails(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveDetailsLabConsumable(labConsumableMstVO, _UserVO);
	}

	public static Map fetchDetails(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
			
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchDetails(labConsumableMstVO, _UserVO);
	}
	public static void saveModifyDetails(LabConsumableMstVO labConsumableMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveDetailsModify(labConsumableMstVO, _UserVO);
	}
	public static int checkDuplicateForModify(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
	
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.checkDuplicateForModify(labConsumableMstVO, _UserVO);
	}
	public static Map getEssentials(LabConsumableMstVO labConsumableMstVO, UserVO _UserVO)
	
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getEssentials(labConsumableMstVO, _UserVO);
	}
	/*
	
	public static Map fetchFilmD(FilmMstVO testNewMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchFilmD(testNewMasterVO, _UserVO);
	}
	
	*/
	
}
