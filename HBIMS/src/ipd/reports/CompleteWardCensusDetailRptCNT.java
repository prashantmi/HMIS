package ipd.reports;

import hisglobal.exceptions.HisException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 
 * @author: Vivek Aggarwal
 * @Date: 12-July-2011
 * @Module:	ADT
 */
public class CompleteWardCensusDetailRptCNT extends DispatchAction {

	/**
	 * @see org.apache.struts.actions.DispatchAction#unspecified
	 * 
	 * @param mapping	the	org.apache.struts.action.ActionMapping
	 * @param form	the	org.apache.struts.action.ActionForm
	 * @param request	the javax.servlet.http.HttpServletRequest
	 * @param response	the javax.servlet.http.HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CompleteWardCensusDetailRptFB formBean = (CompleteWardCensusDetailRptFB) form;
		CompleteWardCensusDetailRptDATA.initReportPage(formBean,request);
		String target = "reportPage";
		return mapping.findForward(target);
	}
	
	
	/**
	 * This Method is used for Showing Report
	 * 
	 * @param mapping	the	org.apache.struts.action.ActionMapping
	 * @param form	the	org.apache.struts.action.ActionForm
	 * @param request	the javax.servlet.http.HttpServletRequest
	 * @param response	the javax.servlet.http.HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CompleteWardCensusDetailRptFB formBean = (CompleteWardCensusDetailRptFB) form;
		CompleteWardCensusDetailRptDATA.showReport(formBean, request, response);
		
		return null;
	}
	
	/**
	 * This Method is used to Cancel the Page
	 * 
	 * @param mapping	the	org.apache.struts.action.ActionMapping
	 * @param form	the	org.apache.struts.action.ActionForm
	 * @param request	the javax.servlet.http.HttpServletRequest
	 * @param response	the javax.servlet.http.HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	
	/**
	 * This Method is used to Clear the Page
	 * 
	 * @param mapping	the	org.apache.struts.action.ActionMapping
	 * @param form	the	org.apache.struts.action.ActionForm
	 * @param request	the javax.servlet.http.HttpServletRequest
	 * @param response	the javax.servlet.http.HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CompleteWardCensusDetailRptFB formBean = (CompleteWardCensusDetailRptFB) form;
		CompleteWardCensusDetailRptDATA.initReportPage(formBean,request);
		String target = "reportPage";
		return mapping.findForward(target);
	}
}
