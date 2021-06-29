package opd.master.dao;

import hisglobal.vo.TemplateMappingMstVO;
import hisglobal.vo.UserVO;
import java.util.List;

public interface TemplateMappingMstDAOi {

		public String getCategoryName(String templateCategory, UserVO userVO);

		public List getDepartmentNotAdded(String templateCategory, UserVO userVO);
		
		public List getUnitNotAdded(TemplateMappingMstVO templateMappingVO,UserVO userVO);
		
		public List getWardNotAdded(TemplateMappingMstVO templateMappingVO, UserVO userVO);
		
		public List getTemplateListNotAdded(String categoryCode,UserVO userVO);
		
		public void saveTemplateMapping(TemplateMappingMstVO templateMappingVO, UserVO userVO);
		
		public TemplateMappingMstVO[] getTemplateMapping(TemplateMappingMstVO templateMappingVO,int flag,UserVO userVO);
		
		public List getAddedTemplateList(TemplateMappingMstVO templateMappingVOs,UserVO userVO);
		
		public void modifyTemplateMapping(TemplateMappingMstVO templateMappingVO,UserVO userVO);

		public void modifyInsertTemplateMapping(TemplateMappingMstVO templateMappingVO,UserVO userVO);
		
		public TemplateMappingMstVO checkBeforeModify(TemplateMappingMstVO templateMappingVO,UserVO userVO);

		public List getAllUnits(String deptCode,UserVO userVO);
}

