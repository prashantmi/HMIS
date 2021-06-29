package ipd.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DischargeSummaryCNT extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

			String target = "reportPage";
			
			return mapping.findForward(target);
	}
	
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws HisException
	 * @throws SQLException
	 */
/////////////////////This Method is used for showing Report/////////////////////////////
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DischargeSummaryFB formBean = (DischargeSummaryFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DischargeSummaryDATA.showReport(formBean, request, response);
			
	}
	
}
