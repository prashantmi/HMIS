package opd.master.controller.data;

import hisglobal.utility.Entry;
import hisglobal.vo.IcdCrossRefMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;




public class IcdCrossRefMasterDATA
{

	/*
	 * Initial New Page for Icd Cross Ref Master
	 * Populating Index Term Combo
	 */
	public static Map<String, Object> getInitializeIndexTerm(UserVO userVO) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getInitializeIndexTerm(userVO);
	}
	
	/*
	 * Populating Modifier Term Combo
	 */
	public static List<Entry> getModifier(String strIndex,UserVO strUserVO_p) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getModifier(strIndex, strUserVO_p);
	}
	
	
	
	public static List<IcdIndexLevelMasterVO> getSeeTerms(String strIndex,UserVO strUserVO_p) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getSeeTerms(strIndex,strUserVO_p);
	}
	/*
	 * Populating Modifier Term Combo
	 */
	public static List<Entry> getInitializeModifierNext(String strModifierID, String level, UserVO strUserVO_p) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getInitializeSubModifierNext(strModifierID, level, strUserVO_p);
	}
	
	//populating see terms for modifier 
	public static List<IcdIndexLevelMasterVO> getSeeTermsForModi(String strModId,UserVO strUserVO_p) 
	{
		OpdMasterDelegate masDelegate= new OpdMasterDelegate();
		return masDelegate.getSeeTermsForModi(strModId,strUserVO_p);
	}
	
	// To Save Record on the Add Page
	public static void saveRecord(IcdCrossRefMasterVO vo, UserVO userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveIcdCrossReferenceMaster(vo,userVO);
	}
}
