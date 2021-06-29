package new_investigation.masters.controller.data;

 
import java.util.Map;

import new_investigation.vo.LabMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class LocalLabMstDATA
{
	public static void saveLocalLab(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveLocalLab(labMasterVO, _UserVO);
	}

	public static Map fetchLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLocalLab(labMasterVO, _UserVO);
	}
	
	public static Map fetchLocalLabD(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLocalLabD(labMasterVO, _UserVO);
	}
	
	public static Map Populate(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.Populate(labMasterVO, _UserVO);
	}
	
	
	public static void savemodifyLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyLocalLab(labMasterVO, _UserVO);
	}
	
	public static Map fetchGlobalLabCombo(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchGlobalLabCombo(labMasterVO, _UserVO);
	}
}
