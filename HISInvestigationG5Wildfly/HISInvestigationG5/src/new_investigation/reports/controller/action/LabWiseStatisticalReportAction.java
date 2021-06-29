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

import new_investigation.reports.controller.utl.LabWiseStatisticalReportUTIL;
import new_investigation.reports.controller.data.LabWiseStatisticalReportDATA;
import new_investigation.reports.controller.fb.LabWiseStatisticalReportFB;
 




import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;




import java.util.ArrayList;



public class LabWiseStatisticalReportAction  extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	
		return this.NEW(mapping,form,request,response);

	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		LabWiseStatisticalReportFB formBean = (LabWiseStatisticalReportFB) form;
		LabWiseStatisticalReportUTIL.fetchLab(formBean, request);
		/*String fmonth =formBean.getFromMonth();
		String fyear =formBean.getFromYear();
		String tmonth =formBean.getToMonth();
		String tyear =formBean.getToYear();
		String fromMonthYear = fmonth+"-"+fyear;
		formBean.setFromMonthYear(fromMonthYear);
		String toMonthYear = tmonth+"-"+tyear;
		formBean.setToMonthYear(toMonthYear);*/
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		LabWiseStatisticalReportFB formBean = (LabWiseStatisticalReportFB) form;
		LabWiseStatisticalReportUTIL.fetchTest(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}

	
	
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		LabWiseStatisticalReportFB formBean = (LabWiseStatisticalReportFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//LabWiseStatisticalReportUTIL.setInitDtl(formBean, request);
		//LabWiseStatisticalReportUTIL.fetchLab(formBean, request);
		LabWiseStatisticalReportDATA.showReport(formBean, request, response);
		
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
