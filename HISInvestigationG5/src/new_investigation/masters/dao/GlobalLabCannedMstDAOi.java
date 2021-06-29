package new_investigation.masters.dao;

import java.util.List;



import new_investigation.vo.LabCannedMasterVO;
import hisglobal.vo.UserVO;

public interface GlobalLabCannedMstDAOi
{


	public void updateValidGlobalLabCannedMaster(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);
	public void createGlobalLabCannedMaster(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);
	/*public void fetchCheckListLabMacroGlobalMap(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);*/
	public String checkPrimaryKeyGlobalLabCannedMaster(LabCannedMasterVO labcannedmaster_VO,UserVO _UserVO);
	public void deleteGlobalLabCannedMaster(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);
	public List getlabCannedCombo(UserVO _UserVO);
	/*public List getmacroglobalCombo(UserVO _UserVO);*/
	/*public List getselectedmacroglobalCombo(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);*/
	public List getnewglobalCannedComboLeft(LabCannedMasterVO labcannedmaster_VO,UserVO _UserVO);
	public List getselectedglobalCannedComboRight(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO,List<LabCannedMasterVO> labcanned_listVO);

}
