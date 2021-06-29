package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.CollectionAreaMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class CollectionAreaMstDATA
{
	public static void saveCollectionArea(CollectionAreaMasterVO collectioncrea_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveCollectionArea(collectioncrea_VO, _UserVO);
	}

	public static Map fetchCheckListCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListCollectionArea(collectionarea_VO, _UserVO);
	}


	public static Map fetchCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCollectionArea(collectionarea_VO, _UserVO);
	}

	public static void savemodifyCollectionArea(CollectionAreaMasterVO collectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyCollectionArea(collectionarea_VO, _UserVO);
	}
}
