package opd.master.controller.data;

import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.TemplateMappingMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public class TemplateMappingMstDATA extends ControllerDATA
{
	public static List getUnitNotAdded(TemplateMappingMstVO templateMappingVO,UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getUnitNotAdded(templateMappingVO,userVO);
	}
	
	public static void saveTemplateMapping(TemplateMappingMstVO[] templateMappingVOs, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveTemplateMapping(templateMappingVOs,userVO);
		
	}

	public static Map getTemplateMapping(TemplateMappingMstVO templateMappingVO, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getTemplateMapping(templateMappingVO,userVO);
	
	}

	public static void modifyTemplateMapping(TemplateMappingMstVO[] updateTemplateMappingVOs,
			TemplateMappingMstVO[] insertTemplateMappingVOs, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifyTemplateMapping(updateTemplateMappingVOs,insertTemplateMappingVOs,userVO);
		
	}

	public static Map getEssentials(String templateCategory, UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getEssentialsForTemplateMapping(templateCategory,userVO);
	}

	public static List getDepartment(UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getAllDepartments(userVO);
	}
	
	public static List getAllUnits(String deptCode,UserVO _UserVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getAllUnits(deptCode,_UserVO);
	}

	public static List getWardNotAdded(TemplateMappingMstVO templateMappingVO,UserVO userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getWardNotAdded(templateMappingVO,userVO);
	}

}
