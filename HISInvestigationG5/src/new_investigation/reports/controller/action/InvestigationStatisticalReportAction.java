/**
 * 
 */
package new_investigation.reports.controller.action;

import hisglobal.backutil.HisException;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.reports.controller.data.InvestigationListingReportDATA;
import new_investigation.reports.controller.data.InvestigationStatisticalReportDATA;
import new_investigation.reports.controller.fb.InvestigationStatisticalReportFB;
import new_investigation.reports.controller.utl.InvestigationStatisticalReportUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author cdac
 *
 */
public class InvestigationStatisticalReportAction extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	
		return this.NEW(mapping,form,request,response);
		
		

	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationStatisticalReportFB formBean = (InvestigationStatisticalReportFB) form;
		InvestigationStatisticalReportUTIL.fetchDept(formBean, request);
		return mapping.findForward("NEW");
	}
	
	/*public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationStatisticalReportFB formBean = (InvestigationStatisticalReportFB) form;
		InvestigationStatisticalReportUTIL.fetchDept(formBean, request);
		//InvestigationStatisticalReportUTIL.fetchLab(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}*/
	
	public ActionForward GETTEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationStatisticalReportFB formBean = (InvestigationStatisticalReportFB) form;
		InvestigationStatisticalReportUTIL.fetchTest(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}
		
	public ActionForward GETLAB(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationStatisticalReportFB formBean = (InvestigationStatisticalReportFB) form;
		InvestigationStatisticalReportUTIL.fetchLab(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}	
		
	
	public ActionForward GETSAMPLE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvestigationStatisticalReportFB formBean = (InvestigationStatisticalReportFB) form;
		InvestigationStatisticalReportUTIL.fetchSample(formBean, request);
		formBean.setHmode("NEW");
		return mapping.findForward("NEW");
	}	
	
	
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		InvestigationStatisticalReportFB formBean = (InvestigationStatisticalReportFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//RequistionListingReportUTIL.setInitDtl(formBean, request);
		//RequistionListingReportUTIL.fetchLab(formBean, request);
		InvestigationStatisticalReportDATA.showReport(formBean, request, response);
		
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
