package opd.master.controller.data;

import hisglobal.utility.Entry;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdIndexMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;
import opd.bo.delegate.OpdMasterDelegate;

import java.util.List;
import java.util.Map;



public class IcdIndexMasterDATA 
{
	/*
	 * Initial Add Page for Icd Index Level Master
	 */
	public static Map<String, Object> getInitializeGroup(UserVO strUserVO_p) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getInitializeGroup(strUserVO_p);
	}
	
	
	/*
	 * Populating the Icd Sub Group Combo
	 */
	public static List<IcdSubgroupMasterVO> getIcdSubGroupByGroupCode(String strIcdGroupCode_p,UserVO strUserVO_p) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getIcdSubGroupByGroupCode(strIcdGroupCode_p,strUserVO_p);
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
	public static boolean saveRecord(IcdIndexMasterVO vo, UserVO userVO)
	{
	
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.saveIcdIndexMaster(vo,userVO);
		
	}
	/*
	 * To get Icd Diseases and Dual Icd Diseases on the Modify Page
	 */
/*	public static String[] getDiseases(IcdIndexMasterVO vo, UserVO userVO)
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getDiseasesIcdIndexLevelMaster(vo,userVO);	
	} 
	
	/*
	 * To get Modify details for IcdIndexLevelMaster
	 */
	public static void getModifyRecord(IcdIndexMasterVO vo, UserVO userVO)
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		masDelegate.modifyRecordIcdIndexMaster(vo,userVO);	
	}
	
	
	/*
	 * To ModifySave a Record for IcdIndexLevelMaster
	 */
	public static boolean modifySave(IcdIndexMasterVO vo, UserVO userVO)
	{
			OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
			return masterDelegate.modifySaveIcdIndexMaster(vo,userVO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

