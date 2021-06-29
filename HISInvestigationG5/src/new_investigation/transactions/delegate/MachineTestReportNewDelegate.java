package new_investigation.reports.delegate;


import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.masters.bo.InvestigationMasterBO;
import new_investigation.masters.bo.InvestigationMasterBOi;
import new_investigation.reports.bo.MachineTestReportNewBO;
import new_investigation.reports.bo.MachineTestReportNewBO;
//import new_investigation.reports.bo.RegEssentialBO;
import new_investigation.vo.BookMarkMstVO;
import new_investigation.vo.CannedMasterVO;
import new_investigation.vo.CollAreaSampleNoConfigMasterVO;
import new_investigation.vo.CollectionAreaMasterVO;
import new_investigation.vo.ContainerMasterVO;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.InvTestSampleMasterVO;
import new_investigation.vo.MachineTestReportNewVO;
import new_investigation.vo.LabCannedMasterVO;
import new_investigation.vo.LabCollectionAreaMasterVO;
import new_investigation.vo.LabConfigratorMstVO;
import new_investigation.vo.LabMacroMapMasterVO;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.LabNoConfigMasterVO;
import new_investigation.vo.LabTestGlobalMstVO;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.LocalTestMandRefMasterVO;
import new_investigation.vo.LoincMstVO;
import new_investigation.vo.MacroMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.RejectionReasonMasterVO;
import new_investigation.vo.MachineTestReportVO;
import new_investigation.vo.SampleNoConfigMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import new_investigation.vo.TestGroupMasterVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.TestMandatoryMstVO;
import new_investigation.vo.TestNewMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.TestParameterMasterVO;

 
 
import new_investigation.vo.ContainerMasterVO;
import new_investigation.vo.CollectionAreaMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.TestParaComboMasterVO;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.InvTestSampleMasterVO;
import new_investigation.vo.UserBookMarkMstVO;

 

import new_investigation.vo.UOMMasterVO;

public class MachineTestReportNewDelegate extends Delegate
{
	public MachineTestReportNewDelegate()
	{
		super(new MachineTestReportNewBO());
	}  

	public Map AjaxGetLabList(MachineTestReportNewVO vo, UserVO userVO) {
		MachineTestReportNewBO serviceBO =(MachineTestReportNewBO) super.getServiceProvider();
		return serviceBO.AjaxGetLabList(vo, userVO);
	}
	
	
	public Map AjaxGetMachineList(MachineTestReportNewVO vo, UserVO userVO) {
		MachineTestReportNewBO serviceBO =(MachineTestReportNewBO) super.getServiceProvider();
		return serviceBO.AjaxGetMachineList(vo, userVO);
	}
	
	
	public Map AjaxGetMachineTestReportList(MachineTestReportNewVO vo, UserVO userVO) {
		MachineTestReportNewBO serviceBO =(MachineTestReportNewBO) super.getServiceProvider();
		return serviceBO.AjaxGetMachineTestReportList(vo, userVO);
	}




}