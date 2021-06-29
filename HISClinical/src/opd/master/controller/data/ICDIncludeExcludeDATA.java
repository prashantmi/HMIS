package opd.master.controller.data;

import java.util.Map;

import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.UserVO;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class ICDIncludeExcludeDATA
{
	// Getting ICD Include Exclude Essentails
	public static Map getEssential(String _groupCode, String _subgroupCode, String _diseaseCode,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getICDIncludeExcludeEssential(_groupCode,_subgroupCode,_diseaseCode,userVO);
	}

	// Saving ICD Include Exclude Record
	public static void saveRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveICDIncludeExclude(_icdDiseaseVO, _UserVO);
	}

	// Getting ICD Include Exclude Record
	public static IcdDiseaseMasterVO getRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getICDIncludeExcludeRecord(_icdDiseaseVO, _userVO);
	}

	// Modifying ICD Include Exclude Record
	public static void updateRecord(IcdDiseaseMasterVO _icdDiseaseVO, UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.updateICDIncludeExcludeRecord(_icdDiseaseVO, _UserVO);
	}
}
