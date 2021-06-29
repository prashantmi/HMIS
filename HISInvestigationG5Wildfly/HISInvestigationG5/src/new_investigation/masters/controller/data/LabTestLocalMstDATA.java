package new_investigation.masters.controller.data;

import java.util.List;
import java.util.Map;

import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.masters.bo.InvestigationMasterBO;
import new_investigation.vo.LabTestGlobalMstVO;

public class LabTestLocalMstDATA {
	
	public static Map fetchLabTest(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchLabTestLocal(labTestGlobalMasterVO, _UserVO);
	}
	public static List getTestBylabCode(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBO masterDelegate = new InvestigationMasterBO();
		return masterDelegate.getTestBylabCodeLocal(labTestGlobalMasterVO, _UserVO);
	}
	public static Map fetchLabTestGlobalData(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBO masterDelegate = new InvestigationMasterBO();
		return masterDelegate.fetchLabTestGlobalData(labTestGlobalMasterVO, _UserVO);
	}
	public static void saveLabTest(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveLabTestLocal(LabTestGlobalMstVO, _UserVO);
	}
	
	public static void savemodifyLabTest(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyLabTestLocal(LabTestGlobalMstVO, _UserVO);
	}
	
	public static Map fetchModifyLabTestGlobal(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchModifyLabTestLocal(labTestGlobalMasterVO, _UserVO);
	}
	
	public static List getMachineLocal(LabTestGlobalMstVO labTestGlobalMasterVO, UserVO _UserVO)
	{
		InvestigationMasterBO masterDelegate = new InvestigationMasterBO();
		return masterDelegate.getMachineLocal(labTestGlobalMasterVO, _UserVO);
	}
}
