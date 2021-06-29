package medicalboard.masters.dao;

import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.UserVO;

public interface OrganizationMstDAOi {

	public String checkDuplicateOrganizationName(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO);
	public void create(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO);
	public MbOrganizationMstVO getOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO _UserVO);
	public void updateOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO _UserVO);
	public void modifySaveOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO _UserVO);
	public String checkDuplicateOrganizatioNameForModify(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO);
	
}
