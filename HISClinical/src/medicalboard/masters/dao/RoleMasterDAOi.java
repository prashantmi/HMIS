package medicalboard.masters.dao;

import hisglobal.vo.RoleMasterVO;
import hisglobal.vo.UserVO;

public interface RoleMasterDAOi {

	public String checkDuplicateRoleName(RoleMasterVO roleMasterVO,UserVO userVO);
	public void create(RoleMasterVO roleMasterVO,UserVO userVO);
	public RoleMasterVO getRoleDtl(RoleMasterVO roleMasterVO, UserVO _UserVO);
	public void updateRoleDtl(RoleMasterVO roleMasterVO,UserVO _UserVO);
	public void modifySaveRoleDtl(RoleMasterVO roleMasterVO, UserVO _UserVO);
	public String checkDuplicateRoleNameForModify(RoleMasterVO roleMasterVO,UserVO userVO);
	
}
