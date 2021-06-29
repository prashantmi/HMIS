package opd.master.controller.data;

/**
 * @author  CDAC
 */

import hisglobal.vo.OpdTemplateVO;
import hisglobal.vo.UserVO;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class TemplateUtilityDATA 
{
	//* Getting All Template List
	public static List getTemplateList(UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getEntryTemplateAllTempList(_userVO);
		return lst;		
	}
	
	//* Getting Template Data By Template Id
	public static void getTemplateDataById(OpdTemplateVO _voTemp, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.getTemplateDataById(_voTemp,_userVO);
	}

	//* Getting Template Parameter Detail List
	public static List getTempParaDetailList(String _tempId, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getTemplateParaDetailListByTempId(_tempId,_userVO);
		return lst;		
	}
	
	//* Getting Parameter Dynamic Data
	public static List getParameterDynamicData(String _query, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getParameterDynamicData(_query,_userVO);
		return lst;		
	}
	
/*	// Getting TemplateParameter List By Template Id
	public static List getTempsParaList(String _tempId, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getTemplateParaListByTempId(_tempId,_userVO);
		return lst;		
	}

	// Getting CR Numbers List
	public static Map getCrNoPatientList(UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		Map mp=essentialDelegate.getAllPatientParaCrNoList(_userVO);
		return mp;		
	}

	// Saving Patient Parameter Detail
	public static void SavePatientParameters(OpdPatientParameterVO  _voPP ,UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.savePatientParameters(_voPP,_userVO);		
	}
	
	// Getting Report Essentials
	public static Map getSetReportEssentials(String _crNo,UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		Map mp=essentialDelegate.getSetTemplateReportEssentials(_crNo,_userVO);
		return mp;		
	}
	
	// Getting Actual Template IDs
	public static List getActualTempIds(String _crNo,String[] _aPV,UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getActualTempIds(_crNo,_aPV,_userVO);
		return lst;		
	}
	
	// Getting Actual Parameter Values
	public static List getPatActualParaValues(String _crNo,String[] _aPV,UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getPatActualParaValues(_crNo,_aPV,_userVO);
		return lst;		
	}

	// Getting Patient Parameter Values By CRNO  
	public static List getPatParaValues(String _crNo,String[] _aPV,UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getPatParaValues(_crNo,_aPV,_userVO);
		return lst;
	}*/
}
