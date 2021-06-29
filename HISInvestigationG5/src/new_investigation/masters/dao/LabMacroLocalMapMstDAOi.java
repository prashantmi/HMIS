package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.LabMacroMapMasterVO;
import hisglobal.vo.UserVO;

public interface LabMacroLocalMapMstDAOi
{


	public void updateValidLabMacroMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	/*public void updateValidLaboratoryMacroMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);*/
	public void createLabMacroMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	/*public void insertLaboratoryMacroMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);*/
	public void fetchCheckListLabMacroMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	public String checkPrimaryKeyLabMacroMap(LabMacroMapMasterVO labmacromap_VO,UserVO _UserVO);
	public void deleteLabMacroMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public List getmacroCombo(UserVO _UserVO);
	public List getselectedmacroCombo(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	public List getnewmacroComboLeft(LabMacroMapMasterVO labmacromap_VO,UserVO _UserVO);
	public List getselectedmacroComboRight(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO,List<LabMacroMapMasterVO> labmacro_listVO);
	public void fetchdisplaydataMacroLocalMap(LabMacroMapMasterVO labmacromap_VO, UserVO _UserVO);
	

}
