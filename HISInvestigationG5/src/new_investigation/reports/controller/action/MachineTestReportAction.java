package new_investigation.reports.controller.action;


import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.reports.controller.utl.MachineTestReportUTIL;
import new_investigation.reports.controller.data.RequistionListingReportDATA;
import new_investigation.reports.controller.data.MachineTestReportDATA;
import new_investigation.reports.controller.fb.MachineTestReportFB;
import new_investigation.reports.controller.fb.MachineTestReportFB;
 






import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;






import java.util.ArrayList;



public class MachineTestReportAction  extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	
		return this.NEW(mapping,form,request,response);

	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MachineTestReportFB formBean = (MachineTestReportFB) form;
		MachineTestReportUTIL.fetchlab(formBean, request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETMACHINE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MachineTestReportFB formBean = (MachineTestReportFB) form;
		MachineTestReportUTIL.getLabBasedMachine(formBean, request);
		formBean.setHmode("NEW");                                           
		return mapping.findForward("NEW");
	}

	
	
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MachineTestReportFB formBean = (MachineTestReportFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//RequistionListingReportUTIL.setInitDtl(formBean, request);
		//RequistionListingReportUTIL.fetchLab(formBean, request);
		MachineTestReportDATA.showReport(formBean, request, response);
		
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		Status  objStatus=new Status();
		  objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("CANCEL");
	}
	

}
