package mms.reports.controller.action;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.reports.controller.data.PendingSampleDetailRptDATA;
import mms.reports.controller.fb.PendingSampleDetailRptFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PendingSampleDetailRptCNT extends DispatchAction
{
	/**
	 * To display Lab Name on the Screen.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
		String target = "reportPage";
		PendingSampleDetailRptFB formBean = (PendingSampleDetailRptFB)form;
		PendingSampleDetailRptDATA.getInitializedValues(formBean,request);
		
		return mapping.findForward(target);
	}
	
	
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		PendingSampleDetailRptFB formBean = (PendingSampleDetailRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PendingSampleDetailRptDATA.showReport(formBean, request, response);
		
		
	}
	
}
