package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.LabMacroMapMasterVO;
import hisglobal.vo.UserVO;

public interface LabMacroGlobalMapMstDAOi
{


	public void updateValidLabMacroGlobalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	public void createLabMacroGlobalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	public void fetchCheckListLabMacroGlobalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	public String checkPrimaryKeyLabMacroGlobalMap(LabMacroMapMasterVO labmacromap_VO,UserVO _UserVO);
	public void deleteLabMacroGlobalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public List getmacroglobalCombo(UserVO _UserVO);
	public List getselectedmacroglobalCombo(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	public List getnewmacroglobalComboLeft(LabMacroMapMasterVO labmacromap_VO,UserVO _UserVO);
	public List getselectedmacroglobalComboRight(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO,List<LabMacroMapMasterVO> labmacro_listVO);

}
