package new_investigation.masters.controller.data;


import new_investigation.vo.InvParameterMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;




public class InvParameterMstDATA
{
	public static void saveParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveParameter(parametermaster_VO, _UserVO);
	}

	public static void fetchCheckListParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchCheckListParameter(parametermaster_VO, _UserVO);
	}

	public static void fetchParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchParameter(parametermaster_VO, _UserVO);
	}

	public static void savemodifyParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyParameter(parametermaster_VO, _UserVO);
	}
}