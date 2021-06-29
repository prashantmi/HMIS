package billing.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;

public class DayEndDuplicateRptCNT extends CSRFGardTokenAction {
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified
	 * (org.apache.struts.action.ActionMapping,
	 *  org.apache.struts.action.ActionForm, 
	 *  javax.servlet.http.HttpServletRequest, 
	 *  javax.servlet.http.HttpServletResponse)
	 */
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
		generateToken(request);

			String target = "reportPage";
			DayEndDuplicateRptFB formBean = (DayEndDuplicateRptFB) form;
			DayEndDuplicateRptDATA.initVal(formBean, request);
			return mapping.findForward(target);
	}
	
	public ActionForward GETSUMMARYDETAILS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		
		DayEndDuplicateRptFB formBean = (DayEndDuplicateRptFB) form;
		DayEndDuplicateRptDATA.getSummaryDetails(formBean, request, response);
		
		return null;
		
	}
	
	public ActionForward REPRINT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		DayEndDuplicateRptDATA.rePrint(request, response);

		return null;

	}
	
	public ActionForward SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DayEndDuplicateRptFB formBean = (DayEndDuplicateRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		 
		DayEndDuplicateRptDATA.showReport(formBean, request, response);
		
		if(formBean.getStrReportFormat().toUpperCase().equals("TEXT")){
			formBean.setStrNormalMsg("Day End Report (Duplicate) Printed Successfully");
			return this.unspecified(mapping, form, request, response);
		}else{
			return null;
		}
		
		
	}
	
	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

}
