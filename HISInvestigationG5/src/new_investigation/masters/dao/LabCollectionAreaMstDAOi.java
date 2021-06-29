package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.LabCollectionAreaMasterVO;
import hisglobal.vo.UserVO;

public interface LabCollectionAreaMstDAOi
{


	public void updateValidLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);
	public void createLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);
	public void fetchCheckListLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);
	public String checkPrimaryKeyLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO,UserVO _UserVO);
	public void deleteLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);
	public List getlabCombo(UserVO _UserVO);
	public List getareaCombo(UserVO _UserVO);
	public List getselectedareaCombo(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);
	public List getnewareaComboLeft(LabCollectionAreaMasterVO labcollectionarea_VO,UserVO _UserVO);
	public List getselectedareaComboRight(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO);

}
