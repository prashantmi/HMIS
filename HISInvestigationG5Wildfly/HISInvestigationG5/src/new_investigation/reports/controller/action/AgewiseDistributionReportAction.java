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

import new_investigation.reports.controller.utl.AgewiseDistributionReportUTIL;
import new_investigation.reports.controller.data.AgewiseDistributionReportDATA;
import new_investigation.reports.controller.fb.AgewiseDistributionReportFB;
 




import new_investigation.reports.controller.fb.RequistionlistingReportFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;





import java.util.ArrayList;



public class AgewiseDistributionReportAction  extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	
		return this.NEW(mapping,form,request,response);
		
		

	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AgewiseDistributionReportFB formBean = (AgewiseDistributionReportFB) form;
		AgewiseDistributionReportUTIL.fetchDept(formBean, request);
		return mapping.findForward("NEW");
	}
	
	/*public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AgewiseDistributionReportFB formBean = (AgewiseDistributionReportFB) form;
		AgewiseDistributionReportUTIL.fetchDept(formBean, request);
		//AgewiseDistributionReportUTIL.fetchLab(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}*/
	
	public ActionForward GETTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AgewiseDistributionReportFB formBean = (AgewiseDistributionReportFB) form;
		AgewiseDistributionReportUTIL.fetchTest(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}
		
	public ActionForward GETLAB(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AgewiseDistributionReportFB formBean = (AgewiseDistributionReportFB) form;
		AgewiseDistributionReportUTIL.fetchLab(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}	
		
	
	public ActionForward GETSAMPLE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AgewiseDistributionReportFB formBean = (AgewiseDistributionReportFB) form;
		AgewiseDistributionReportUTIL.fetchSample(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}	
	
	
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		AgewiseDistributionReportFB formBean = (AgewiseDistributionReportFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//RequistionListingReportUTIL.setInitDtl(formBean, request);
		//RequistionListingReportUTIL.fetchLab(formBean, request);
		AgewiseDistributionReportDATA.showReport(formBean, request, response);
		
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
