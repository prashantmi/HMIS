package new_investigation.reports.delegate;


import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

import java.util.Map;

import new_investigation.reports.bo.MachineTestReportNewBO;
import new_investigation.vo.MachineTestReportNewVO;

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