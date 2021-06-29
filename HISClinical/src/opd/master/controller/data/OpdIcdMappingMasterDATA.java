/**
 * 
 */
package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdMappingMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

/**
 * @author ashas
 *
 */
public class OpdIcdMappingMasterDATA extends ControllerDATA{
	
	// to get Hospital Disease
	public static Map getHospitalDisease(UserVO userVO_p)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getHospitalDiseaseForIcdMapping(userVO_p);
	}
	// to get Chronic Disease
	public static Map getChronicDisease(UserVO userVO_p)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getChronicDiseaseForIcdMapping(userVO_p);
	}
	//to get group name 	
	public static Map getEssential(UserVO userVO_p)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getMappingTypeWiseDiseaseEssential(userVO_p);
	}
	// to get subgroup based on group selelcted
	public static List<IcdSubgroupMasterVO> getSubGroupsByGroup(String strIcdGroupCode_p, UserVO userVO_p)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getSubGroupsByGroup(strIcdGroupCode_p, userVO_p);
	}
	// to get disease list based on subgroup selected
	public static List<IcdDiseaseMasterVO> getDiseaseBySubGroup(String strIcdSubgroupCode_p, UserVO userVO_p)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getDiseaseBySubGroup(strIcdSubgroupCode_p, userVO_p);
	}
	// to save icd mapping
	public static void saveIcdMapping(List listIcdMasterVO_p, UserVO userVO_p) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveIcdMapping(listIcdMasterVO_p,userVO_p);
		
	}
	// to get modify detail of icd mapping
	public static Map getIcdMappingForModify(IcdMappingMasterVO vo_p, UserVO userVO_p) {
		OpdMasterDelegate delegate=new OpdMasterDelegate();
		return delegate.getIcdMappingForModify(vo_p,userVO_p);
	}
	// to get modified etail of icd disease
	public static Map getModIcdDisease(String strMappingType_p,String strMappingId_p,String strIcdSubgroupCode_p, UserVO userVO_p)
	{
		OpdMasterDelegate delegate = new OpdMasterDelegate();
		return delegate.getModIcdDisease(strMappingType_p, strMappingId_p,strIcdSubgroupCode_p, userVO_p);
	}
	//to save modified detail of icd mapping
	public static void modifySaveIcdMapping(List listIcdMappingMasterVO_p, UserVO userVO_p) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.modifySaveIcdMapping(listIcdMappingMasterVO_p,userVO_p);
		
	}
	
}
