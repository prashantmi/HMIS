package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DiseaseSiteMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class DiseaseSiteDATA extends ControllerDATA
{
	// Get Essentials
	public static Map getEssentials(UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getDiseaseSiteEssential(_userVO);
	}
	
	// Get Subgroups Group Wise
	public static List<IcdSubgroupMasterVO> getSubGroupsByGroup(String _icdGroupCode, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getSubGroupsByGroup(_icdGroupCode, _userVO);
	}

	// Get Disease SubGroup Wise
	public static List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String _icdSubgroupCode, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getDiseaseBySubGroup(_icdSubgroupCode, _userVO);
	}

	// Saving Disease Site Detail
	public static void save(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		OpdMasterDelegate delegate = new OpdMasterDelegate();
		delegate.saveDiseaseSite(_diseaseSiteVO,_userVO);
	}

	// Fetching Disease Site Detail
	public static DiseaseSiteMasterVO getRecord(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.fetchDiseaseSiteDtl(_diseaseSiteVO,_userVO); 
	}
	
	// Modifying Disease Site Detail
	public static void modify(DiseaseSiteMasterVO _diseaseSiteVO, UserVO _userVO)
	{
		OpdMasterDelegate delegate = new OpdMasterDelegate();
		delegate.modifyDiseaseSite(_diseaseSiteVO,_userVO);
	}

	/*public static List getPrimaryKey(String tableName)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getPrimaryKey( tableName);
	}
	
	public static List getAllergySensistivity(UserVO _UserVO)
	{
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		return masterDelegate.getAllergySensistivity( _UserVO);
	}*/
}
