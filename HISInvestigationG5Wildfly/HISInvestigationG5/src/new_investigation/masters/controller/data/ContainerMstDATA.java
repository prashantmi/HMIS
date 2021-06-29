package new_investigation.masters.controller.data;


import java.util.Map;

import new_investigation.vo.ContainerMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class ContainerMstDATA
{
	public static void saveContainer(ContainerMasterVO container_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveContainer(container_VO, _UserVO);
	}

	public static Map fetchCheckListContainer(ContainerMasterVO container_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckListContainer(container_VO, _UserVO);
	}


	public static Map fetchContainer(ContainerMasterVO container_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchContainer(container_VO, _UserVO);
	}

	public static void savemodifyContainer(ContainerMasterVO container_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyContainer(container_VO, _UserVO);
	}
}
