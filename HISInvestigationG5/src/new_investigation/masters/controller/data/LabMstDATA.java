package new_investigation.masters.controller.data;

 
import java.util.Map;

import new_investigation.vo.LabMasterVO;
import new_investigation.vo.TestNewMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class LabMstDATA
{
	public static void saveLab(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveLab(labMasterVO, _UserVO);
	}

	public static Map fetchLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLab(labMasterVO, _UserVO);
	}
	
	public static Map fetchLabD(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabD(labMasterVO, _UserVO);
	}
	
	public static void savemodifyLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyLab(labMasterVO, _UserVO);
	}
}
