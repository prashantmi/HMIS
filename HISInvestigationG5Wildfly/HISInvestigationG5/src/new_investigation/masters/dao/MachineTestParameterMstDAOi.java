package new_investigation.masters.dao;

import java.util.List;







import new_investigation.vo.MachineTestParameterMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
//import new_investigation.vo.TestParaComboMasterVO;
import hisglobal.vo.UserVO;

public interface MachineTestParameterMstDAOi
{


	public List getMachineCombo(UserVO _UserVO);
	public List getTestCombo(UserVO _UserVO);
	public List<MachineTestParameterMasterVO> displayParameterDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);
	public void saveDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);
	//public void fetchModifyDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);
	public List<MachineTestParameterMasterVO> fetchModifyDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);

	public List<MachineTestParameterMasterVO> displayDetails(MachineTestParameterMasterVO machineTestParaMstVO, UserVO _UserVO);
	//public String checkDuplicateModifyTestParaCombo(MachineTestParameterMasterVO testparacombo_VO,UserVO _UserVO);
	public void updateTestParaCombo(MachineTestParameterMasterVO testparacombo_VO, UserVO _UserVO);
	//public void fetchCheckListMachineTestParaCombo(MachineTestParameterMasterVO testparacombo_VO, UserVO _UserVO);
}
