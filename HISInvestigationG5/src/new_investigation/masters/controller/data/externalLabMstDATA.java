package new_investigation.masters.controller.data;


import new_investigation.vo.externalLabMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;




public class externalLabMstDATA
{
	public static void saveParameter(externalLabMasterVO parametermaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveParameter(parametermaster_VO, _UserVO);
	}

	public static void fetchCheckListParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchCheckListParameter(parametermaster_VO, _UserVO);
	}

	public static void fetchParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchParameter(parametermaster_VO, _UserVO);
	}

	public static void savemodifyParameter(externalLabMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyParameter(parametermaster_VO, _UserVO);
	}
}