package new_investigation.masters.controller.data;

 

import new_investigation.vo.MandatoryMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class MandatoryMstDATA
{
	public static void saveMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveMandatory(mandatorymaster_VO, _UserVO);
	}

	public static void fetchCheckListMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchCheckListMandatory(mandatorymaster_VO, _UserVO);
	}
	
	
	public static void fetchMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchMandatory(mandatorymaster_VO, _UserVO);
	}
	
	public static void savemodifyMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyMandatory(mandatorymaster_VO, _UserVO);
	}
}
