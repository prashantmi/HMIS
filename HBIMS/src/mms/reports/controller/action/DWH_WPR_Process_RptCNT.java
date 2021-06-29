package mms.reports.controller.action;



import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mms.reports.controller.data.DWH_WPR_Process_RptDATA;
import mms.reports.controller.fb.DWH_WPR_Process_RptFB;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DWH_WPR_Process_RptCNT extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		return this.INITVAL(mapping, form, request, response);
		
	}
	
	public ActionForward INITVAL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	throws HisException, SQLException {


String strTarget = "reportPage";
DWH_WPR_Process_RptFB formBean = (DWH_WPR_Process_RptFB) form;

//ListOfInstitutionsRptDATA.getInitializedValues(formBean,request, response);
return mapping.findForward(strTarget);
}

	


public void SHOWRPT(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws HisException, SQLException {

	DWH_WPR_Process_RptFB formBean = (DWH_WPR_Process_RptFB) form;
formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
DWH_WPR_Process_RptDATA.showReport(formBean, request, response);


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



