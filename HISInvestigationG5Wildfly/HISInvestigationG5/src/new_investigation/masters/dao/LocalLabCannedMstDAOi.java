package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.LabCannedMasterVO;
import hisglobal.vo.UserVO;

public interface LocalLabCannedMstDAOi
{


	public void updateValidLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);
	public void createLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);
	public String checkPrimaryKeyLocalLabCanned(LabCannedMasterVO labcannedmaster_VO,UserVO _UserVO);
	public void deleteLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);
	public List getnewLocalLabCannedComboLeft(LabCannedMasterVO labcannedmaster_VO,UserVO _UserVO);
	public List getselectedLocalLabCannedComboRight(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO,List<LabCannedMasterVO> labcanned_listVO );
	public void fetchdisplaydataLocalLabCanned(LabCannedMasterVO labcannedmaster_VO, UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public String checkLocallyMapped(LabCannedMasterVO labcannedmaster_VO,UserVO _UserVO);
	
}
