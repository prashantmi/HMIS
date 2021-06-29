package new_investigation.masters.controller.data;


import java.util.List;
import java.util.Map;

import new_investigation.vo.LabCollectionAreaMasterVO;
import new_investigation.vo.machineMstVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class machineMstDATA
{
	public static void saveMachine(List<machineMstVO> lstVO,machineMstVO machine_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveMachine(lstVO,machine_VO, _UserVO);
	}
	
	public static void saveModifyMachine(List<machineMstVO> lstVO,machineMstVO machine_VO,List deleteList,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveModifyMachine(lstVO,machine_VO,deleteList, _UserVO);
	}




	public static Map getEssentialMachineCombo(machineMstVO machinemst_vo, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.getEssentialMachineCombo(machinemst_vo, _UserVO);
	}


	
	public static List<machineMstVO> fetchMachineDetails(machineMstVO machinemst_vo, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchMachineDetails(machinemst_vo, _UserVO);
	}
	
}
