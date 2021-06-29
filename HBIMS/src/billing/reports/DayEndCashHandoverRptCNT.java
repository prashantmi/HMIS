package billing.reports;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;

public class DayEndCashHandoverRptCNT  extends CSRFGardTokenAction {



	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
		generateToken(request);

		DayEndCashHandoverRptFB formBean = (DayEndCashHandoverRptFB) form;
		DayEndCashHandoverRptDATA.initReportPage(formBean,request);
					String target = "reportPage";
				
			return mapping.findForward(target);
	}


	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DayEndCashHandoverRptFB formBean = (DayEndCashHandoverRptFB) form;
		DayEndCashHandoverRptDATA.showReport(formBean, request, response);
		
		
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
