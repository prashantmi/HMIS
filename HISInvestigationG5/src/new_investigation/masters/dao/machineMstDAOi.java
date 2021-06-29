package new_investigation.masters.dao;

import java.util.List;





import new_investigation.vo.LabCollectionAreaMasterVO;
import new_investigation.vo.machineMstVO;
import hisglobal.vo.UserVO;

public interface machineMstDAOi
{



	
	public void createMachine(machineMstVO vo, UserVO uservo);
	public void createMachineCommports(machineMstVO vo, UserVO uservo);
	public String generateMachineId(machineMstVO vo, UserVO uservo);
	public String checkDuplicateMachine(machineMstVO vo, UserVO uservo);
	public String checkDuplicateModifyMachine(machineMstVO vo, UserVO uservo);
	public void fetchMachine(machineMstVO vo, UserVO uservo);
	public void fetchMachineCommPort(machineMstVO vo, UserVO uservo);  
	public List<machineMstVO> fetchMachineAndCommportDetails(machineMstVO machinemst_VO, UserVO _UserVO);
	public String checkCommPortAvailable(machineMstVO machinemst_VO, UserVO _UserVO);
	public void updateMachine(machineMstVO vo, UserVO uservo);
	public void updateMachineCommports(machineMstVO vo, UserVO uservo);
	public void deleteMachineCommPort( String code,String commPort, UserVO _UserVO);

	
	public List getFormat(machineMstVO vo, UserVO uservo);
	public List getCommFlag(machineMstVO vo, UserVO uservo);
	public List getLocationCombo(machineMstVO vo, UserVO uservo);
}
