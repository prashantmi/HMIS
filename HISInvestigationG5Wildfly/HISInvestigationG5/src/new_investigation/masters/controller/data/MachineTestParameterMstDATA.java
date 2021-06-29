package new_investigation.masters.controller.data;


import java.util.List;
import java.util.Map;

import new_investigation.vo.MachineTestParameterMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
//import new_investigation.vo.TestParaComboMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class MachineTestParameterMstDATA
{
	public static Map fetchParaMachineCombo(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchParaMachineCombo(machineTestParaMstVO, _UserVO);
	}
	
	public static Map fetchParaTestCombo(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchParaTestCombo(machineTestParaMstVO, _UserVO);
	}
	public static List<MachineTestParameterMasterVO> displayParameterDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.displayParameterDetails(machineTestParaMstVO, _UserVO);
	}
	public static void saveDetails(List<MachineTestParameterMasterVO> machineTestParamMst_listVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.saveDetails(machineTestParamMst_listVO, _UserVO);
	}

	
	public static Map fetchModifyDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyDetails(machineTestParaMstVO, _UserVO);
	}

	
	public static void savemodifymachineTestParaCombo(List<MachineTestParameterMasterVO> machinetestparacombo_listVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyMachineTestParaCombo(machinetestparacombo_listVO, _UserVO);
	}

	public static Map displayDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.displayDetails(machineTestParaMstVO, _UserVO);
	}
}
