package new_investigation.reports.controller.data;


import java.util.Map;
import new_investigation.reports.delegate.MachineTestReportNewDelegate;
import new_investigation.vo.MachineTestReportNewVO;
import hisglobal.vo.UserVO;



public class MachineTestReportNewDATA {

	

	public static  Map AjaxGetLabList(MachineTestReportNewVO vo, UserVO userVO) {
		MachineTestReportNewDelegate reportDelegate = new MachineTestReportNewDelegate();
		return reportDelegate.AjaxGetLabList(vo, userVO);
	}
	

	public static  Map AjaxGetMachineList(MachineTestReportNewVO vo, UserVO userVO) {
		MachineTestReportNewDelegate reportDelegate = new MachineTestReportNewDelegate();
		return reportDelegate.AjaxGetMachineList(vo, userVO);
	}
	

	public static  Map AjaxGetMachineTestReportList(MachineTestReportNewVO vo, UserVO userVO) {
		MachineTestReportNewDelegate reportDelegate = new MachineTestReportNewDelegate();
		return reportDelegate.AjaxGetMachineTestReportList(vo, userVO);
	}

}
