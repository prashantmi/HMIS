package opd.master.controller.data;

/**
 * @author  CDAC
 */

import hisglobal.vo.OpdParameterVO;
import hisglobal.vo.OpdTemplateParameterVO;
import hisglobal.vo.OpdTemplateVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class AddTemplateParameterMasterDATA 
{
	//* Getting Parameter List
	public static Map getParameterList(UserVO _UserVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		Map mp=essentialDelegate.getAddTempleteParameterMasterParaList(_UserVO);
		return mp;		
	}
	
	//* Saving Template Record
	public static void SaveTemplate(OpdTemplateVO _tmpltVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveTemplate(_tmpltVO,_userVO);		
	}
	
	//* Updating Template Record  
	public static void UpdateTemplate(OpdTemplateVO _tmpltVO, UserVO _userVO)
	{
		//OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		//masterDelegate.updateTemplate(_tmpltVO,_userVO);		
	}

	//* Adding Template with New Serial No 
	public static void SaveNewTemp(OpdTemplateVO _tmpltVO, UserVO _userVO)
	{
		//OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		//masterDelegate.saveNewTemp(_tmpltVO,_userVO);		
	}

	//* Saving Parameter Record
	public static void SaveOPDParameter(OpdParameterVO _paraVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveOPDParameter(_paraVO,_userVO);		
	}
	
	//* Saving Template Parameter Detail to TemplateParameterMaster
	public static void SaveParameterToTemplate(OpdTemplateParameterVO _tempParaVO ,UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveParameterToTemplate(_tempParaVO,_userVO);		
	}

	//* Getting Template Data
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

	//* Delete the old Template Parameter Data By Template Id
	public static void deleteTempParaById(String _TempId, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.deleteTempParaById(_TempId,_userVO);
	}

	//* Getting Parameter Dynamic Data
	public static List getParameterDynamicData(String _query, UserVO _userVO)
	{
		OpdEssentialDelegate essentialDelegate=new OpdEssentialDelegate();
		List lst=essentialDelegate.getParameterDynamicData(_query,_userVO);
		return lst;		
	}
}
