package mms.reports.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.reports.controller.data.IssueToPatientDetailRptData;
import mms.reports.controller.fb.IssueToPatientDetailRptFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
/**
 * Developer : Anurudra Goel
 * Version : 1.0
 * Date : 17/July/2009
 *  
*/
public class IssueToPatientDetailRptCNT extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		return this.INITDETAIL(mapping, form, request, response);

	}
	/**
	 * this function is used forward control to main  page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException*/
		public ActionForward INITDETAIL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String target="index";
		IssueToPatientDetailRptFB formBean = (IssueToPatientDetailRptFB) form;
		IssueToPatientDetailRptData.setInitDtl(formBean, request);
		return mapping.findForward(target);

	}
		/**
		 * this function is used to transfer control to Item category combo
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws HisException
		 * @throws SQLException
		 */
	public ActionForward ITEMCATEGORYCOMBO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

	
		IssueToPatientDetailRptFB formBean = (IssueToPatientDetailRptFB) form;
		IssueToPatientDetailRptData.setItemCategComboDtl(formBean, request, response);
		return null;

	}
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		IssueToPatientDetailRptFB formBean = (IssueToPatientDetailRptFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IssueToPatientDetailRptData.showReport(formBean, request, response);
		
		
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			   HttpServletRequest request, HttpServletResponse response)
			 {
			  ActionForward acFwd = new ActionForward();
			  acFwd.setPath("/hisglobal/initPage.jsp");
			  acFwd.setContextRelative(true);
			  return acFwd;
			 }
	public ActionForward Back(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		return this.INITDETAIL(mapping, form, request, response);

	}
		
}
