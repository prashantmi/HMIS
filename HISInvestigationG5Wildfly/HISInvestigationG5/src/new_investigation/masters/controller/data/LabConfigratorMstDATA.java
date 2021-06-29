package new_investigation.masters.controller.data;

import hisglobal.vo.UserVO;
import java.util.Map;
import java.util.List;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.LabConfigratorMstVO;

public class LabConfigratorMstDATA {
	public static Map fetchLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabConfigrator(labConfigratorMstVO, _UserVO);
	}
	public static Map populate(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.populate(labConfigratorMstVO, _UserVO);
	}
	public static Map getTestByLabCode(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getTestByLabCode(labConfigratorMstVO, _UserVO);
	}
	public static void saveLabConfigrator(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveLabConfigrator(labConfigratorMstVO, _UserVO);
	}
	public static Map fetchModifyLabConfigrator(LabConfigratorMstVO labConfigratorMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyLabConfigrator(labConfigratorMstVO, _UserVO);
	}
	public static void saveModifyLabConfigrator(LabConfigratorMstVO labConfigratorMstVO,UserVO _UserVO, List deleteTest, List deleteSample, List deleteMand, List deleteCanned,List deleteMacro)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveModifyLabConfigrator(labConfigratorMstVO, _UserVO,deleteTest,deleteSample,deleteMand,deleteCanned,deleteMacro);
	}
}
