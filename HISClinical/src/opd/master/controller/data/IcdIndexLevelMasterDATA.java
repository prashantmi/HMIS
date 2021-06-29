package opd.master.controller.data;

import hisglobal.utility.Entry;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdMasterDelegate;

import java.util.List;
import java.util.Map;


/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 1-Feb-2011
 *  Process Name : Icd Index Level Master
 *  Last Modified Date : 28-Mar-2011  
 */
public class IcdIndexLevelMasterDATA 
{
	/*
	 * Initial Add Page for Icd Index Level Master
	 */
	public static Map<String, Object> getInitializeAdd(UserVO userVO) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getInitializeAdd(userVO);
	}
	
	
	/*
	 *  Populating the Parent Modifier Combo
	 */
	public static List<Entry> getParentModifier(IcdIndexLevelMasterVO icdIndexLevelMasterVO,UserVO userVO) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getParentModifier(icdIndexLevelMasterVO,userVO);
	}
	
	
	/*
	 * Populating the Icd Sub Group Combo
	 */
	public static List<IcdSubgroupMasterVO> getIcdSubGroupByGroupCode(String _icdGroupCode,UserVO _userVO) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getIcdSubGroupByGroupCode(_icdGroupCode, _userVO);
	}
	
	/*
	 * Populating the Icd Disease Combo
	 */
	public static List<IcdDiseaseMasterVO> getIcdDiseaseBySubGroup(String _subgroupCode,UserVO userVO) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getDiseaseBySubGroup(_subgroupCode,userVO);
	}
	
	/*
	 * To Save Record on the Add Page
	 */
	public static boolean saveRecord(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
	
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.saveIcdIndexLevelMaster(vo,userVO);
		
	}

	
	/*
	 * To get Modify details for IcdIndexLevelMaster
	 */
	public static void getModifyRecord(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		masDelegate.modifyRecordIcdIndexLevelMaster(vo,userVO);	
	}
	
	/*
	 * To ModifySave a Record for IcdIndexLevelMaster
	 */
	public static boolean modifySave(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
			OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
			return masterDelegate.modifySaveIcdIndexLevelMaster(vo,userVO);
	}
	
	/*
	 * To View Page
	 */
	public static void getViewRecord(IcdIndexLevelMasterVO vo, UserVO userVO)
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		masDelegate.getViewRecordIcdIndexLevelMaster(vo,userVO);	
	}
}
