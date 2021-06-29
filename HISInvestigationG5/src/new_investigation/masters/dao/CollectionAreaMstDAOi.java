package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.CollectionAreaMasterVO;
import hisglobal.vo.UserVO;

public interface CollectionAreaMstDAOi
{


	public String checkDuplicateCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO);
	public void createCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO);
	public void fetchCheckListCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO);
	public String checkDuplicateModifyCollectionArea(CollectionAreaMasterVO collectionarea_VO,UserVO _UserVO);
	public void updateCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO);
	public List getlocationCombo(UserVO _UserVO);
	public List getwardCombo(UserVO _UserVO);
	public List getwardComboOnModify(String areaCode,UserVO _UserVO);


}
