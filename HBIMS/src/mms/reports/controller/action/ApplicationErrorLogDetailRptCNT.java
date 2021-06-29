package mms.reports.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.reports.controller.data.ApplicationErrorLogDetailRptDATA;
import mms.reports.controller.fb.ApplicationErrorLogDetailRptFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ApplicationErrorLogDetailRptCNT extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		return this.init(mapping, form, request, response);
		
	}
	
	/** This method is used to forward the request on first jsp page
	 * And calls the methods getInitialValues() which is define in PendingListAgendaRptDATA java file. AND LIST VALUES to display combos 
	 * on first page. 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 * 
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		String strTarget = "reportPage";
		ApplicationErrorLogDetailRptFB formBean = (ApplicationErrorLogDetailRptFB) form;
		ApplicationErrorLogDetailRptDATA.getInit(formBean,request);
				
		return mapping.findForward(strTarget);
	}
	

	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ApplicationErrorLogDetailRptFB formBean = (ApplicationErrorLogDetailRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		ApplicationErrorLogDetailRptDATA.showReport(formBean, request, response);
		
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

}
