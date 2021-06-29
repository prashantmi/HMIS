package new_investigation.reports.controller.data;


import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.reports.controller.fb.MachineTestReportFB;
import new_investigation.reports.controller.fb.SampleRejectionListingReportFB;
import new_investigation.reports.delegate.MachineTestReportNewDelegate;
import new_investigation.reports.delegate.MachineTestReportNewDelegate;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.MachineTestReportNewVO;
import new_investigation.vo.MachineTestReportVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;





import java.text.SimpleDateFormat;



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
