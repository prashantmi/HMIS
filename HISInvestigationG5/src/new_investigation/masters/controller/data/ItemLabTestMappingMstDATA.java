package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.ItemLabTestMappingMstVO;
import new_investigation.vo.LabConsumableMstVO;
import new_investigation.vo.TestNewMasterVO;

public class ItemLabTestMappingMstDATA {

	public static void saveDetailsModify(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveDetailsModifyItemConsumable(itemLabTestMappingMstVO, _UserVO);
	}

	public static Map fetchDetails(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
			
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchDetailsOfItems(itemLabTestMappingMstVO, _UserVO);
	}
	public static void saveDetails(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveDetailsItemConsumable(itemLabTestMappingMstVO, _UserVO);
	}
	public static Map getEssentials(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
	
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getEssentialsForItemConsumable(itemLabTestMappingMstVO, _UserVO);
	}
	public static Map lotDuplicacyCheck(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
	
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.lotDuplicacyCheck(itemLabTestMappingMstVO, _UserVO);
	}
public static List getItemListCombo(ItemLabTestMappingMstVO itemLabTestMappingMstVO, UserVO _UserVO)
	
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getItemListCombo(itemLabTestMappingMstVO, _UserVO);
	}
	
	
}
