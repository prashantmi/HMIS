package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.LabCollectionAreaMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class LabCollectionAreaMstDATA
{
	public static void saveLabCollectionArea(LabCollectionAreaMasterVO[] insert_labcollectionarea_VO,LabCollectionAreaMasterVO[] delete_labcollectionarea_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveLabCollectionArea(insert_labcollectionarea_VO,delete_labcollectionarea_VO, _UserVO);
	}




	public static Map fetchLabCollectionArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabCollectionArea(labcollectionarea_VO, _UserVO);
	}


	
	public static Map getArea(LabCollectionAreaMasterVO labcollectionarea_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getArea(labcollectionarea_VO, _UserVO);
	}
	
}
